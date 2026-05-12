package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // ログイン画面表示
    // =========================
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "login";
    }

    // =========================
    // ログイン処理
    // =========================
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("userForm") UserForm form,
            BindingResult result,
            HttpSession session
    ) {

        // バリデーションエラー
        if (result.hasErrors()) {
            return "login";
        }

        // ユーザー取得
        User user = userService.findByUsername(form.getUsername());

        // ユーザー存在チェック
        if (user == null) {
            result.rejectValue("username", "", "ユーザーが存在しません");
            return "login";
        }

        // パスワードチェック
        if (!user.getPassword().equals(form.getPassword())) {
            result.rejectValue("password", "", "パスワードが違います");
            return "login";
        }

        // ログイン成功 → セッション保存
        session.setAttribute("loginUser", user);

        // タスク一覧へ
        return "redirect:/tasks";
    }
    
 // =========================
 // ログアウト処理
 // =========================
 @GetMapping("/logout")
 public String logout(HttpSession session) {

     session.invalidate();

     return "redirect:/login";
 }
}