package com.example.paf.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientUser;
    private String actorUser;
    private String type; // "LIKE" or "COMMENT"
    private Long postId;
    private String message;
    private boolean read = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setRecipientUser(Object author) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setActorUser(String author) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setType(String comment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPostId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMessage(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
