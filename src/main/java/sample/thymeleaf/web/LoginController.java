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

    private final LoginService userService;

    public LoginController(LoginService userService) {
        this.userService = userService;
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

        // バリデーションエラー
        if (result.hasErrors()) {
            return "login";
        }

        // ユーザー取得
        Login user = userService.findByUsername(form.getUsername());

        // ログイン失敗
        if (user == null ||
            !userService.matches(form.getPassword(), user.getPassword())) {

            result.reject(
                    "login.failed",
                    "ユーザー名またはパスワードが正しくありません"
            );

            return "login";
        }

        // ログイン成功
        session.setAttribute("loginUser", user);

        return "redirect:/tasks";
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}