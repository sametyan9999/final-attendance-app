package sample.common.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;

import sample.common.dao.entity.Task;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void findByUsernameでタスク一覧取得できる() {

        Task task = new Task();

        task.setUsername("alice");
        task.setTitle("テストタイトル");
        task.setContent("テスト内容");
        task.setName("管理者");
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        taskMapper.insert(task);

        List<Task> tasks = taskMapper.findByUsername(
                "alice",
                10,
                0
        );

        assertEquals(1, tasks.size());
        assertEquals("テストタイトル", tasks.get(0).getTitle());
    }
}