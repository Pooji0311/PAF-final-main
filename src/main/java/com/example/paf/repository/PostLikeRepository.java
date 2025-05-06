package com.example.paf.repository;

import com.example.paf.entity.PostLike;
import com.example.paf.entity.BusinessPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndUsername(BusinessPost post, String username);
    int countByPost(BusinessPost post);
}
