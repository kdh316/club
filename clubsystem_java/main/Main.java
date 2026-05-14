import java.util.Scanner;

import domain.Student;
import domain.ClubApplication;
import repository.ClubRepository;
import service.ClubService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClubRepository clubRepository = new ClubRepository();
        ClubService clubService = new ClubService(clubRepository);

        while (true) {
            System.out.println("\n[광운대학교 동아리 시스템]");
            System.out.println("1. 축구");
            System.out.println("2. 농구");
            System.out.println("3. 게임");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String clubName = "";

            if (choice == 1) {
                clubName = "축구";
            } else if (choice == 2) {
                clubName = "농구";
            } else if (choice == 3) {
                clubName = "게임";
            } else if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 선택입니다.");
                continue;
            }

            while (true) {
                System.out.println("\n[" + clubName + " 동아리]");
                System.out.println("1. 소개");
                System.out.println("2. 회원 목록");
                System.out.println("3. 가입 신청");
                System.out.println("4. 가입 승인");
                System.out.println("0. 뒤로가기");
                System.out.print("선택: ");

                int menu = scanner.nextInt();
                scanner.nextLine();

                if (menu == 1) {
                    System.out.println();

                    System.out.println(
                    clubService.getClub(clubName).getDescription()
                    );
                } else if (menu == 2) {
                    System.out.println("\n[부서원 목록]");

                    for (Student member : clubService.getMembers(clubName)) {
                        System.out.println(
                            member.getRole() + " / " +
                            member.getStudentId() + " / " +
                            member.getName()
                        );
                    }

                } else if (menu == 3) {
                    System.out.println("\n[가입 신청]");

                    System.out.print("학번: ");
                    String studentId = scanner.nextLine();

                    System.out.print("이름: ");
                    String name = scanner.nextLine();

                    boolean result = clubService.applyToClub(clubName, studentId, name);

                    if (result) {
                        System.out.println("가입 신청이 완료되었습니다.");
                    } else {
                        System.out.println("이미 가입했거나 신청한 학번입니다.");
                    }

                } else if (menu == 4) {
                    System.out.print("비밀번호 입력: ");
                    String password = scanner.nextLine();

                    boolean passwordResult = clubService.checkClubPassword(clubName, password);

                    if (!passwordResult) {
                        System.out.println("비밀번호가 틀렸습니다.");
                        continue;
                    }

                    System.out.println("\n[가입 승인 목록]");

                    if (clubService.getApplications(clubName).isEmpty()) {
                        System.out.println("대기 중인 신청자가 없습니다.");
                        continue;
                    }

                    for (ClubApplication app : clubService.getApplications(clubName)) {
                        System.out.println(
                            app.getStudent().getStudentId() + " / " +
                            app.getStudent().getName() + " / " +
                            "[수락]"
                        );
                    }

                    System.out.print("수락할 학번 입력: ");
                    String studentId = scanner.nextLine();

                    boolean result = clubService.approveApplication(clubName, studentId);

                    if (result) {
                        System.out.println("수락되었습니다.");
                    } else {
                        System.out.println("해당 학번의 신청자를 찾을 수 없습니다.");
                    }

                } else if (menu == 0) {
                    break;

                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
        }

        scanner.close();
    }
}