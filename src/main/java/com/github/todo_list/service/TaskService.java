package com.github.todo_list.service;

import com.github.todo_list.exception.ResourceNotFoundException;
import com.github.todo_list.model.entity.Task;
import com.github.todo_list.model.enums.TaskStatusEnum;
import com.github.todo_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
    }

    @Transactional
    public Task create(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setTaskStatus(TaskStatusEnum.TODO);
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        existingTask.setUpdatedAt(LocalDateTime.now());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setTaskStatus(task.getTaskStatus());
        return taskRepository.save(existingTask);
    }

    @Transactional
    public void delete(Long taskId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        taskRepository.delete(existingTask);
    }
}
