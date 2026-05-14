package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// TOP画面用Controller
@Controller
public class HomeController {

    // TOP画面表示
    @GetMapping("/")
    public String home() {
        return "homePage";
    }
}