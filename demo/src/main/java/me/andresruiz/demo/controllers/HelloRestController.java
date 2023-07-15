package me.andresruiz.demo.controllers;

import me.andresruiz.demo.json.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloRestController {
    private static final Map<String, Greeting> greetings = new HashMap<>();

    static {
        greetings.put("World", new Greeting("Hello World!"));
    }

    @GetMapping("/hellorest")
    public ResponseEntity<Greeting> greet(@RequestParam(defaultValue = "World") String name) {
        Greeting greeting = greetings.get(name);
        return greeting != null ? ResponseEntity.ok(greeting) : ResponseEntity.notFound().build();
    }

    @PostMapping("/hellorest/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting postGreeting(@PathVariable String name) {
        Greeting greeting = greetings.get(name);
        if (greeting == null) {
            greeting = new Greeting("Hello " + name + "!");
            greetings.put(name, greeting);
        }
        return greeting;
    }
}