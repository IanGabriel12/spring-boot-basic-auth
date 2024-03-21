package com.example.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ResourceController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> unsecureEndpoint() {
        return ResponseEntity.ok().body("Unsecure hello world");
    }
}
