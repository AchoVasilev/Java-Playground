package com.example.PathFinder.services.comment;

import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;

import java.util.List;

public interface ICommentService {
    CommentDisplayView createComment(CommentDto commentDto);

    List<CommentDisplayView> getAllRouteComments(Long routeId);
}
