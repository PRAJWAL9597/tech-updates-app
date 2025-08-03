package com.notmine.tech_updates_app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Hash a raw password
    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // Compare a raw password to a hashed one
    public static boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
