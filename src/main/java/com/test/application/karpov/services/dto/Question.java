package com.test.application.karpov.services.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionID", nullable = false)
    private Long id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "question_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quizID", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private Set<Answer> answers;

}
