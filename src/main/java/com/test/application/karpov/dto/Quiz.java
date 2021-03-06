package com.test.application.karpov.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Question> questions;

}
