package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sample.common.dao.entity.Login;
import sample.common.service.LoginService;
import sample.thymeleaf.form.UserForm;

// ログイン機能用Controller
@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // ログイン画面表示
    @GetMapping("/login")
    public String showLogin(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("userForm") UserForm form,
            BindingResult result,
            HttpSession session) {

        // 入力エラー時は入力内容を保持したまま再表示する
        if (result.hasErrors()) {
            return "login";
        }

        Login user = loginService.findByUsername(
                form.getUsername()
        );

        // ユーザー存在有無を判別できないよう、
        // エラーメッセージは統一する
        if (user == null ||
            !loginService.matches(
                    form.getPassword(),
                    user.getPassword()
            )) {

            result.reject(
                    "login.failed",
                    "ユーザー名またはパスワードが正しくありません"
            );

            return "login";
        }

        // セッションへ保存してログイン状態を保持する
        session.setAttribute("loginUser", user);

        return "redirect:/tasks";
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // セッションを破棄してログイン状態を削除する
        session.invalidate();

        return "redirect:/login";
    }
}