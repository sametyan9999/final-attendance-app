package com.example;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // ユーザー登録処理
    public void register(UserForm form) {

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());

        userMapper.insert(user);
    }

    // ユーザー取得
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    // ユーザー名が存在するか確認
    public boolean existsByUsername(String username) {
        return userMapper.findByUsername(username) != null;
    }
}