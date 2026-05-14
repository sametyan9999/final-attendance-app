package sample.common.logic;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

// ログインチェック用Aspect
@Aspect
@Component
public class LoginCheckAspect {

    private final HttpSession session;

    public LoginCheckAspect(HttpSession session) {
        this.session = session;
    }

    // TaskController実行前にログイン状態を確認
    @Before("execution(* sample.thymeleaf.web.TaskController.*(..))")
    public void checkLogin() {

        Object loginUser = session.getAttribute("loginUser");

        // 未ログイン時
        if (loginUser == null) {
            throw new RuntimeException("ログインしてください");
        }
    }
}