package com.example;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // =========================
    // ユーザー登録
    // =========================
    @Insert("""
        INSERT INTO login (username, password)
        VALUES (#{username}, #{password})
    """)
    void insert(User user);

    // =========================
    // ユーザー取得（ログイン用）
    // =========================
    @Select("""
        SELECT id, username, password
        FROM login
        WHERE username = #{username}
    """)
    User findByUsername(String username);
}