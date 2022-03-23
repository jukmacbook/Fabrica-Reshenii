package com.test.application.karpov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quizID", nullable = false)
    private Quiz quiz;

    @OneToMany
    @JoinTable(name = "answer",
            joinColumns = @JoinColumn(name = "answerID"))
    private Set<Answer> answers;

}
