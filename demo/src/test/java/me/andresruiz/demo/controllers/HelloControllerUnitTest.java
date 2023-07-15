package me.andresruiz.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerUnitTest {
    @Test
    void sayHello() {
        HelloController helloController = new HelloController();
        Model model = new BindingAwareConcurrentModel();
        String result = helloController.sayHello("Hanna", model);
        assertEquals("welcome", result);
        assertEquals("Hanna", model.asMap().get("user"));
    }
}