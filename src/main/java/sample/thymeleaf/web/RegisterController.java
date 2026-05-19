package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sample.common.service.LoginService;
import sample.thymeleaf.form.UserForm;

// ユーザー登録用Controller
@Controller
public class RegisterController {

    private final LoginService loginService;

    public RegisterController(LoginService loginService) {
        this.loginService = loginService;
    }

    // ユーザー登録画面表示
    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "register";
    }

    // ユーザー登録処理
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userForm") UserForm form,
            BindingResult result) {

        // 入力エラー時は入力内容を保持したまま再表示する
        if (result.hasErrors()) {
            return "register";
        }

        // 登録済みユーザー名との重複を防ぐ
        if (loginService.existsByUsername(
                form.getUsername()
        )) {

            result.reject(
                    "register.failed",
                    "ユーザー登録に失敗しました"
            );

            return "register";
        }

        loginService.register(form);

        return "redirect:/register/complete";
    }

    // 登録完了画面表示
    @GetMapping("/register/complete")
    public String showRegisterComplete() {

        return "register-complete";
    }
}