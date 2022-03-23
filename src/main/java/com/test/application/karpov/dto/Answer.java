package com.test.application.karpov.dto;

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
    private Long id;

    @Column(name = "answer_text", nullable = false)
    private String answerText;


}
