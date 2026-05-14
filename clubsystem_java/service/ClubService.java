package service;

import java.util.ArrayList;

import domain.Club;
import domain.Student;
import domain.ClubApplication;
import repository.ClubRepository;

public class ClubService {
    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ArrayList<Club> getClubList() {
        return clubRepository.getClubs();
    }

    public Club getClub(String clubName) {
        return clubRepository.findByName(clubName);
    }

    public boolean checkClubPassword(String clubName, String password) {
        Club club = clubRepository.findByName(clubName);

        if (club == null) {
            return false;
        }

        return club.checkPassword(password);
    }

    public ArrayList<Student> getMembers(String clubName) {
        Club club = clubRepository.findByName(clubName);

        if (club == null) {
            return new ArrayList<>();
        }

        return club.getMembers();
    }

    public ArrayList<ClubApplication> getApplications(String clubName) {
        Club club = clubRepository.findByName(clubName);

        if (club == null) {
            return new ArrayList<>();
        }

        return club.getApplications();
    }

    public boolean applyToClub(String clubName, String studentId, String name) {
        Club club = clubRepository.findByName(clubName);

        if (club == null) {
            return false;
        }

        for (Student member : club.getMembers()) {
            if (member.getStudentId().equals(studentId)) {
                return false;
            }
        }

        for (ClubApplication app : club.getApplications()) {
            if (app.getStudent().getStudentId().equals(studentId)) {
                return false;
            }
        }

        Student student = new Student("부원", studentId, name);
        ClubApplication application = new ClubApplication(student);

        club.addApplication(application);
        return true;
    }

    public boolean approveApplication(String clubName, String studentId) {
        Club club = clubRepository.findByName(clubName);

        if (club == null) {
            return false;
        }

        ClubApplication target = null;

        for (ClubApplication app : club.getApplications()) {
            if (app.getStudent().getStudentId().equals(studentId)) {
                target = app;
                break;
            }
        }

        if (target == null) {
            return false;
        }

        Student student = target.getStudent();
        student.setRole("동아리원");

        club.addMember(student);
        club.removeApplication(target);

        return true;
    }
}