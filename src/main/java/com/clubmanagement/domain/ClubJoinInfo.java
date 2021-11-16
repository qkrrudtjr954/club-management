package com.clubmanagement.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "club_join_info")
@Getter
@Setter
@ToString
public class ClubJoinInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    private Date joinedAt;

    @Builder
    public ClubJoinInfo(Club club, Student student) {
        this.club = club;
        this.student = student;
    }

    public ClubJoinInfo() {
    }
}
