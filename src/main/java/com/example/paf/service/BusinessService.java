package com.example.paf.service;

import com.example.paf.entity.*;
import com.example.paf.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final NotificationRepository notificationRepository;

    private final String uploadDir = "src/main/resources/static/uploads/";

    public BusinessPost createPost(String title, String content, String author, List<MultipartFile> images) throws IOException {
        if (images.size() > 3) throw new IllegalArgumentException("Maximum 3 images allowed.");
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : images) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            imageUrls.add("/uploads/" + fileName);
        }
        BusinessPost post = new BusinessPost();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setImageUrls(imageUrls);
        post.setLikeCount(0);
        return postRepository.save(post);
    }

    public List<BusinessPost> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<BusinessPost> getPost(Long id) {
        return postRepository.findById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Comment addComment(Long postId, String author, String content) {
        BusinessPost post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        // Notification for comment
        if (!author.equals(post.getAuthor())) {
            Notification notification = new Notification();
            notification.setRecipientUser(post.getAuthor());
            notification.setActorUser(author);
            notification.setType("COMMENT");
            notification.setPostId(post.getId());
            notification.setMessage(author + " commented on your post.");
            notificationRepository.save(notification);
        }
        return savedComment;
    }

    public List<Comment> getComments(Long postId) {
        BusinessPost post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getComments();
    }

    public int toggleLike(Long postId, String username) {
        BusinessPost post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Optional<PostLike> existingLike = postLikeRepository.findByPostAndUsername(post, username);
        if (existingLike.isPresent()) {
            postLikeRepository.delete(existingLike.get());
        } else {
            PostLike like = new PostLike();
            like.setPost(post);
            like.setUsername(username);
            postLikeRepository.save(like);

            // Notification for like
            if (!username.equals(post.getAuthor())) {
                Notification notification = new Notification();
                notification.setRecipientUser(post.getAuthor());
                notification.setActorUser(username);
                notification.setType("LIKE");
                notification.setPostId(post.getId());
                notification.setMessage(username + " liked your post.");
                notificationRepository.save(notification);
            }
        }
        int likeCount = postLikeRepository.countByPost(post);
        post.setLikeCount(likeCount);
        postRepository.save(post);
        return likeCount;
    }

    public List<Notification> getNotifications(String username) {
        return notificationRepository.findByRecipientUserOrderByCreatedAtDesc(username);
    }
}
