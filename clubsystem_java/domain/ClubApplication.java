package domain;

public class ClubApplication {
    private Student student;

    public ClubApplication(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}