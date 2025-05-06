package com.example.paf.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_posts")
public class BusinessPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;

    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    private int likeCount = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void setTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setContent(String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setImageUrls(List<String> imageUrls) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAuthor(String author) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLikeCount(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getAuthor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
