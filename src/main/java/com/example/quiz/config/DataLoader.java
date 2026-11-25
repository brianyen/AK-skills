package com.example.quiz.config;

import com.example.quiz.data.CharacterJson;
import com.example.quiz.data.SkillJson;
import com.example.quiz.model.GameCharacter;
import com.example.quiz.model.Skill;
import com.example.quiz.repository.GameCharacterRepository;
import com.example.quiz.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final ObjectMapper mapper;
    private final GameCharacterRepository characterRepo;
    private final SkillRepository skillRepo;

    public DataLoader(ObjectMapper mapper, GameCharacterRepository characterRepo, SkillRepository skillRepo) {
        this.mapper = mapper;
        this.characterRepo = characterRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws IOException {
        List<CharacterJson> characterData = read("data/characters.json", new TypeReference<>() {});
        List<GameCharacter> characters = new ArrayList<>();
        for (CharacterJson c : characterData) {
            characters.add(new GameCharacter(c.id(), c.name(), c.id()));
        }
        List<GameCharacter> saved = characterRepo.saveAll(characters);

        Map<String, GameCharacter> charactersById = new HashMap<>();
        for (GameCharacter c : saved) {
            charactersById.put(c.getId(), c);
        }

        List<SkillJson> skillData = read("data/skills.json", new TypeReference<>() {});
        List<Skill> skills = new ArrayList<>();
        for (SkillJson s : skillData) {
            Skill skill = new Skill(
                    s.id(),
                    s.name(),
                    s.description(),
                    s.type(),
                    s.activation(),
                    s.rangeExtension(),
                    s.icon(),
                    s.attributes().get(0),
                    s.attributes().get(1),
                    s.attributes().get(2)
            );
            for (String characterId : s.characters()) {
                GameCharacter c = charactersById.get(characterId);
                if (c == null) {
                    throw new IllegalStateException(
                            "Skill '" + s.id() + "' references unknown character '" + characterId + "'");
                }
                skill.getCharacters().add(c);
            }
            skills.add(skill);
        }
        skillRepo.saveAll(skills);

        log.info("INFO: Loaded {} characters, {} skills", characterRepo.count(), skillRepo.count());
    }

    private <T> T read(String path, TypeReference<T> type) throws IOException {
        try (InputStream in = new ClassPathResource(path).getInputStream()) {
            return mapper.readValue(in, type);
        }
    }
}