package com.test.application.karpov.repositories.submission;

import com.test.application.karpov.dto.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}