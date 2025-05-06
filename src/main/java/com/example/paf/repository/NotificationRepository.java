package com.example.paf.repository;

import com.example.paf.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientUserOrderByCreatedAtDesc(String recipientUser);
}
