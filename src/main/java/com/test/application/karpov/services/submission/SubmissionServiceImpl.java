package com.test.application.karpov.services.submission;

import com.test.application.karpov.dto.Submission;
import com.test.application.karpov.repositories.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    @Override
    public Submission findSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Submission update(Submission newSubmission, Long id) {
        return submissionRepository.findById(id)
                .map(submission -> {
                    submission.setAnswers(newSubmission.getAnswers());
                    submission.setQuiz(newSubmission.getQuiz());
                    submission.setUser(newSubmission.getUser());
                    return save(submission);
                })
                .orElseGet(() -> {
                    newSubmission.setId(id);
                    return save(newSubmission);
                });
    }

    @Override
    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public void delete(Long id) {
        submissionRepository.deleteById(id);
    }
}
