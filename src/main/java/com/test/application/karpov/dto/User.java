package com.test.application.karpov.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "users_quizzes",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "quizID"))
    @ToString.Exclude
    private List<Quiz> quizzes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_answers",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "answerID"))
    @ToString.Exclude
    private List<Answer> answers;
}
