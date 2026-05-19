CREATE TABLE IF NOT EXISTS login (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL REFERENCES login(username) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT chk_dates CHECK (start_date <= end_date)
);

CREATE INDEX IF NOT EXISTS idx_tasks_username_id
ON tasks (username, id DESC);