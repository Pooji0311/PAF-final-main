package com.example.paf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.paf.entity.BusinessPost;
import com.example.paf.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final PostRepository postRepository;

    public BusinessPost createPost(BusinessPost post) {
        return postRepository.save(post);
    }

    public List<BusinessPost> getAllPosts() {
        return postRepository.findAll();
    }

    public BusinessPost updatePost(Long id, BusinessPost updatedPost) {
        return postRepository.findById(id)
            .map(post -> {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                return postRepository.save(post);
            }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
