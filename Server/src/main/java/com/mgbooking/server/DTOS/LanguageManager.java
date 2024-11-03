package com.mgbooking.server.DTOS;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LanguageManager {
    private final Map<String, language> languages = new HashMap<>();
    private language currentLanguage;
    private final ResourceLoader resourceLoader;

    public LanguageManager(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        loadLanguages("en");
        loadLanguages("vi");

    }
    private void loadLanguages(String langCode) {
        try {
            Resource resource = resourceLoader.getResource("classpath:lang/" + langCode + ".json");
            language lang = new language(langCode);
            lang.loadTranslations(resource.getFile().getPath());
            languages.put(langCode, lang);
            System.out.println("Loaded language " + languages);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }


    public String translate(String langCode, String key) {
        language lang = languages.getOrDefault(langCode, currentLanguage); // Use currentLanguage as fallback
        if (lang != null) {
            return lang.getTranslation(key); // Get the translation for the provided key
        }
        return "Translation not found";
    }
}
