package com.example.PathFinder.services.comment;

import com.example.PathFinder.exceptions.RouteNotFoundException;
import com.example.PathFinder.models.Comment;
import com.example.PathFinder.repositories.ICommentRepository;
import com.example.PathFinder.repositories.IRouteRepository;
import com.example.PathFinder.repositories.IUserRepository;
import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {
    private final IRouteRepository routeRepository;
    private final IUserRepository userRepository;
    private final ICommentRepository commentRepository;

    public CommentService(IRouteRepository routeRepository, IUserRepository userRepository, ICommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDisplayView createComment(CommentDto commentDto) {
        var comment = new Comment();

        var author = this.userRepository.findByUsername(commentDto.getUsername()).get();
        comment.setCreated(LocalDateTime.now())
                .setRoute(this.routeRepository.getReferenceById(commentDto.getRouteId()))
                .setAuthor(author)
                .setApproved(true)
                .setText(commentDto.getMessage());

        this.commentRepository.save(comment);

        var result = new CommentDisplayView(comment.getId(), author.getUsername(), comment.getText());

        return result;
    }

    public List<CommentDisplayView> getAllRouteComments(Long routeId){
        var route = this.routeRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);

        var comments = this.commentRepository.findAllByRoute(route)
                .get()
                .stream()
                .map(comment -> new CommentDisplayView(
                        comment.getId(),
                        comment.getAuthor().getFullName(),
                        comment.getText()
                ))
                .collect(Collectors.toList());

        return comments;
    }
}
