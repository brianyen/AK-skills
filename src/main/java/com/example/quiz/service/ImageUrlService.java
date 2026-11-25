package com.example.quiz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageUrlService {

    private final String baseUrl;

    public ImageUrlService(@Value("${app.images.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String url(String key) {
        return baseUrl + key;
    }
}