package ru.fix.task.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fix.task.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServletControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testMove1() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=B1&end=A3",
                String.class)).contains("1");
    }

    @Test
    public void testMove2() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=A1&end=h8",
                String.class)).contains("6");
    }

    @Test
    public void testMove3() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=8&height=8&start=A1&end=A1",
                String.class)).contains("0");
    }

    @Test
    public void testWidthLessThanThree() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=2&height=14&start=B1&end=A3",
                String.class)).contains("Invalid board width/height parameters");
    }

    @Test
    public void testNegativeWidth() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=-2&height=14&start=B1&end=A3",
                String.class)).contains("Invalid board width/height parameters");
    }

    @Test
    public void testHeightLessThanThree() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=2&start=B1&end=A3",
                String.class)).contains("Invalid board width/height parameters");
    }

    @Test
    public void testNegativeHeight() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=-2&start=B1&end=A3",
                String.class)).contains("Invalid board width/height parameters");
    }

    @Test
    public void testStartNotMatchPattern() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=48!3_&end=A3",
                String.class)).contains("Not valid start parameter");
    }

    @Test
    public void testStartMoreThanWidth() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=Z1&end=A3",
                String.class)).contains("Not valid start parameter");
    }

    @Test
    public void testStartMoreThanHeight() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=B15&end=A3",
                String.class)).contains("Not valid start parameter");
    }

    @Test
    public void testEndNotMatchPattern() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=B1&end=DD2",
                String.class)).contains("Not valid end parameter");
    }

    @Test
    public void testEndMoreThanWidth() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=B1&end=Z3",
                String.class)).contains("Not valid end parameter");
    }

    @Test
    public void testEndMoreThanHeight() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/horse/servlet/count?width=10&height=14&start=B1&end=A15",
                String.class)).contains("Not valid end parameter");
    }
}
