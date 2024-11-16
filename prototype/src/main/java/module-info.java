module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    exports com.example.prototype;

    opens com.example.prototype to
            javafx.fxml;
}

