package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    // ログインユーザーのタスク一覧取得
    public List<Task> findByUsername(String username, int page) {

        int limit = 10;
        int offset = (page - 1) * limit;

        return taskMapper.findByUsername(username, limit, offset);
    }

    // ログインユーザーのタスク件数取得
    public int countByUsername(String username) {

        return taskMapper.countByUsername(username);
    }

    // ID検索
    public Task findById(Integer id) {

        return taskMapper.findById(id);
    }

    // 登録
    public void insert(Task task) {

        taskMapper.insert(task);
    }

    // 更新
    public void update(Task task) {

        taskMapper.update(task);
    }

    // 削除
    public void delete(Integer id) {

        taskMapper.delete(id);
    }
}