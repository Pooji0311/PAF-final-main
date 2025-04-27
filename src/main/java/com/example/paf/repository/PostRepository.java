package com.example.paf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.paf.entity.BusinessPost;

@Repository
public interface PostRepository extends JpaRepository<BusinessPost, Long> {
}
