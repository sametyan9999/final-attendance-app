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

    private final LoginService userService;

    public RegisterController(LoginService userService) {
        this.userService = userService;
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

        // バリデーションエラー
        if (result.hasErrors()) {
            return "register";
        }

     // ユーザー登録失敗
        if (userService.existsByUsername(form.getUsername())) {

            result.reject(
                    "register.failed",
                    "ユーザー登録に失敗しました"
            );

            return "register";
        }

        // ユーザー登録
        userService.register(form);

        return "redirect:/register/complete";
    }

    // 登録完了画面表示
    @GetMapping("/register/complete")
    public String showRegisterComplete() {

        return "register-complete";
    }
}