package com.example.quiz.data;

import java.util.List;

public record SkillJson(
        String id,
        String name,
        String description,
        String type,
        String activation,
        String rangeExtension,
        String icon,
        List<String> characters,
        List<Integer> attributes
) {}