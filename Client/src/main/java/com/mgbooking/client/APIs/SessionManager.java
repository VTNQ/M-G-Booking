package com.mgbooking.client.APIs;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SessionManager {
    private List<Cookie> sessionCookies = new CopyOnWriteArrayList<>();

    // Save session cookies
    public void saveSessionCookies(HttpUrl url, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            // Save only session cookies (for example, JSESSIONID)
            if ("JSESSIONID".equals(cookie.name())) {
                sessionCookies.add(cookie);
            }
        }
    }

    // Retrieve session cookies
    public List<Cookie> getSessionCookies() {
        return sessionCookies;  // Return saved session cookies
    }

    // Clear session cookies (e.g., on logout)
    public void clearSessionCookies() {
        sessionCookies.clear();
    }
}
