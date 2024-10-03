package com.ndn.post_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndn.post_service.model.Post;
import com.ndn.post_service.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post resp =  postService.createPost(post);
        System.out.println("resp "+resp);
        if(resp==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> list = postService.getAllPosts();
        if(list==null || list.size()==0){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post resp =  postService.findPostById(id);
        if(resp==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
}
