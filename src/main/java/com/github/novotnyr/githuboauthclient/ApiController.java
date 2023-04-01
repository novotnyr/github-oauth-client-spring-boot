package com.github.novotnyr.githuboauthclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/resources")
    public Map<String, String> getVersion() {
        return Map.of("version", "1.0");
    }
}
