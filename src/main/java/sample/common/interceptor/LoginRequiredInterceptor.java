package sample.common.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {

		// ログイン済みユーザーのみ
		// タスク画面へアクセスできるようにするため、
		// セッションに loginUser が存在するか確認する
		HttpSession session = request.getSession(false);

		// 未ログインの場合はログイン画面へリダイレクト
		if (session == null || session.getAttribute("loginUser") == null) {

			response.sendRedirect("/login");

			return false;
		}

		return true;
	}
}