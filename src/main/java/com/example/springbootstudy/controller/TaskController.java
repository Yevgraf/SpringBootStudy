package com.example.springbootstudy.controller;

import com.example.springbootstudy.model.Task;
import com.example.springbootstudy.model.TaskDetails;
import com.example.springbootstudy.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getALLtasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody TaskDetails taskDetails){
        Task task = taskRepository.findById(id).orElse(null);

        if (task!=null){
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        }else{
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id){
        taskRepository.deleteById(id);
    }
}
