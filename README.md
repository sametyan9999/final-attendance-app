# Todo Application

Spring Boot と MyBatis を使用して作成したタスク管理アプリです。  
ユーザー登録・ログイン機能を実装し、ログインユーザーごとにタスクを管理できます。

---

# アプリ概要

本アプリは、Spring Boot の学習および実践を目的として作成した ToDo / タスク管理アプリです。

ユーザー認証機能を実装し、ログインしたユーザーごとにタスクを管理できる構成になっています。

CRUD処理（登録・一覧表示・編集・削除）を中心に、MyBatis を利用した DB操作や、Thymeleaf を用いた画面表示を実装しています。

また、Interceptor を利用したログインチェック機能や、Spring Validation による入力チェックも実装しています。

---

# 主な機能

- ユーザー登録
- ログイン / ログアウト
- タスク登録
- タスク一覧表示
- タスク編集
- タスク削除
- ページング機能
- セッション管理
- バリデーション機能
- ログインチェック
- タスク入力時の必須チェック
- 開始日・終了日の整合性チェック

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
| ログイン制御 | Interceptor |
| バージョン管理 | Git / GitHub |
| IDE | Eclipse(STS) |

---

# 画面一覧

| 画面 | 内容 |
|---|---|
| TOP画面 | ログイン画面・ユーザー登録画面への遷移 |
| ログイン画面 | ユーザー認証 |
| ユーザー登録画面 | 新規ユーザー登録 |
| タスク一覧画面 | タスク一覧表示 |
| タスク登録画面 | 新規タスク登録 |
| タスク編集画面 | タスク更新 |

---

# エンドポイント

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
| username | VARCHAR(50) | ユーザー名（FK） |
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
| username | VARCHAR(50) | ユーザー名（UNIQUE） |
| password | VARCHAR(255) | パスワード |

---

# プロジェクト構成

```text
src/main/java
├ sample
│ ├ TodoApplication.java
│ │
│ ├ common
│ │ ├ config
│ │ │ ├ PasswordConfig.java
│ │ │ ├ SecurityConfig.java
│ │ │ └ WebConfig.java
│ │ │
│ │ ├ dao.entity
│ │ │ ├ Login.java
│ │ │ └ Task.java
│ │ │
│ │ ├ dao.mapper
│ │ │ ├ LoginMapper.java
│ │ │ └ TaskMapper.java
│ │ │
│ │ ├ exception
│ │ │ ├ GlobalExceptionHandler.java
│ │ │ └ TaskNotFoundException.java
│ │ │
│ │ ├ interceptor
│ │ │ └ LoginRequiredInterceptor.java
│ │ │
│ │ └ service
│ │   ├ LoginService.java
│ │   └ TaskService.java
│ │
│ ├ thymeleaf.form
│ │ ├ UserForm.java
│ │ └ TaskForm.java
│ │
│ └ thymeleaf.web
│   ├ HomeController.java
│   ├ LoginController.java
│   ├ RegisterController.java
│   └ TaskController.java

src/main/resources
├ error
│ ├ 400.html
│ ├ 404.html
│ └ 500.html
│
├ templates
│ ├ homePage.html
│ ├ login.html
│ ├ register.html
│ ├ register-complete.html
│ └ tasks
│   ├ form-new.html
│   ├ form-edit.html
│   └ list.html
│
├ static/css
│ ├ style.css
│ ├ task-form.css
│ └ tasks.css
│
├ schema.sql
└ application.properties
```

---

# application.properties

```properties
# Thymeleaf設定
spring.thymeleaf.mode=HTML
spring.thymeleaf.check-template-location=false

# PostgreSQL接続設定
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_app
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:}

# schema.sql 自動実行
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql

# ログレベル
logging.level.sample=DEBUG

# セッション有効期限
server.servlet.session.timeout=1d
```

---

# build.gradle

```gradle
plugins {
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.5'
    id 'java'
}

group = 'sample'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'

    runtimeOnly 'org.postgresql:postgresql'

    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-aop'

    implementation 'org.springframework.security:spring-security-crypto'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

---

# 実行方法

## テスト用ログイン情報

動作確認用に以下のユーザーを使用できます。

| ユーザー名 | パスワード |
|---|---|
| testuser | test123 |

## 1. PostgreSQLでDB作成

```sql
CREATE DATABASE todo_app;
```

---

## 2. DBパスワード設定

macOS / zsh の場合：

```bash
export DB_PASSWORD=your_password
```

---

## 3. アプリ起動

```bash
./gradlew bootRun
```

---

## 4. ブラウザでアクセス

```text
http://localhost:8080
```

---

# schema.sql

schema.sql により、
Spring Boot 起動時にテーブルを自動生成しています。

- login テーブル
- tasks テーブル
- login.username の UNIQUE 制約
- tasks.username の外部キー制約
- tasks(username, id) の INDEX
- 開始日 <= 終了日の CHECK 制約

などを定義しています。
