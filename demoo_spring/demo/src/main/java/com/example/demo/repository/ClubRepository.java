package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Club;
import com.example.demo.domain.Student;

@Repository
public class ClubRepository {
    private ArrayList<Club> clubs = new ArrayList<>();

    public ClubRepository() {

        String soccerDescription =
                
                "- 축구를 좋아하는 광운대학교 학생 누구나\n\n" +

                "- 주 2회 풋살 및 교내 친선 경기 진행\n\n" +

                "- 실력보다 즐겁게 참여하는 분위기\n\n" +

                "- 교내 대회 참가 및 MT 진행\n\n" +

                "- 회비 : 월 20000원\n\n";

        String basketballDescription =
               
                "- 농구를 좋아하는 학생들이 함께 운동하는 동아리입니다.\n\n" +

                "- 농구에 관심 있는 학생 누구나\n\n" +

                "- 주 2회 교내 농구 친선 경기 및 연습\n\n" +

                "- 대학부 교내 농구 대회 참가\n\n" +

                "- 활발하고 친목 중심 분위기\n\n" +

                "- 회비 : 월 20000원\n\n";

        String gameDescription =
                
                "- 게임을 좋아하는 학생들이 함께 즐기고 교류하는 동아리입니다.\n\n" +

                "- 매주 화요일, 목요일 정기모임 진행\n\n" +

                "- 게임룸을 대여해 PC, 보드게임 등 다양한 활동 진행\n\n" +

                "- 편하고 자유로운 분위기\n\n" +

                "- 회비 : 월 15000원\n\n";

        Club soccer = new Club("축구", "1111", soccerDescription);
        soccer.addMember(new Student("회장", "20240001", "축구회장"));
        soccer.addMember(new Student("부회장", "20240002", "축구부회장"));

        Club basketball = new Club("농구", "2222", basketballDescription);
        basketball.addMember(new Student("회장", "20240003", "농구회장"));
        basketball.addMember(new Student("부회장", "20240004", "농구부회장"));

        Club game = new Club("보드게임", "3333", gameDescription);
        game.addMember(new Student("회장", "20240005", "게임회장"));
        game.addMember(new Student("부회장", "20240006", "게임부회장"));

        clubs.add(soccer);
        clubs.add(basketball);
        clubs.add(game);
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    public Club findByName(String clubName) {
        for (Club club : clubs) {
            if (club.getClubName().equals(clubName)) {
                return club;
            }
        }

        return null;
    }
}