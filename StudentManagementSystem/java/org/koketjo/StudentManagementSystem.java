package org.koketjo;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentManagementSystem extends Application{

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();

        Image backgroundImage = new Image("file:src/main/StudentManagementSystem/pictures/data-storage.jpg");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        grid.setBackground(new Background(background));
        grid.setPadding(new Insets(40, 40, 40, 40));

        Label welcomeLabel = new Label("Welcome to the Student Management System.");
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18;");

        Label secondLabel = new Label("What would you like to do? :");
        secondLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20;");

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        addStudentButton.setOnAction(e -> {
            try
            {
                SystemMethods.addStudent();

            } catch (NumberFormatException ex)
            {
                System.out.println("Error adding student.");
            }
        });

        Button removeStudentButton = new Button("Remove Student");
        removeStudentButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        removeStudentButton.setOnAction(e -> {
            try
            {
                SystemMethods.removeStudent();

            } catch (NumberFormatException ex)
            {
                System.out.println("Error removing student.");

            }
        });

        Button searchStudentButton = new Button("Search Student");
        searchStudentButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        searchStudentButton.setOnAction(e -> {
            try
            {
                SystemMethods.searchStudent();

            } catch (NumberFormatException ex)
            {
                System.out.println("Error searching for student.");

            }
        });

        Button displayAllStudentButton = new Button("Display All Students");
        displayAllStudentButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        displayAllStudentButton.setOnAction(e -> {
            try
            {
                SystemMethods.displayAllStudents();

            } catch (NumberFormatException ex)
            {
                System.out.println("Error displaying students.");
            }
        });

        Button updateStudentDataButton = new Button("Update Student Data");
        updateStudentDataButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        updateStudentDataButton.setOnAction(e -> {
            try
            {
                SystemMethods.updateStudentData();

            } catch (NumberFormatException ex)
            {
                System.out.println("Error updating student data.");
            }
        });

        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
        quitButton.setOnAction(a ->
        {
            Stage shutdownStage = new Stage();

            GridPane quitGrid = new GridPane();

            quitGrid.setStyle("-fx-background-color: #808080;");
            Label shutdownLabel = new Label("  SHUTTING DOWN STUDENT DATABASE... ");
            shutdownLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24; -fx-font-weight: bold");

            quitGrid.add(shutdownLabel, 3, 500);

            Label countdownLabel = new Label("3");
            countdownLabel.setStyle("-fx-font-size: 200; -fx-font-weight: bold");
            GridPane.setHalignment(countdownLabel, HPos.CENTER);
            GridPane.setValignment(countdownLabel, VPos.CENTER);
            quitGrid.add(countdownLabel, 3, 2000);

            Scene shutdownScene = new Scene(quitGrid, 500, 300);
            shutdownStage.setScene(shutdownScene);

            Label byeLabel = new Label("  Goodbye! ");
            byeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold");

            quitGrid.add(byeLabel, 3, 2000);

            final int[] countdown = {2};
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        countdownLabel.setText(String.valueOf(countdown[0]));
                        countdown[0]--;

                        if (countdown[0] < -1) {
                            shutdownStage.close();
                            System.exit(0);
                        }
                    })
            );
            timeline.setCycleCount(4);

            shutdownStage.show();
            timeline.play();
        });


        grid.add(welcomeLabel, 0, 0);
        grid.add(secondLabel, 0, 5);
        grid.add(addStudentButton, 0, 500);
        grid.add(removeStudentButton,0,700);
        grid.add(searchStudentButton,1,400);
        grid.add(updateStudentDataButton,1,600);
        grid.add(displayAllStudentButton,0,2000);
        grid.add(quitButton, 1, 800);

        Scene scene = new Scene(grid, 800, 400);
        stage.setTitle("Student-Management-System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
