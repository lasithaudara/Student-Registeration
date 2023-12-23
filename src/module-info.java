module StudentRegisteration {

	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;

	opens application to javafx.graphics, javafx.fxml;
	opens controllers;

	exports controllers;

}
