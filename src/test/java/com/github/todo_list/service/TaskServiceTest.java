package com.github.todo_list.service;

import com.github.todo_list.model.entity.Task;
import com.github.todo_list.model.enums.TaskStatusEnum;
import com.github.todo_list.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    public void setup() {
        task = new Task(1L, "Test Task", "Description",
                TaskStatusEnum.TODO,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getAll();

        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }

    @Test
    public void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task foundTask = taskService.getById(1L);

        assertNotNull(foundTask);
        assertEquals("Test Task", foundTask.getTitle());
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task(null, "New Task", "New Description",
                TaskStatusEnum.TODO, null, null);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.create(newTask);

        assertNotNull(createdTask);
        assertEquals(TaskStatusEnum.TODO, createdTask.getTaskStatus());
        assertNotNull(createdTask.getCreatedAt());
    }

    @Test
    public void testUpdateTask() {
        Task updatedDetails = new Task(null, "Updated Title", "Updated Description",
                TaskStatusEnum.IN_PROGRESS, null, null);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.update(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    public void testDeleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        assertDoesNotThrow(() -> taskService.delete(1L));
        verify(taskRepository, times(1)).delete(task);
    }
}
