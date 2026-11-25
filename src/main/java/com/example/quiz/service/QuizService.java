package com.example.quiz.service;

import com.example.quiz.dto.CharacterSummary;
import com.example.quiz.model.GameCharacter;
import com.example.quiz.repository.GameCharacterRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final GameCharacterRepository characterRepo;
    private final ImageUrlService imageUrls;

    public QuizService(GameCharacterRepository characterRepo, ImageUrlService imageUrls) {
        this.characterRepo = characterRepo;
        this.imageUrls = imageUrls;
    }

    @Transactional(readOnly = true)
    public List<CharacterSummary> getAllCharacters() {
        List<GameCharacter> charList = characterRepo.findAll(Sort.by("name"));
        List<CharacterSummary> out = new ArrayList<>();
        for (GameCharacter c : charList) {
            out.add(new CharacterSummary(c.getId(), c.getName(), imageUrls.url(c.getPortrait())));
        }
        return out;
    }
}