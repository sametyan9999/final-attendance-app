package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.UserForm;

import jakarta.validation.Valid;
import sample.common.service.LoginService;

@Controller
public class RegisterController {

    private final LoginService userService;

    public RegisterController(LoginService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userForm") UserForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.existsByUsername(form.getUsername())) {
            result.rejectValue("username", "", "このユーザー名はすでに使われています");
            return "register";
        }

        userService.register(form);

        return "redirect:/register/complete";
    }

    @GetMapping("/register/complete")
    public String showRegisterComplete() {
        return "register-complete";
    }
}