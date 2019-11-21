package com.unisystems.registry.task;

import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
     TaskMapper mapper;

    @Autowired
    TaskRepository repository;

    public GenericResponse<MultipleTaskResponse> getAllTasks(){
        Iterable<Task> retrievedTasks = repository.findAll();
        List<TaskResponse> tasks = new ArrayList<>();
        for(Task task : retrievedTasks){
            tasks.add(mapper.mapTaskResponseFromTask(task));
        }
        return new GenericResponse<>(new MultipleTaskResponse(tasks));
    }

    public List<TaskResponseById> getTaskWithId(long taskId){
        Task retrievedTask = repository.findById(taskId).get();
        List<TaskResponseById> tasks = new ArrayList<>();
        //List<Employee> employees = new ArrayList<>();
                tasks.add(mapper.mapTaskResponseFromTaskId(retrievedTask));
        return tasks;
    }


//    public GenericResponse<TaskResponseById> getTaskWithId(long taskId) {
//            return new GenericResponse<>(mapper.mapTaskResponseFromTaskiD(repository.findById(taskId).orElseThrow(()
//                    -> new InvalidIdException("Unit",taskId))));
//    }


//    public List<EmployeeResponse> getEmployeesByCriteriaAndId(String uCriteria, Long id) {
//        Iterable<Employee> retrievedEmployees = repository.findAll();
//        List<EmployeeResponse> employees = new ArrayList<>();
//        switch (uCriteria) {
//            case "Company":
//                for (Employee employee : retrievedEmployees) {
//                    if (employee.getUnit().getDepartment().getBusinessUnit().getCompany().getId() == id) {
//                        EmployeeResponse employeesToAdd = mapper.mapEmployeeResponseFromEmployee(employee);
//                        employees.add((employeesToAdd));
//                    }
//                }
//                break;
}
