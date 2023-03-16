package org.aston.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HelloController {
    @GetMapping(value = "/hello", produces = APPLICATION_JSON_VALUE)
    public String hello() {
        return """
                {
                    "data": {
                        "message": "Hello controller",
                        "test-message": "Hello controller10",
                    }
                }
                """;
    }
}
