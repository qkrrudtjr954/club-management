package com.clubmanagement.dto;

import com.clubmanagement.domain.Club;
import com.clubmanagement.domain.ClubJoinInfo;
import lombok.Builder;

import java.util.Date;

public class ClubDto {
    private final Club club;
    private final ClubJoinInfo clubJoinInfo;

    @Builder
    public ClubDto(Club club, ClubJoinInfo clubJoinInfo) {
        this.club = club;
        this.clubJoinInfo = clubJoinInfo;
    }


    public Long getId() {
        return club.getId();
    }

    public String getName() {
        return club.getName();
    }

    public Club getClub() {
        return club;
    }

    public Date getJoinedAt() {
        return clubJoinInfo.getJoinedAt();
    }
}
