package com.example.quiz.repository;

import com.example.quiz.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, String> {}