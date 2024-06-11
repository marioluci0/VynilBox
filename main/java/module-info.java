module ProjetoPooFX {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.controlsfx.controls;

    opens com.vynilbox to javafx.fxml;
    exports com.vynilbox;
    exports com.vynilbox.controllers;
    opens com.vynilbox.controllers to javafx.fxml;
    exports com.vynilbox.connection;
    opens com.vynilbox.connection to javafx.fxml;
    exports com.vynilbox.abstracts;
    opens com.vynilbox.abstracts to javafx.fxml;
}