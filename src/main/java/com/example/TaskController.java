package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // タスク一覧
    @GetMapping("/tasks")
    public String showTasks(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        String username = loginUser.getUsername();

        int size = 10;

        List<Task> list = taskService.findByUsername(username, page);

        int totalCount = taskService.countByUsername(username);

        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("tasks", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "tasks/list";
    }

    // 編集画面
    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable("id") Integer id, Model model) {

        Task task = taskService.findById(id);

        model.addAttribute("task", task);

        return "tasks/form-edit";
    }

    // 新規作成画面
    @GetMapping("/tasks/new")
    public String showNewForm() {

        return "tasks/form-new";
    }

    // タスク新規登録処理
    @PostMapping("/tasks")
    public String createTask(Task task, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        task.setUsername(loginUser.getUsername());

        taskService.insert(task);

        return "redirect:/tasks";
    }

    // タスク更新処理
    @PostMapping("/tasks/update/{id}")
    public String updateTask(@PathVariable("id") Integer id, Task task) {

        task.setId(id);

        taskService.update(task);

        return "redirect:/tasks";
    }

    // タスク削除処理
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id) {

        taskService.delete(id);

        return "redirect:/tasks";
    }
}