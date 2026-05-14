package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import sample.common.dao.entity.Login;

@Mapper
public interface LoginMapper {

    // ユーザー登録
    @Insert("""
        INSERT INTO login (username, password)
        VALUES (#{username}, #{password})
    """)
    void insert(Login user);

    // ユーザー取得（ログイン用）
    @Select("""
        SELECT id, username, password
        FROM login
        WHERE username = #{username}
    """)
    Login findByUsername(String username);
}