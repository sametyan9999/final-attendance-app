package sample.common.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserForm;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;

@Service
public class LoginService {

    private final LoginMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginMapper userMapper,
                        PasswordEncoder passwordEncoder) {

        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

 // ユーザー登録処理
    public void register(UserForm form) {

        Login user = new Login();
        user.setUsername(form.getUsername());

        // パスワードをハッシュ化して保存
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        userMapper.insert(user);
    }

    // ユーザー取得
    public Login findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    // ユーザー名が存在するか確認
    public boolean existsByUsername(String username) {
        return userMapper.findByUsername(username) != null;
    }
    
 // パスワード照合処理
    public boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}