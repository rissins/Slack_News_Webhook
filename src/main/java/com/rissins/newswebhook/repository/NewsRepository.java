package com.rissins.newswebhook.repository;

import com.rissins.newswebhook.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findTopByOrderByIdDesc();
}
