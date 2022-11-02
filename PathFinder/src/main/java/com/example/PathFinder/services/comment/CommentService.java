package com.example.PathFinder.services.comment;

import com.example.PathFinder.models.Comment;
import com.example.PathFinder.repositories.ICommentRepository;
import com.example.PathFinder.repositories.IRouteRepository;
import com.example.PathFinder.repositories.IUserRepository;
import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
