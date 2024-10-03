package com.ndn.comment_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ndn.comment_service.model.Comment;
import com.ndn.comment_service.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Comment createComment(Comment comment) {
        // Check if the post exists
        ResponseEntity<String> postResponse = restTemplate.getForEntity(
            "http://POST-SERVICE/posts/getById/" + comment.getPostId(),
            String.class
        );

        // Check if the user exists
        ResponseEntity<String> userResponse = restTemplate.getForEntity(
            "http://USER-SERVICE/users/getById/" + comment.getUserId(),
            String.class
        );

        // If both the post and user exist, save the comment
        if (postResponse.getStatusCode().is2xxSuccessful() && userResponse.getStatusCode().is2xxSuccessful()) {
            return commentRepository.save(comment);
        }
        return null; // Handle the case when the post or user doesn't exist
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
