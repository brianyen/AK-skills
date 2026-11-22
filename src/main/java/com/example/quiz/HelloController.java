package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/items/{id}")
    public Map<String, Object> getItem(@PathVariable int id) {
        return Map.of("id", id, "name", "Item " + id);
    }

    @PostMapping("/items")
    public Item create(@RequestBody Item item) {
        return item;
    }

    public record Item(String name, double price) {}
}