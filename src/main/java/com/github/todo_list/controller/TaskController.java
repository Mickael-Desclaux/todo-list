package com.github.todo_list.controller;

import com.github.todo_list.model.entity.Task;
import com.github.todo_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.create(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(taskId, task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
