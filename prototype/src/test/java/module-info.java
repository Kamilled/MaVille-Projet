module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires io.javalin;
    requires org.slf4j;
    requires org.junit.jupiter.api;
    requires com.example.prototype;
    requires junit;


    exports com.example.test;

    opens com.example.test to
            org.junit.platform.commons;

}
