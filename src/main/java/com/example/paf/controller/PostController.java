package com.example.paf.controller;

import com.example.paf.entity.*;
import com.example.paf.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final BusinessService businessService;

    

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BusinessPost> createPost(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String author,
            @RequestParam("images") List<MultipartFile> images
    ) throws IOException {
        BusinessPost post = businessService.createPost(title, content, author, images);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BusinessPost>> getAllPosts() {
        return ResponseEntity.ok(businessService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessPost> getPost(@PathVariable Long id) {
        return businessService.getPost(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        businessService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long id,
            @RequestParam String author,
            @RequestParam String content
    ) {
        Comment comment = businessService.addComment(id, author, content);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.getComments(id));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, Integer>> likePost(
            @PathVariable Long id,
            @RequestParam String username
    ) {
        int likeCount = businessService.toggleLike(id, username);
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@RequestParam String username) {
        return ResponseEntity.ok(businessService.getNotifications(username));
    }
}
