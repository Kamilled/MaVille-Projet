module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires io.javalin;
    requires org.slf4j;

    exports com.example.prototype;

    opens com.example.prototype to
            javafx.fxml;
}

