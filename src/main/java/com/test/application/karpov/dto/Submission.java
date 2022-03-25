package com.test.application.karpov.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submissions")
@Setter
@Getter
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "submission", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Answer> answers;

}