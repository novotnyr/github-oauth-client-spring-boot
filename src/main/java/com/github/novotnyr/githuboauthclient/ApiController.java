package com.github.novotnyr.githuboauthclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final WebClient webClient;

    public ApiController(@Qualifier("github") WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/")
    public Map<String, String> getVersion() {
        return Map.of("version", "1.0");
    }

    @GetMapping("/repositories")
    public List<Repository> getRepositories() {
        String url = "https://api.github.com/user/repos?type=owner&since=2023-01-01T00:00:00Z";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Repository.class)
                .collectList()
                .block();
    }
}
