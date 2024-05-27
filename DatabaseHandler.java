package org.koketjo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "gender TEXT NOT NULL, " +
                "dob TEXT NOT NULL, " +
                "grade TEXT NOT NULL, " +
                "rollNumber TEXT NOT NULL UNIQUE, " +
                "email TEXT NOT NULL UNIQUE)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addStudent(Student student) {
        String insertSQL = "INSERT INTO students(name, surname, gender, dob, grade, rollNumber, email) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getDOB());
            pstmt.setString(5, student.getGrade());
            pstmt.setString(6, student.getRollNumber());
            pstmt.setString(7, student.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStudent(Student student) {
        String updateSQL = "UPDATE students SET name = ?, surname = ?, gender = ?, dob = ?, grade = ?, email = ? WHERE rollNumber = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getDOB());
            pstmt.setString(5, student.getGrade());
            pstmt.setString(6, student.getEmail());
            pstmt.setString(7, student.getRollNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudent(String rollNumber) {
        String deleteSQL = "DELETE FROM students WHERE rollNumber = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, rollNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<Student> getAllStudents() {
        String selectSQL = "SELECT * FROM students";
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");
                String grade = rs.getString("grade");
                String rollNumber = rs.getString("rollNumber");
                String email = rs.getString("email");
                Student student = new Student(name, surname, gender, dob, grade, rollNumber, email);
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return studentList;
    }
}