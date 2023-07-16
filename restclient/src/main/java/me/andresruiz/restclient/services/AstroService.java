package me.andresruiz.restclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Service
public class AstroService {
    private final WebClient client = WebClient.create("http://api.open-notify.org");
    private final RestTemplate template;

    @Autowired
    public AstroService(RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    public String getAstros() {
        return client.get().uri("astros.json").retrieve().bodyToMono(String.class).block();
    }

    public AstroResponse getAstrosResponse() {
        return client.get()
                .uri("astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstroResponse.class)
                .block(Duration.ofSeconds(2));
    }

    public AstroResponse getAstrosResponseRT() {
        return template.getForObject("http://api.open-notify.org/astros.json", AstroResponse.class);
    }
}

record AstroResponse(String message, int number, List<Assignment> people) {
    record Assignment(String name, String craft) {}
}