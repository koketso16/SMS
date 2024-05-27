package org.koketjo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SystemMethods {

    private static List<Student> students = new ArrayList<>();
    private static final String filePath = "src/main/StudentManagementSystem/java/org/koketjo/StudentData.txt";

    public static List<String> readData()
    {
        try
        {
            return Files.readAllLines(Paths.get(filePath));

        } catch (IOException e)
        {
            System.out.println("Error reading data.");
        }
        return null;
    }

    public static void writeData(List<Student> students)
    {
        String filePath = "src/main/StudentManagementSystem/java/org/koketjo/StudentData.txt";

        try {
            for (Student student : students) {
                String studentData = student.toString();
                Files.write(Path.of(filePath), List.of(studentData), StandardOpenOption.APPEND);
            }

        } catch (IOException e)
        {
            System.out.println("Error writing to text file.");
        }
    }

    public static void addStudent()
    {
        GridPane details = new GridPane();

        List<ImageView> imageViews = new ArrayList<>();
        Image image = new Image("file:src/main/StudentManagementSystem/pictures/pp.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(80);
        imageViews.add(imageView);

        details.add(imageView, 1, 10);

        TextField nameTextField = new TextField();
        nameTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label = new Label("Name:  ");
        label.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label, 0, 50);

        details.add(nameTextField, 1, 50);

        TextField surnameTextField = new TextField();
        surnameTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label1 = new Label("Surname:  ");
        label1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label1, 0, 100);

        details.add(surnameTextField, 1, 100);

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        genderComboBox.getItems().addAll("Male", "Female", "Non-binary", "Prefer not to say");

        Label label3 = new Label("Gender:  ");
        label3.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label3, 0, 200);

        details.add(genderComboBox, 1, 200);

        DatePicker dobTextField = new DatePicker();
        dobTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label4 = new Label("Date of Birth:  ");
        label4.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label4, 0, 300);

        details.add(dobTextField, 1, 300);

        TextField gradeTextField = new TextField();
        gradeTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label2 = new Label("Grade:  ");
        label2.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label2, 0, 400);

        details.add(gradeTextField, 1, 400);

        TextField rollTextField = new TextField();
        rollTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
        Label label5 = new Label("Roll number:  ");
        label5.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label5, 0, 500);

        details.add(rollTextField, 1, 500);

        TextField emailTextField = new TextField();
        emailTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label6 = new Label("Email:  ");
        label6.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label6, 0, 600);

        details.add(emailTextField, 1, 600);

        Stage stage = new Stage();
        Scene scene = new Scene(details, 400, 500);
        stage.setTitle("Student Details");
        stage.setScene(scene);
        stage.show();

        Button submitButton = new Button("Add Student");
        submitButton.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(submitButton, 1, 800);

        Label submitted = new Label();
        details.add(submitted, 1, 1200);

        submitButton.setOnAction(a ->
        {
            try {

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
                        genderComboBox.getValue() == null || dobTextField.getValue() == null ||
                        gradeTextField.getText().isEmpty() || rollTextField.getText().isEmpty() ||
                        emailTextField.getText().isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled out.");
                }
                Student student = new Student(nameTextField.getText(), surnameTextField.getText(), genderComboBox.getValue(), String.valueOf(dobTextField.getValue()), gradeTextField.getText(), rollTextField.getText(), emailTextField.getText());
                students.add(student);
                writeData(students);
                students.clear();

                submitted.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 20;");
                submitted.setText("Successfully added Student!");

                Button ok = new Button("OK");
                ok.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");

                ok.setOnAction(b ->
                {
                    stage.close();
                });

                details.add(ok, 1, 1700);
            } catch (Exception e)
            {
                submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                submitted.setText("Error: " + e.getMessage());
            }
        });

    }

    public static void removeStudent() {
        GridPane details = new GridPane();

        List<ImageView> imageViews = new ArrayList<>();
        Image image = new Image("file:src/main/StudentManagementSystem/pictures/delete.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(80);
        imageViews.add(imageView);

        details.add(imageView, 1, 10);

        TextField nameTextField = new TextField();
        nameTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label = new Label("Name:  ");
        label.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label, 0, 60);

        details.add(nameTextField, 1, 60);

        TextField surnameTextField = new TextField();
        surnameTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label1 = new Label("Surname:  ");
        label1.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label1, 0, 100);

        details.add(surnameTextField, 1, 100);

        TextField rollTextField = new TextField();
        rollTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label5 = new Label("Roll number:  ");
        label5.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(label5, 0, 500);

        details.add(rollTextField, 1, 500);

        Stage stage = new Stage();
        Scene scene = new Scene(details, 400, 320);
        stage.setTitle("Remove Student");
        stage.setScene(scene);
        stage.show();

        Button removeButton = new Button("Remove Student");
        removeButton.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        details.add(removeButton, 1, 800);

        Label submitted = new Label();
        details.add(submitted, 1, 1200);

        removeButton.setOnAction(a ->
        {
            try {

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
                        rollTextField.getText().isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled out.");
                }
                List<String> data = readData();
                assert data != null;
                List<String> updatedLines = data.stream()
                        .filter(line -> !line.contains("Name: " + nameTextField.getText()) && !line.contains("Surname: " + surnameTextField.getText()) && !line.contains("Roll number: " + rollTextField.getText()))
                        .toList();

                Files.write(Path.of(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);


                submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                submitted.setText("Successfully removed Student!");

                Button ok = new Button("OK");
                ok.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");

                ok.setOnAction(b ->
                {
                    stage.close();
                });

                details.add(ok, 1, 1700);

            } catch (Exception e)
            {
                submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                submitted.setText("Error: " + e.getMessage());
            }

        });
    }

    public static void searchStudent() {
        GridPane search = new GridPane();

        List<ImageView> imageViews = new ArrayList<>();
        Image image = new Image("file:src/main/StudentManagementSystem/pictures/files.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(180);
        imageView.setFitHeight(120);
        imageViews.add(imageView);

        search.add(imageView, 1, 10);

        ComboBox<String> searchComboBox = new ComboBox<>();
        searchComboBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
        searchComboBox.getItems().addAll("Roll number", "Email");

        Label label3 = new Label("Search by:  ");
        label3.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        search.add(label3, 0, 200);
        search.add(searchComboBox, 1, 200);

        Stage stage = new Stage();
        Scene scene = new Scene(search, 400, 500);
        stage.setTitle("Search Database");
        stage.setScene(scene);
        stage.show();

        Button submit = new Button("Submit");
        submit.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
        search.add(submit, 1, 500);

        submit.setOnAction(d ->
        {
            try {
                if (searchComboBox.getValue() == null) {
                    throw new IllegalArgumentException("Please select a search criteria.");
                }
                if (searchComboBox.getValue().equalsIgnoreCase("Roll number")) {
                    TextField rollTextField = new TextField();
                    rollTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                    Label label5 = new Label("Roll number:  ");
                    label5.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                    search.add(label5, 0, 800);

                    search.add(rollTextField, 1, 800);

                    Button submit1 = new Button("Submit");
                    submit1.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                    search.add(submit1, 1, 1000);

                    submit1.setOnAction(x ->
                    {
                        try {
                            if (rollTextField.getText().isEmpty()) {
                                throw new IllegalArgumentException("Please enter a roll number.");
                            }
                            List<String> data = readData();
                            assert data != null;
                            for (String line : data) {
                                if (line.contains(rollTextField.getText())) {
                                    String[] list = line.split(", ");
                                    Label label = new Label(list[0]);
                                    label.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(label, 1, 2000);

                                    Label labelA = new Label(list[1]);
                                    labelA.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelA, 1, 2200);

                                    Label labelB = new Label(list[2]);
                                    labelB.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelB, 1, 2400);

                                    Label labelC = new Label(list[3]);
                                    labelC.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelC, 1, 2600);

                                    Label labelD = new Label(list[4]);
                                    labelD.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelD, 1, 2800);

                                    Label labelE = new Label(list[5]);
                                    labelE.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelE, 1, 3000);

                                    Label labelF = new Label("Email: " + list[6].split(": ")[1].split(" ")[0]);
                                    labelF.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelF, 1, 3200);
                                }
                                Button close = new Button("CLOSE");
                                close.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                                close.setOnAction(b ->
                                {
                                    stage.close();
                                });
                                search.add(close, 1, 3500);
                            }
                        } catch (Exception e)
                        {
                            Label submitted = new Label();
                            submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                            submitted.setText("Error: " + e.getMessage());
                            search.add(submitted,1,1500);
                        }
                    });

                } else if(searchComboBox.getValue().equalsIgnoreCase("Email")){
                    TextField emailTextField = new TextField();
                    emailTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                    Label label6 = new Label("Email:  ");
                    label6.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                    search.add(label6, 0, 800);

                    search.add(emailTextField, 1, 800);

                    Button submit1 = new Button("Submit");
                    submit1.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
                    search.add(submit1, 1, 1000);

                    submit1.setOnAction(x ->
                    {
                        try {
                            if (emailTextField.getText().isEmpty()) {
                                throw new IllegalArgumentException("Please enter email.");
                            }
                            List<String> data = readData();
                            assert data != null;
                            for (String line : data) {
                                if (line.contains(emailTextField.getText())) {
                                    String[] list = line.split(", ");
                                    Label label = new Label(list[0]);
                                    label.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(label, 1, 2000);

                                    Label labelA = new Label(list[1]);
                                    labelA.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelA, 1, 2200);

                                    Label labelB = new Label(list[2]);
                                    labelB.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelB, 1, 2400);

                                    Label labelC = new Label(list[3]);
                                    labelC.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelC, 1, 2600);

                                    Label labelD = new Label(list[4]);
                                    labelD.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelD, 1, 2800);

                                    Label labelE = new Label(list[5]);
                                    labelE.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelE, 1, 3000);

                                    Label labelF = new Label("Email: " + list[6].split(": ")[1].split(" ")[0]);
                                    labelF.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                    search.add(labelF, 1, 3200);
                                }
                                Button close = new Button("CLOSE");
                                close.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

                                close.setOnAction(b ->
                                {
                                    stage.close();
                                });
                                search.add(close, 1, 3500);
                            }
                        }catch (Exception e)
                        {
                            Label submitted = new Label();
                            submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                            submitted.setText("Error: " + e.getMessage());
                            search.add(submitted,1,1500);
                        }
                    });
                }
            } catch (Exception e)
            {
                Label submitted = new Label();
                submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                submitted.setText("Error: " + e.getMessage());
                search.add(submitted,1,800);
            }
        });


    }

    public static void updateStudentData() {
        GridPane update = new GridPane();

        List<ImageView> imageViews = new ArrayList<>();
        Image image = new Image("file:src/main/StudentManagementSystem/pictures/edit.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(100);
        imageViews.add(imageView);
        update.add(imageView,1,10);

        TextField rollTextField = new TextField();
        rollTextField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

        Label label5 = new Label("Enter Roll Number:  ");
        label5.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 18;");
        update.add(label5, 0, 500);

        update.add(rollTextField, 1, 700);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        update.add(submitButton, 1, 800);

        Stage stage = new Stage();
        Scene scene = new Scene(update, 700, 600);
        stage.setTitle("Update Data");
        stage.setScene(scene);
        stage.show();

        submitButton.setOnAction(c ->
                {
                    try {
                        if (rollTextField.getText().isEmpty()) {
                            throw new IllegalArgumentException("Please enter a Roll Number.");
                        }

                        List<String> data = readData();
                        try {
                            assert data != null;
                            List<String> updatedLines = data.stream()
                                    .filter(line -> !line.contains(rollTextField.getText())).toList();

                            Files.write(Path.of(filePath), updatedLines, StandardOpenOption.TRUNCATE_EXISTING);

                        } catch (Exception e) {
                            System.out.println("Error finding student.");
                        }

                        for (String line : data) {
                            String[] list = line.split(", ");
                            if (line.contains(rollTextField.getText())) {
                                Label label = new Label("Name: ");
                                label.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label, 1, 900);

                                TextField textField1 = new TextField(list[0].split(": ")[1]);
                                textField1.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
                                update.add(textField1, 2, 900);

                                Label label2 = new Label("Surname: ");
                                label2.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label2, 1, 1100);

                                TextField textField2 = new TextField(list[1].split(": ")[1]);
                                textField2.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                                update.add(textField2, 2, 1100);

                                Label label3 = new Label("Gender: ");
                                label3.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label3, 1, 1300);

                                TextField textField3 = new TextField(list[2].split(": ")[1]);
                                textField3.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
                                update.add(textField3, 2, 1300);

                                Label label4 = new Label("DOB: ");
                                label4.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label4, 1, 1500);

                                TextField textField4 = new TextField(list[3].split(": ")[1]);
                                textField4.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");

                                update.add(textField4, 2, 1500);

                                Label label6 = new Label("Grade: ");
                                label6.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label6, 1, 1700);

                                TextField textField5 = new TextField(list[4].split(": ")[1]);
                                textField5.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
                                update.add(textField5, 2, 1700);

                                Label label7 = new Label("Email: ");
                                label7.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(label7, 1, 2100);

                                TextField textField8 = new TextField(list[6].split(": ")[1].split(" ")[0]);
                                textField8.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5; -fx-font-weight: bold; -fx-font-size: 16");
                                update.add(textField8, 2, 2100);

                                Button updateData = new Button("Update Data");
                                updateData.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16;");
                                update.add(updateData, 1, 2500);

                                Label submitted = new Label();
                                update.add(submitted, 1, 2700);

                                updateData.setOnAction(a ->
                                {
                                    Student student = new Student(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), rollTextField.getText(), textField8.getText());
                                    students.add(student);
                                    writeData(students);
                                    students.clear();

                                    submitted.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 18;");
                                    submitted.setText("Successfully updated student data!");

                                    Button ok = new Button("OK");
                                    ok.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");

                                    ok.setOnAction(b ->
                                    {
                                        stage.close();
                                    });

                                    update.add(ok, 1, 2900);
                                });
                            }
                        }
                    }catch (Exception e)
                    {
                        Label submitted = new Label();
                        submitted.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 18;");
                        submitted.setText("Error: " + e.getMessage());
                        update.add(submitted,1,1200);
                    }
                });


    }

    private static Student parseStudent(String line)
    {
        String[] students = line.split("x");

        for (String studs : students)
        {
            String[] parts = studs.split(", ");

            String name = parts[0].split(": ")[1];
            String surname = parts[1].split(": ")[1];
            String gender = parts[2].split(": ")[1];
            String dob = parts[3].split(": ")[1];
            String grade = parts[4].split(": ")[1];
            String roll = parts[5].split(": ")[1];
            String email = parts[6].split(": ")[1];

            return new Student(name, surname, gender, dob, grade, roll, email);
        }
        return null;
    }

    public static void displayAllStudents() {
        TableView<Student> studentTable = new TableView<>();
        studentTable.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 15;");

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, String> dobColumn = new TableColumn<>("DOB");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));

        TableColumn<Student, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Student, String> rollColumn = new TableColumn<>("Roll Number");
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentTable.getColumns().addAll(nameColumn, surnameColumn, genderColumn, dobColumn, gradeColumn, rollColumn, emailColumn);

        try {
            Path filePath1 = Path.of("src/main/StudentManagementSystem/java/org/koketjo/StudentData.txt");

            List<String> lines = Files.readAllLines(filePath1);

            ObservableList<Student> studs = FXCollections.observableArrayList();

            for (String line : lines)
            {
                Student student = parseStudent(line);
                studs.add(student);
            }
            studentTable.setItems(studs);

        } catch (IOException e)
        {
            System.out.println("Error writing to table.");
        }

        Scene scene = new Scene(studentTable, 750, 450);
        Stage stage = new Stage();
        stage.setTitle("Student Database");
        stage.setScene(scene);
        stage.show();
    }

}


