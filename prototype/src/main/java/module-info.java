module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires io.javalin;
    requires org.slf4j;
    requires org.junit.jupiter.api;
    requires org.testng;

    exports com.example.prototype;

    opens com.example.prototype to
            javafx.fxml;
}

