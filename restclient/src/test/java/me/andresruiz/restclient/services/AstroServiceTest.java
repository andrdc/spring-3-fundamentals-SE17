package me.andresruiz.restclient.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AstroServiceTest {
    @Autowired
    private AstroService astroService;

    @Test
    void getAstros() {
        String json = astroService.getAstros();
        assertTrue(json.contains("people"));
        System.out.println(json);
    }

    @Test
    void getAstrosResponse() {
        AstroResponse response = astroService.getAstrosResponse();
        System.out.println(response);
        assertAll(
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
    }

    @Test
    void getAstrosResponseRT() {
        AstroResponse response = astroService.getAstrosResponseRT();
        System.out.println(response);
        assertAll(
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.number(), response.people().size()),
                () -> assertEquals("success", response.message())
        );
    }
}