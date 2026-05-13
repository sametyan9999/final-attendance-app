package sample.common.service;

import org.springframework.stereotype.Service;

import com.example.UserForm;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;

@Service
public class LoginService {

    private final LoginMapper userMapper;

    public LoginService(LoginMapper userMapper) {
        this.userMapper = userMapper;
    }

    // ユーザー登録処理
    public void register(UserForm form) {

        Login user = new Login();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());

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
}