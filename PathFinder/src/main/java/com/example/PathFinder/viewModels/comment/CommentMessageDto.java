package com.example.PathFinder.viewModels.comment;

public class CommentMessageDto {
    private String message;

    public CommentMessageDto() {}

    public CommentMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public CommentMessageDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
