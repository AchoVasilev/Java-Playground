package com.example.PathFinder.web.rest;

import com.example.PathFinder.exceptions.RouteNotFoundException;
import com.example.PathFinder.services.comment.ErrorApiResponse;
import com.example.PathFinder.services.comment.ICommentService;
import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;
import com.example.PathFinder.viewModels.comment.CommentMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsApiController {
    private final ICommentService commentService;

    public CommentsApiController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{routeId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("routeId") Long routeId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDto commentMessageDto) {
        var commentCreationDto = new CommentDto(userDetails.getUsername(), routeId, commentMessageDto.getMessage());

        var result = this.commentService.createComment(commentCreationDto);

        return ResponseEntity.created(URI.create(String.format("/api/%d/comments/%d",routeId, result.getId())))
                .body(result);
    }

    @GetMapping("/{routeId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("routeId") Long routeId) {
        return ResponseEntity.ok(this.commentService.getAllRouteComments(routeId));
    }


    @ExceptionHandler({RouteNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleRouteNotFound() {
        return ResponseEntity.status(404)
                .body(new ErrorApiResponse("Such route doesn't exist", 1004));
    }
}
