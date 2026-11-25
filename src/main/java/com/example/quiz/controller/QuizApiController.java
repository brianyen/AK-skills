package com.example.quiz.controller;

import com.example.quiz.dto.CharacterSummary;
import com.example.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizApiController {

    private final QuizService quizService;

    public QuizApiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/characters")
    public List<CharacterSummary> characters() {
        return quizService.getAllCharacters();
    }
}