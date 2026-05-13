# final-attendance-app

Spring Boot と MyBatis を使用して作成したタスク管理アプリです。  
ユーザー登録・ログイン機能を実装し、ログインユーザーごとにタスクを管理できます。

---

# アプリ概要

本アプリは、Spring Boot の学習および実践を目的として作成した ToDo / タスク管理アプリです。

ユーザー認証機能を実装し、ログインしたユーザーごとにタスクを管理できる構成になっています。

CRUD処理（登録・一覧表示・編集・削除）を中心に、MyBatis を利用した DB操作や、Thymeleaf を用いた画面表示を実装しています。

---

# 主な機能

- ユーザー登録
- ログイン / ログアウト
- タスク登録
- タスク一覧表示
- タスク編集
- タスク削除
- ページング対応
- セッション管理
- バリデーション機能
- ログインチェック

---

# 使用技術

| 項目 | 内容 |
|---|---|
| 言語 | Java 17 |
| フレームワーク | Spring Boot 3.2.5 |
| テンプレートエンジン | Thymeleaf |
| DBアクセス | MyBatis 3.0.3 |
| データベース | PostgreSQL |
| ビルドツール | Gradle |
| バリデーション | Spring Validation |
| AOP | Spring AOP |
| バージョン管理 | Git / GitHub |
| IDE | Eclipse(STS) |

---

# 画面一覧

| 画面 | 内容 |
|---|---|
| TOP画面 | ログイン画面・ユーザー登録画面への遷移 |
| ログイン画面 | ユーザー認証 |
| ユーザー登録画面 | 新規ユーザー登録 |
| タスク一覧画面 | タスク一覧表示・編集・削除 |
| タスク登録画面 | 新規タスク登録 |
| タスク編集画面 | タスク更新 |

---

# API / エンドポイント

| エンドポイント | メソッド | 内容 |
|---|---|---|
| `/` | GET | TOP画面表示 |
| `/register` | GET | ユーザー登録画面表示 |
| `/register` | POST | ユーザー登録処理 |
| `/login` | GET | ログイン画面表示 |
| `/login` | POST | ログイン処理 |
| `/logout` | GET | ログアウト処理 |
| `/tasks` | GET | タスク一覧表示 |
| `/tasks/new` | GET | タスク登録画面表示 |
| `/tasks` | POST | タスク登録処理 |
| `/tasks/edit/{id}` | GET | タスク編集画面表示 |
| `/tasks/update/{id}` | POST | タスク更新処理 |
| `/tasks/delete/{id}` | POST | タスク削除処理 |

---

# DB設計

## tasks テーブル

| カラム名 | 型 | 内容 |
|---|---|---|
| id | BIGINT | タスクID |
| username | VARCHAR(50) | ユーザー名 |
| title | VARCHAR(255) | タイトル |
| content | TEXT | 内容 |
| name | VARCHAR(100) | 登録者名 |
| start_date | DATE | 開始日 |
| end_date | DATE | 終了日 |
| created_at | TIMESTAMP | 作成日時 |
| updated_at | TIMESTAMP | 更新日時 |

---

## login テーブル

| カラム名 | 型 | 内容 |
|---|---|---|
| id | BIGSERIAL | ユーザーID |
| username | VARCHAR(50) | ユーザー名 |
| password | VARCHAR(255) | パスワード |
| created_at | TIMESTAMP | 作成日時 |
| updated_at | TIMESTAMP | 更新日時 |

---

# プロジェクト構成

```text
src/main/java/com.example
 ├ FinalAttendanceAppApplication.java
 ├ HomeController.java
 ├ LoginController.java
 ├ RegisterController.java
 ├ TaskController.java
 ├ LoginCheckAspect.java
 ├ TaskService.java
 ├ TaskMapper.java
 ├ UserService.java
 ├ UserMapper.java
 ├ Task.java
 ├ User.java
 └ UserForm.java

src/main/resources
 ├ templates
 │   ├ home.html
 │   ├ login.html
 │   ├ register.html
 │   ├ register-complete.html
 │   └ tasks
 │       ├ form-new.html
 │       ├ form-edit.html
 │       └ list.html
 │
 ├ static/css
 │   ├ style.css
 │   ├ task-form.css
 │   └ tasks.css
 │
 └ application.properties
