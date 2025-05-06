package com.example.paf.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_likes", uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "username"}))
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private BusinessPost post;

    public void setPost(BusinessPost post) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
