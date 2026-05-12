package com.example;

// ↓ バリデーション用のアノテーションを使うためのimport
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// ↓ 画面の入力値を受け取るクラス
public class UserForm {

    // =========================
    // ユーザー名
    // =========================

    // ↓ 空欄チェック（未入力はNG）
    @NotBlank(message = "ユーザー名を入力してください")

    // ↓ 入力ルール（半角英字のみOK）
    // ^        → 先頭から
    // [a-zA-Z] → 英字のみ
    // +        → 1文字以上
    // $        → 最後まで
    @Pattern(
        regexp = "^[a-zA-Z]+$",
        message = "ユーザー名は半角英字で入力してください"
    )

    // ↓ 画面の「ユーザー名」がここに入る
    private String username;


    // =========================
    // パスワード
    // =========================

    // ↓ 空欄チェック
    @NotBlank(message = "パスワードを入力してください")

    // ↓ 入力ルール（半角英字 + 数字のみOK）
    // [a-zA-Z0-9] → 英字 or 数字
    @Pattern(
        regexp = "^[a-zA-Z0-9]+$",
        message = "パスワードは半角英数字で入力してください"
    )

    // ↓ 画面の「パスワード」がここに入る
    private String password;


    // =========================
    // getter（値を取り出す）
    // =========================

    // ↓ usernameの値をControllerで使うために取り出す
    public String getUsername() {
        return username;
    }

    // ↓ passwordの値を取り出す
    public String getPassword() {
        return password;
    }


    // =========================
    // setter（値を入れる）
    // =========================

    // ↓ 画面で入力されたusernameをここに入れる
    // （Springが自動で呼ぶ）
    public void setUsername(String username) {
        this.username = username;
    }

    // ↓ 画面で入力されたpasswordをここに入れる
    public void setPassword(String password) {
        this.password = password;
    }
}