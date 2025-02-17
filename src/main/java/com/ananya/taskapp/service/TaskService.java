package com.ananya.taskapp.service;

import com.ananya.taskapp.model.Task;
import com.ananya.taskapp.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private TaskRepo repo;

    @Autowired
    public void setRepo(TaskRepo repo) {
        this.repo = repo;
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return repo.save(task);
    }

    public List<Task> getAllTask() {
        return repo.findAll();
    }

    public Task getTaskById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Task updateTask(Task task) {
        Task existingTask = repo.findById(task.getTaskId()).orElse(null);
        if (existingTask != null) {
            task.setCreatedAt(existingTask.getCreatedAt());
            return repo.save(task);
        }
        return null;
    }

    public void deleteTask(int id) {
        repo.deleteById(id);
    }


    public Task updateStatus(int id, String status) {
        Task existingTask = repo.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setStatus(status);
            return repo.save(existingTask);
        }
        return null;
    }

}
