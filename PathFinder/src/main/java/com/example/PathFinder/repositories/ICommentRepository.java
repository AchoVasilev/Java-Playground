package com.example.PathFinder.repositories;

import com.example.PathFinder.models.Comment;
import com.example.PathFinder.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByRoute(Route route);
}
