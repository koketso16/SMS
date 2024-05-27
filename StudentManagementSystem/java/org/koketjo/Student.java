package org.koketjo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class Student {

    private final String name;
    private final String surname;
    private final String gender;
    private final String DOB;
    private final String grade;
    private final String rollNumber;

    private final String email;

    Student(String name1, String surname1,String gender1,String DOB1, String grade1, String rollNumber1,String email1)
    {
        this.name = name1;
        this.surname = surname1;
        this.gender = gender1;
        this.DOB = DOB1;
        this.grade = grade1;
        this.rollNumber = rollNumber1;
        this.email = email1;
    }

    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getSurname() {
        return surname;
    }

    public String getGrade() {
        return grade;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Surname: %s, Gender: %s, DOB: %s , Grade: %s, Roll Number: %s, Email: %s x", name, surname,gender,DOB,grade, rollNumber, email);
    }

}
