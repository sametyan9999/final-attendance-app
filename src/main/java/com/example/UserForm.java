package com.example;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// ユーザー登録・ログイン画面の入力値を受け取るクラス
public class UserForm {

    // ユーザー名
    @NotBlank(message = "ユーザー名を入力してください")
    @Pattern(
        regexp = "^[a-zA-Z]+$",
        message = "ユーザー名は半角英字で入力してください"
    )
    private String username;

    // パスワード
    @NotBlank(message = "パスワードを入力してください")

    // 5文字以上
    @Size(min = 5, message = "パスワードは5文字以上で入力してください")

    @Pattern(
        regexp = "^[a-zA-Z0-9]+$",
        message = "パスワードは半角英数字で入力してください"
    )
    private String password;

    // getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}