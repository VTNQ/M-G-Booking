package com.mgbooking.server.Services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlackListService {
    private final Set<String>blackListedTokens=new HashSet<>();
    public void blackListTokens(String token) {
        blackListedTokens.add(token);
    }
    public boolean isBlackListed(String token) {
        return blackListedTokens.contains(token);
    }
}
