package com.test.application.karpov.services.submission;

import com.test.application.karpov.dto.Submission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubmissionService {

    List<Submission> findAll();

    Submission findSubmissionById(Long id);

    Submission update(Submission submission, Long id);

    Submission save(Submission submission);

    void delete(Long id);

}
