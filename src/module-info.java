module AssignmentManagment {
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens UI to javafx.graphics, javafx.fxml;
}
