package com.clubmanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club")
@Getter
@Setter
@ToString
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "club")
    @ToString.Exclude
    private List<ClubJoinInfo> memberInfo = new ArrayList<>();

    @Builder
    public Club(String name, School school) {
        this.name = name;
        this.school = school;
    }

    public Club() {
    }

    public Integer getMemberCount() {
        return memberInfo.size();
    }
}
