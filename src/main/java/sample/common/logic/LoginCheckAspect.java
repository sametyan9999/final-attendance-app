package sample.common.logic;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LoginCheckAspect {

    private final HttpSession session;

    public LoginCheckAspect(HttpSession session) {
        this.session = session;
    }

    @Before("execution(* sample.thymeleaf.web.TaskController.*(..))")
    public void checkLogin() {

        Object loginUser = session.getAttribute("loginUser");

        if (loginUser == null) {
            throw new RuntimeException("ログインしてください");
        }
    }
}