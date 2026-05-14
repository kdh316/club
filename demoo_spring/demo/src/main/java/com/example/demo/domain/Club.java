package com.example.demo.domain;

import java.util.ArrayList;

public class Club {
    private String clubName;
    private String password;
    private String description;

    private ArrayList<Student> members = new ArrayList<>();
    private ArrayList<ClubApplication> applications = new ArrayList<>();

    public Club(String clubName, String password, String description) {
        this.clubName = clubName;
        this.password = password;
        this.description = description;
    }

    public String getClubName() {
        return clubName;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public ArrayList<ClubApplication> getApplications() {
        return applications;
    }

    public void addMember(Student student) {
        members.add(student);
    }

    public void addApplication(ClubApplication application) {
        applications.add(application);
    }

    public void removeApplication(ClubApplication application) {
        applications.remove(application);
    }
}