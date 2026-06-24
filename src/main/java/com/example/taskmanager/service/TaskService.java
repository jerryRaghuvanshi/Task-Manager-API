package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task,String username);
    List<Task> getAllTasks(String username);
    Task updateTask(Long id ,Task task);
    Task getTaskById(Long id);
    void deleteTask(Long id);
    Task markAsCompleted(Long id);

}
