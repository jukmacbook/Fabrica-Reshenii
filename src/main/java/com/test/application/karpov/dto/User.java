package com.test.application.karpov.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Submission> submissions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<Answer> answers;
}
