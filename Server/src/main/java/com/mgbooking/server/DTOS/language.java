package com.mgbooking.server.DTOS;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class language {
    private final String langCode;
    private  final Map<String,String>translations=new HashMap<>();
    public language(String langCode) {
        this.langCode = langCode;
    }
    public void loadTranslations(String filepath){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filepath));
            jsonNode.fields().forEachRemaining(entry -> translations.put(entry.getKey(), entry.getValue().asText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getTranslation(String key){
        return translations.getOrDefault(key,key);
    }
    public String getLangCode(){
        return langCode;
    }
}
