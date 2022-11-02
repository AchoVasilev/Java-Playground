package com.example.PathFinder.services.comment;

import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;

public interface ICommentService {
    CommentDisplayView createComment(CommentDto commentDto);
}
