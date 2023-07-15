package me.andresruiz.demo.controllers;

import me.andresruiz.demo.json.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTest {
    @Test
    public void greetWithName(@Autowired TestRestTemplate template) {
        template.postForEntity("/hellorest/{name}", null, Greeting.class, "Hanna");
        Greeting response = template.getForObject("/hellorest?name=Hanna", Greeting.class);
        assertEquals("Hello Hanna!", response.getMessage());
    }

    @Test
    public void greetWithoutName(@Autowired TestRestTemplate template) {
        ResponseEntity<Greeting> entity = template.getForEntity("/hellorest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if (response != null) {
            assertEquals("Hello World!", response.getMessage());
        }
    }
}