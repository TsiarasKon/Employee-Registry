package com.unisystems.registry.task;
import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
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
    TaskMapper mapper;

    @Autowired
    private SearchTaskStrategyFactory factory;

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
}
