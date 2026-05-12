package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Task> findAll(int page, int size) {

        int offset = (page - 1) * size;

        return taskMapper.findAll(size, offset);
    }

    public int countAll() {
        return taskMapper.countAll();
    }

    public Task findById(Integer id) {
        return taskMapper.findById(id);
    }

    public void insert(Task task) {
        taskMapper.insert(task);
    }

    public void update(Task task) {
        taskMapper.update(task);
    }

    public void delete(Integer id) {
        taskMapper.delete(id);
    }
}