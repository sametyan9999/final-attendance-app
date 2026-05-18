package sample.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

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

    // ログインユーザー本人のタスク取得
    public Task findByIdForOwner(Integer id, String username) {

        return taskMapper.findByIdAndUsername(id, username);
    }

    // 登録
    public void insert(Task task) {

        taskMapper.insert(task);
    }

    // ログインユーザー本人のタスク更新
    public void updateForOwner(Task task, String username) {

        taskMapper.updateByOwner(task, username);
    }

    // ログインユーザー本人のタスク削除
    public void deleteForOwner(Integer id, String username) {

        taskMapper.deleteByOwner(id, username);
    }
}