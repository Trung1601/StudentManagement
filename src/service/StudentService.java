package service;

import model.Student;
import util.Validator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<Student>();

    public boolean addStudent(int id, String name, double gpa) throws IllegalArgumentException {
        if (!Validator.isValidId(id) || !Validator.isValidName(name) || !Validator.isValidGpa(gpa)) {
            throw new IllegalArgumentException("Invalid student data.");
        }

        if (findStudentById(id) != null) {
            throw new IllegalArgumentException("Duplicate student ID.");
        }

        students.add(new Student(id, name, gpa));
        return true;
    }

    public boolean deleteStudent(int id) {
        Student found = findStudentById(id);
        if (found != null) {
            students.remove(found);
            return true;
        }
        return false;
    }

    public List<Student> searchByName(String keyword) {
        List<Student> result = new ArrayList<Student>();
        for (Student s : students) {
            if (s.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
