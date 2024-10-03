package com.ndn.post_service.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ndn.post_service.model.Post;
import com.ndn.post_service.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Post createPost(Post post) {
        // Check user with userId using another microservice
        ResponseEntity<String> resp = restTemplate.getForEntity(
            "http://USER-SERVICE/users/getById/" + post.getUserId(), 
            String.class
        );
        if (resp.getStatusCode() == HttpStatus.OK) {
            return postRepository.save(post);
        }
        return null;
    }    

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
