package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.Article;
import com.notmine.tech_updates_app.model.Bookmark;
import com.notmine.tech_updates_app.model.User;
import com.notmine.tech_updates_app.repository.ArticleRepository;
import com.notmine.tech_updates_app.repository.BookmarkRepository;
import com.notmine.tech_updates_app.repository.UserRepository;
import com.notmine.tech_updates_app.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    // Registration endpoint hashes the password before saving
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }
        // Hash password before saving
        String hashedPassword = PasswordUtil.hashPassword(newUser.getPasswordHash());
        newUser.setPasswordHash(hashedPassword);

        User savedUser = userRepository.save(newUser);

        // Remove password hash before returning
        savedUser.setPasswordHash(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Login endpoint verifies password hash
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        User user = userOpt.get();

        // Check password hash match
        if (PasswordUtil.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Add a bookmark for a user
    @PostMapping("/{userId}/bookmarks/{articleId}")
    public ResponseEntity<?> addBookmark(@PathVariable Long userId, @PathVariable Long articleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Article> articleOpt = articleRepository.findById(articleId);

        if (userOpt.isEmpty() || articleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Article not found.");
        }

        User user = userOpt.get();
        Article article = articleOpt.get();

        // Avoid duplicate bookmarks
        boolean exists = bookmarkRepository.findByUser(user)
                .stream()
               .anyMatch(b -> b.getArticle().getId() == articleId.longValue());


        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bookmark already exists.");
        }

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setArticle(article);
        bookmarkRepository.save(bookmark);

        return ResponseEntity.ok("Article bookmarked successfully.");
    }

    // Get all bookmarks for a user
    @GetMapping("/{userId}/bookmarks")
    public ResponseEntity<?> getBookmarks(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        List<Bookmark> bookmarks = bookmarkRepository.findByUser(userOpt.get());
        List<Article> bookmarkedArticles = bookmarks.stream()
                .map(Bookmark::getArticle)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookmarkedArticles);
    }
}

// DTO for login request
class LoginRequest {
    private String email;
    private String password;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
