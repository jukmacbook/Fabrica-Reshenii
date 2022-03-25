package com.test.application.karpov.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerID", nullable = false)
    private Long answerID;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_ID")
    @JsonBackReference
    private Submission submission;

}
