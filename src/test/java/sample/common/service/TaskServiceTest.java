package sample.common.service;

import static org.mockito.Mockito.verify;

import java.time.Clock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sample.common.dao.mapper.TaskMapper;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private Clock clock;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findByUsername_pageが1ならoffsetは0() {

        taskService.findByUsername(
                "alice",
                1,
                TaskService.PAGE_SIZE
        );

        verify(taskMapper).findByUsername(
                "alice",
                10,
                0
        );
    }

    @Test
    void findByUsername_pageが3ならoffsetは20() {

        taskService.findByUsername(
                "alice",
                3,
                TaskService.PAGE_SIZE
        );

        verify(taskMapper).findByUsername(
                "alice",
                10,
                20
        );
    }
}