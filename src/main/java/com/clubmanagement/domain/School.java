package com.clubmanagement.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "school")
@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "school")
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @Builder
    public School(String name) {
        this.name = name;
    }
}
