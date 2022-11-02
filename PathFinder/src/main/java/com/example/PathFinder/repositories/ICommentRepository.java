package com.example.PathFinder.repositories;

import com.example.PathFinder.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
