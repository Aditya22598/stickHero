module main.stickhero {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.desktop;
        requires javafx.media;
        requires org.testng;
        requires org.junit.jupiter.api;
        requires org.junit.platform.commons;


        opens Main to javafx.fxml;
        exports Main;
        exports GameLayers;
        opens GameLayers to javafx.fxml;
}