package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    public final TaskRepository taskRepository;
    public final UserRepository userRepository;


    @Override
    public Task createTask(Task task,String username) {
       task.setCompleted(false);

      User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User not found"));
      task.setOwner(user);
       return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(String username) {
        return taskRepository.findByOwnerUsername(username);
    }

    @Override
    public Task updateTask( Long id ,Task task)  {

        Task existingTask = taskRepository.findById(id).orElse(null);
        if(existingTask == null){

            throw new TaskNotFoundException(
                    "Task "+id+" not found");
        }

        existingTask.setTitle(task.getTitle());

        existingTask.setDescription(task.getDescription());

        existingTask.setCompleted(task.isCompleted());
        return taskRepository.save(existingTask);
    }

    @Override
    public Task getTaskById(Long id) {

        return taskRepository.findById(id).orElseThrow( ()->new TaskNotFoundException(
                "Task "+id+" not found"
        ));
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(
                        ()->new TaskNotFoundException(
                                "Task "+id+" not found"
                        )
                );


        taskRepository.delete(task);
    }

    @Override
    public Task markAsCompleted(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()->


             new TaskNotFoundException(
                    "Task "+id+" not found"));

        task.setCompleted(true);
        return taskRepository.save(task);
    }
}
