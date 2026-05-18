package sample.thymeleaf.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sample.common.dao.entity.Login;
import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

// タスク管理用Controller
@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // タスク一覧表示
    @GetMapping("/tasks")
    public String showTasks(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model,
            HttpSession session) {

        Login loginUser = (Login) session.getAttribute("loginUser");

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

    // タスク編集画面表示
    @GetMapping("/tasks/edit/{id}")
    public String editTask(
            @PathVariable("id") Integer id,
            Model model,
            HttpSession session) {

        Login loginUser = (Login) session.getAttribute("loginUser");

        Task task = taskService.findByIdForOwner(
                id,
                loginUser.getUsername()
        );

        model.addAttribute("task", task);

        return "tasks/form-edit";
    }

    // タスク新規作成画面表示
    @GetMapping("/tasks/new")
    public String showNewForm(Model model) {

        model.addAttribute("task", new Task());

        return "tasks/form-new";
    }

    // タスク新規登録処理
    @PostMapping("/tasks")
    public String createTask(
            @Valid Task task,
            BindingResult result,
            HttpSession session,
            Model model) {

        // バリデーションエラー
        if (result.hasErrors()) {
            return "tasks/form-new";
        }

        Login loginUser = (Login) session.getAttribute("loginUser");

        task.setUsername(loginUser.getUsername());

        taskService.insert(task);

        return "redirect:/tasks";
    }

    // タスク更新処理
    @PostMapping("/tasks/update/{id}")
    public String updateTask(
            @PathVariable("id") Integer id,
            @Valid Task task,
            BindingResult result,
            Model model,
            HttpSession session) {

        task.setId(id);

        // バリデーションエラー
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "tasks/form-edit";
        }

        Login loginUser = (Login) session.getAttribute("loginUser");

        taskService.updateForOwner(
                task,
                loginUser.getUsername()
        );

        return "redirect:/tasks";
    }

    // タスク削除処理
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(
            @PathVariable("id") Integer id,
            HttpSession session) {

        Login loginUser = (Login) session.getAttribute("loginUser");

        taskService.deleteForOwner(
                id,
                loginUser.getUsername()
        );

        return "redirect:/tasks";
    }
}