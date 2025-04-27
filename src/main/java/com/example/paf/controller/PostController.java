package com.example.paf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.paf.entity.BusinessPost;
import com.example.paf.service.BusinessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final BusinessService businessService = null;

    @PostMapping
    public ResponseEntity<BusinessPost> createPost(@RequestBody BusinessPost post) {
        return new ResponseEntity<>(businessService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BusinessPost>> getAllPosts() {
        return ResponseEntity.ok(businessService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessPost> updatePost(@PathVariable Long id, @RequestBody BusinessPost post) {
        return ResponseEntity.ok(businessService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        businessService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
