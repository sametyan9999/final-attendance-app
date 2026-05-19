package sample.common.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.thymeleaf.form.UserForm;

@Service
@Transactional(readOnly = true)
public class LoginService {

	private final LoginMapper loginMapper;
	private final PasswordEncoder passwordEncoder;

	public LoginService(LoginMapper loginMapper, PasswordEncoder passwordEncoder) {

		this.loginMapper = loginMapper;
		this.passwordEncoder = passwordEncoder;
	}

	// 登録時は平文パスワードを保存しないよう、ハッシュ化してからDBへ登録する
	@Transactional
	public void register(UserForm form) {

		Login user = new Login();

		user.setUsername(form.getUsername());

		user.setPassword(passwordEncoder.encode(form.getPassword()));

		loginMapper.insert(user);
	}

	// ログイン認証時に、入力されたユーザー名に対応する情報を取得する
	public Login findByUsername(String username) {

		return loginMapper.findByUsername(username);
	}

	// 同じユーザー名の重複登録を防ぐために存在確認を行う
	public boolean existsByUsername(String username) {

		return loginMapper.findByUsername(username) != null;
	}

	// 入力されたパスワードと、DBに保存されたハッシュ値を照合する
	public boolean matches(String rawPassword, String hashedPassword) {

		return passwordEncoder.matches(rawPassword, hashedPassword);
	}
}