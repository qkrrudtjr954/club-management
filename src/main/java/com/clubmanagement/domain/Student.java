package com.clubmanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<ClubJoinInfo> joinedClubInfos = new ArrayList<>();

    public Student() {
    }

    @Builder
    public Student(String name, School school) {
        this.name = name;
        this.school = school;
    }

    public Integer getJoinedClubCount() {
        return joinedClubInfos.size();
    }


}
