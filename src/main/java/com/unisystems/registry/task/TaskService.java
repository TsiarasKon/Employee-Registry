package com.unisystems.registry.task;
import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.Employee;
import com.unisystems.registry.employee.EmployeeRepository;
import com.unisystems.registry.task.search_task_strategy.SearchTaskStrategy;
import com.unisystems.registry.task.search_task_strategy.SearchTaskStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TaskMapper mapper;

    @Autowired
    private SearchTaskStrategyFactory factory;

    public TaskService(TaskMapper mapper,TaskRepository taskRepository){
        this.mapper= mapper;
        this.taskRepository = taskRepository;
    }

    public GenericResponse<MultipleTaskResponse> getAllTasks() {
        Iterable<Task> retrievedTasks = taskRepository.findAll();
        List<TaskResponse> tasks = new ArrayList<>();

        for(Task task : retrievedTasks){
            tasks.add(mapper.mapTask(task));
        }

        return new GenericResponse<>(new MultipleTaskResponse(tasks));
    }

    public GenericResponse<TaskResponseId> getTaskWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapTaskId(taskRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Task", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public GenericResponse<MultipleTaskResponseId> getTaskById(long taskId){
        try {
            List<TaskResponseId> tasks = getTaskResponses(taskId);
            return new GenericResponse<>((new MultipleTaskResponseId(tasks)));
        } catch (InvalidIdException e){
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    private List<TaskResponseId> getTaskResponses(long taskId) throws InvalidIdException {
        List<TaskResponseId> tasks = new ArrayList<>();

        if (getTaskWithId(taskId).getError() != null) {
            throw new InvalidIdException("Task", taskId);
        }

        tasks.add(getTaskWithId(taskId).getData());

        return tasks;
    }

    public GenericResponse<MultipleTaskResponseId> getTasksByDifficulty(String difficulty, Long assignedEmployeesNumber){
        Iterable<Task> retrievedTasks = taskRepository.findAll();
        if(difficulty == null) {
            return getTasksByNumberOfEmployees(assignedEmployeesNumber, retrievedTasks);
        }
        else{
            SearchTaskStrategy strategy = factory.makeStrategyForDifficulty(difficulty);
            try {
                return new GenericResponse<>(mapper.mapTaskList(strategy.execute(assignedEmployeesNumber, retrievedTasks)));
            } catch (InvalidIdException e) {
                return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
            }
        }
    }

    private GenericResponse<MultipleTaskResponseId> getTasksByNumberOfEmployees(Long assignedEmployeesNumber, Iterable<Task> retrievedTasks) {
        List<TaskResponseId> taskResponseIdList = new ArrayList<>();
        for (Task task : retrievedTasks) {
            if(assignedEmployeesNumber == task.getAssignedEmployee().size()){
                taskResponseIdList.add(mapper.mapTaskId(task));
            }
        }
        return new GenericResponse<>(new MultipleTaskResponseId(taskResponseIdList));
    }

    public Task post(TaskRequest taskRequest) throws InvalidIdException, InterUnitTaskException {
        List<Employee> assignedEmployeeList = getValidAssignedEmployeeList(taskRequest.getAssignedEmployeeIds());
        Task task = new Task(taskRequest.getTitle(), taskRequest.getDesc(),
                taskRequest.getEstimationA(), taskRequest.getEstimationB(), taskRequest.getEstimationC(),
                taskRequest.getTaskStatusValid(), taskRequest.getUpdates());
        task.setAssignedEmployee(assignedEmployeeList);
        return taskRepository.save(task);
    }

    public Task put(TaskRequest taskRequest, long id) throws InvalidIdException, InterUnitTaskException {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Task", id));
        List<Employee> assignedEmployeeList = getValidAssignedEmployeeList(taskRequest.getAssignedEmployeeIds());
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDesc());
        task.setEstimationA(taskRequest.getEstimationA());
        task.setEstimationB(taskRequest.getEstimationB());
        task.setEstimationC(taskRequest.getEstimationC());
        task.setTaskStatus(taskRequest.getTaskStatusValid());
        task.setUpdates(taskRequest.getUpdates());
        task.setAssignedEmployee(assignedEmployeeList);
        return taskRepository.save(task);
    }

    public Task patch(TaskRequest taskRequest, long id) throws InvalidIdException, InterUnitTaskException {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Task", id));
        List<Employee> assignedEmployeeList = getValidAssignedEmployeeList(taskRequest.getAssignedEmployeeIds());
        if (taskRequest.getTitle() != null) task.setTitle(taskRequest.getTitle());
        if (taskRequest.getDesc() != null) task.setDescription(taskRequest.getDesc());
        if (taskRequest.getEstimationA() <= 0) task.setEstimationA(taskRequest.getEstimationA());
        if (taskRequest.getEstimationB() <= 0) task.setEstimationB(taskRequest.getEstimationB());
        if (taskRequest.getEstimationC() <= 0) task.setEstimationC(taskRequest.getEstimationC());
        if (taskRequest.getTaskStatusValid() != null) task.setTaskStatus(taskRequest.getTaskStatusValid());
        if (taskRequest.getUpdates() != null) task.setUpdates(taskRequest.getUpdates());
        if (taskRequest.getAssignedEmployeeIds() != null) task.setAssignedEmployee(assignedEmployeeList);
        return taskRepository.save(task);
    }

    private List<Employee> getValidAssignedEmployeeList(List<Long> employeeIds) throws InvalidIdException, InterUnitTaskException {
        List<Employee> assignedEmployeeList = new ArrayList<>();
        long taskUnitId = -1;
        Employee currEmployee;
        for (long employeeId : employeeIds) {
            currEmployee = employeeRepository.findById(employeeId).orElseThrow(()
                    -> new InvalidIdException("Employee", employeeId));
            if (taskUnitId != -1 && currEmployee.getUnit().getId() != taskUnitId)   // if currEmployee belongs to a different unit from previous employee
                throw new InterUnitTaskException();
            taskUnitId = currEmployee.getUnit().getId();
            assignedEmployeeList.add(currEmployee);
        }
        return assignedEmployeeList;
    }
}
