package com.example.demo.domain;

public class Student {
    private String role;      // 회장, 부회장, 부원
    private String studentId;
    private String name;

    public Student(String role, String studentId, String name) {
        this.role = role;
        this.studentId = studentId;
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}