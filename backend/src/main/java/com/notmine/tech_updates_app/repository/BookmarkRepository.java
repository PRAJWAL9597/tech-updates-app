package com.notmine.tech_updates_app.repository;

import com.notmine.tech_updates_app.model.Bookmark;
import com.notmine.tech_updates_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser(User user);
}
