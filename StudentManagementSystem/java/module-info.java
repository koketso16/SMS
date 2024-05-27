module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires unirest.java;
    requires java.datatransfer;
    requires java.desktop;


    opens org.koketjo to javafx.fxml;
    exports org.koketjo;
}