package com.ben.repo;


import com.ben.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, Long> {
    History findHistoriesByUserId(Long userId);
}
