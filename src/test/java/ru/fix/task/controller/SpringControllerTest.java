package ru.fix.task.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringRestController.class})
@AutoConfigureMockMvc
public class SpringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMove1() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "A3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    public void testMove2() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "8")
                .param("height", "8")
                .param("start", "A1")
                .param("end", "H8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6")));
    }

    @Test
    public void testMove3() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "8")
                .param("height", "8")
                .param("start", "A1")
                .param("end", "A1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    public void testWidthLessThanThree() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "2")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid board width/height parameters")));
    }

    @Test
    public void testNegativeWidth() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "-2")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid board width/height parameters")));
    }

    @Test
    public void testHeightLessThanThree() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "2")
                .param("start", "B1")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid board width/height parameters")));
    }

    @Test
    public void testNegativeHeight() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "-2")
                .param("start", "B1")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid board width/height parameters")));
    }

    @Test
    public void testStartNotMatchPattern() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "48!3_")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid start parameter")));
    }

    @Test
    public void testStartMoreThanWidth() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "Z1")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid start parameter")));
    }

    @Test
    public void testStartMoreThanHeight() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "B15")
                .param("end", "A3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid start parameter")));
    }

    @Test
    public void testEndNotMatchPattern() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "DD2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid end parameter")));
    }

    @Test
    public void testEndMoreThanWidth() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "Z3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid end parameter")));
    }

    @Test
    public void testEndMoreThanHeight() throws Exception {
        this.mockMvc.perform(get("/horse/rest/count/")
                .param("width", "10")
                .param("height", "14")
                .param("start", "B1")
                .param("end", "A15"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not valid end parameter")));
    }

}
