module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires io.javalin;
    requires org.slf4j;

    opens com.example.prototype to
            javafx.fxml;
    exports com.example.prototype;
}
