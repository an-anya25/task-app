package com.ananya.taskapp.controller;

import com.ananya.taskapp.model.Task;
import com.ananya.taskapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskService service;

    @Autowired
    public void setService(TaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = service.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.getAllTask();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = service.getTaskById(id);
        if (task != null)
            return new ResponseEntity<>(task, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = service.updateTask(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/task/{id}")
    @ResponseBody
    public ResponseEntity<Task> updateStatus(@PathVariable int id, @RequestBody String status) {
        Task updatedTask = service.updateStatus(id, status);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return new ResponseEntity<>("Task deleted", HttpStatus.OK);
    }
}
