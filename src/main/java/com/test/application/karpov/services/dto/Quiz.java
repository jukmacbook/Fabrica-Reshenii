package com.test.application.karpov.services.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizID", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false, updatable = false)
    private Date startDate;

    @Column(name = "stop_date", nullable = false)
    private Date stopDate;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "users_quizzes",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "quizID"))
    @JsonManagedReference
    private Set<User> users;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private Set<Question> questions;

}
