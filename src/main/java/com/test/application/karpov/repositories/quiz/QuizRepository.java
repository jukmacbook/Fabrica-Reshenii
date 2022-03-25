package com.test.application.karpov.repositories.quiz;

import com.test.application.karpov.services.dto.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
