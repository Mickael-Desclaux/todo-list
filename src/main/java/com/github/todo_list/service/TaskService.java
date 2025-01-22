package com.github.todo_list.service;

import com.github.todo_list.model.entity.Task;
import com.github.todo_list.model.enums.TaskStatusEnum;
import com.github.todo_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new RuntimeException("Task not found with id " + id);
        }
    }

    public Task create(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setTaskStatus(TaskStatusEnum.TODO);
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Task not found"));
        existingTask.setUpdatedAt(LocalDateTime.now());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setTaskStatus(task.getTaskStatus());
        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        taskRepository.delete(existingTask);
    }
}
