module com.example.mv {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.json;

    opens com.example.mv to javafx.fxml;
    exports com.example.mv;
}
