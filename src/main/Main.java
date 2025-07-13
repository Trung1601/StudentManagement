package main;

import java.util.List;
import java.util.Scanner;

import model.Student;
import service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            showMenu();
            try {
                System.out.print("Your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addStudentUI(service, sc);
                        break;
                    case 2:
                        deleteStudentUI(service, sc);
                        break;
                    case 3:
                        searchStudentUI(service, sc);
                        break;
                    case 4:
                        displayAll(service);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add student");
        System.out.println("2. Delete student");
        System.out.println("3. Search student");
        System.out.println("4. Display all");
        System.out.println("0. Exit");
    }

    private static void addStudentUI(StudentService service, Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter full name: ");
            String name = sc.nextLine();

            System.out.print("Enter GPA: ");
            double gpa = Double.parseDouble(sc.nextLine());

            service.addStudent(id, name, gpa);
            System.out.println("Student added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudentUI(StudentService service, Scanner sc) {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            if (service.deleteStudent(id)) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchStudentUI(StudentService service, Scanner sc) {
        System.out.print("Enter name keyword: ");
        String keyword = sc.nextLine();
        List<Student> results = service.searchByName(keyword);

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Student s : results) {
                System.out.println(s);
            }
        }
    }

    private static void displayAll(StudentService service) {
        List<Student> list = service.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.printf("| %-5s | %-50s | %-4s |\n", "ID", "Name", "GPA");
            System.out.println("---------------------------------------------------------------");
            for (Student s : list) {
                System.out.println(s);
            }
        }
    }
}
