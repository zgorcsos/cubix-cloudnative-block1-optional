package hu.cubix.cloud.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerIT {

    @Autowired
    private MockMvc mvc;

    @Value("${default-message}")
    private String defaultMessage;

    @Test
    void demoDefaultMessage() throws Exception {
        mvc.perform(get("/demo/message"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(createDateJsonPathMatcher())
                .andExpect(createTimeJsonPathMatcher())
                .andExpect(createMessageJsonPathMatcher(defaultMessage));
    }

    @Test
    void demoMessage() throws Exception {
        var message = "hello";
        mvc.perform(get("/demo/message").queryParam("message", message))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(createDateJsonPathMatcher())
                .andExpect(createTimeJsonPathMatcher())
                .andExpect(createMessageJsonPathMatcher(message));
    }

    private ResultMatcher createDateJsonPathMatcher() {
        return MockMvcResultMatchers.jsonPath("$.date", matchesRegex("\\d{4}-\\d{2}-\\d{2}"));
    }

    private static ResultMatcher createTimeJsonPathMatcher() {
        return MockMvcResultMatchers.jsonPath("$.time", matchesRegex("\\d{2}:\\d{2}:\\d{2}\\.\\d*"));
    }

    private ResultMatcher createMessageJsonPathMatcher(String message) {
        return MockMvcResultMatchers.jsonPath("$.message", is(message));
    }
}