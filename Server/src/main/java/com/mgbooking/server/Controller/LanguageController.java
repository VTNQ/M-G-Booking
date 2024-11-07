package com.mgbooking.server.Controller;

import com.mgbooking.server.DTOS.LanguageManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/Language"})
@CrossOrigin(origins = "http://localhost:8386")
public class LanguageController {
    private final LanguageManager languageManager;

    public LanguageController(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

    @GetMapping("translate")
    public ResponseEntity<String> translate(
            @RequestParam String langCode,
            @RequestParam String key
    ) {
        String translation = languageManager.translate(langCode, key);
        return ResponseEntity.ok(translation);
    }
}
