package sample.thymeleaf.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import sample.common.service.LoginService;

@WebMvcTest(
        controllers = LoginController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void login画面が正常表示される() throws Exception {

        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"));
    }

    @Test
    void ログイン画面で未入力ならエラーになる() throws Exception {

        mockMvc.perform(post("/login")
                .param("username", "")
                .param("password", ""))
               .andExpect(status().isOk())
               .andExpect(model().hasErrors())
               .andExpect(view().name("login"));
    }
}