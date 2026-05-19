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

    public LoginService(
            LoginMapper loginMapper,
            PasswordEncoder passwordEncoder) {

        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザー登録処理
    @Transactional
    public void register(UserForm form) {

        Login user = new Login();

        user.setUsername(form.getUsername());

        // パスワードをハッシュ化して保存
        user.setPassword(
                passwordEncoder.encode(form.getPassword())
        );

        loginMapper.insert(user);
    }

    // ユーザー取得
    public Login findByUsername(String username) {

        return loginMapper.findByUsername(username);
    }

    // ユーザー名が存在するか確認
    public boolean existsByUsername(String username) {

        return loginMapper.findByUsername(username) != null;
    }

    // パスワード照合処理
    public boolean matches(
            String rawPassword,
            String hashedPassword) {

        return passwordEncoder.matches(
                rawPassword,
                hashedPassword
        );
    }
}