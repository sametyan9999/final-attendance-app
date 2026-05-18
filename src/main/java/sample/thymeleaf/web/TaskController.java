package sample.thymeleaf.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sample.common.dao.entity.Login;
import sample.common.dao.entity.Task;
import sample.common.service.TaskService;
import sample.thymeleaf.form.TaskForm;

// タスク管理用Controller
@Controller
public class TaskController {

    private static final int PAGE_SIZE = 10;

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

        int totalCount = taskService.countByUsername(username);

        int totalPages = Math.max(
                1,
                (int) Math.ceil((double) totalCount / PAGE_SIZE)
        );

        int safePage = Math.min(
                Math.max(page, 1),
                totalPages
        );

        List<Task> list = taskService.findByUsername(
                username,
                safePage,
                PAGE_SIZE
        );

        model.addAttribute("tasks", list);
        model.addAttribute("currentPage", safePage);
        model.addAttribute("totalPages", totalPages);

        return "tasks/list";
    }

    // タスク編集画面表示
    @GetMapping("/tasks/edit/{id}")
    public String editTask(
            @PathVariable("id") Integer id,
            Model model,
            HttpSession session) {

        Login loginUser =
                (Login) session.getAttribute("loginUser");

        Task task = taskService.findByIdForOwner(
                id,
                loginUser.getUsername()
        );

        TaskForm form = new TaskForm();

        form.setTitle(task.getTitle());
        form.setContent(task.getContent());
        form.setName(task.getName());
        form.setStartDate(task.getStartDate());
        form.setEndDate(task.getEndDate());

        model.addAttribute("task", form);

        return "tasks/form-edit";
    }

    // タスク新規作成画面表示
    @GetMapping("/tasks/new")
    public String showNewForm(Model model) {

        model.addAttribute("task", new TaskForm());

        return "tasks/form-new";
    }

    // タスク新規登録処理
    @PostMapping("/tasks")
    public String createTask(
            @Valid @ModelAttribute("task") TaskForm form,
            BindingResult result,
            HttpSession session,
            Model model) {

        // バリデーションエラー
        if (result.hasErrors()) {
            return "tasks/form-new";
        }

        Login loginUser =
                (Login) session.getAttribute("loginUser");

        taskService.insert(
                form,
                loginUser.getUsername()
        );

        return "redirect:/tasks";
    }

    // タスク更新処理
    @PostMapping("/tasks/update/{id}")
    public String updateTask(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("task") TaskForm form,
            BindingResult result,
            Model model,
            HttpSession session) {

        // バリデーションエラー
        if (result.hasErrors()) {
            model.addAttribute("task", form);
            return "tasks/form-edit";
        }

        Login loginUser =
                (Login) session.getAttribute("loginUser");

        taskService.updateForOwner(
                id,
                form,
                loginUser.getUsername()
        );

        return "redirect:/tasks";
    }

    // タスク削除処理
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(
            @PathVariable("id") Integer id,
            HttpSession session) {

        Login loginUser =
                (Login) session.getAttribute("loginUser");

        taskService.deleteForOwner(
                id,
                loginUser.getUsername()
        );

        return "redirect:/tasks";
    }
}