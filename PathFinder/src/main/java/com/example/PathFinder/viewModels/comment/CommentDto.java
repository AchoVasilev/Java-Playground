package com.example.PathFinder.viewModels.comment;

public class CommentDto {
    private String username;
    private Long routeId;
    private String message;

    public CommentDto(String username, Long routeId, String message) {
        this.username = username;
        this.routeId = routeId;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public CommentDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getRouteId() {
        return routeId;
    }

    public CommentDto setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
