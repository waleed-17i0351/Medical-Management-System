package UI;

import businessLogic.Inventory;
import businessLogic.Medicine;
import businessLogic.User;
import databaseFiles.connectDB;
import databaseFiles.HandleFiles;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AccountSelector.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			// connecting DB
			connectDB connection = new connectDB();
			connection.load();

			// ----
			Inventory inv = new Inventory();
			Medicine panadol = new Medicine(1, "Panadol", 50, 100);
			Medicine ibrufin = new Medicine(2, "Ibrufin", 120, 36);
			Medicine cs = new Medicine(3, "Cough Syrup", 80, 60);
			HandleFiles fileHandler = new HandleFiles();
			User c1 = new User(1, "c1@c.com", "customer1", "123", "c");
			User c2 = new User(1, "c2@c.com", "customer2", "321", "c");
			User c3 = new User(1, "c3@c.com", "customer3", "abc", "c");
			User m1 = new User(1, "m1@m.com", "manager1", "admin", "m");
			User m2 = new User(1, "1", "1", "1", "m");
			User[] users = {c1, c2, c3, m1, m2};

			inv.addMed(panadol);
			inv.addMed(ibrufin);
			inv.addMed(cs);
			inv.displayInv();

			fileHandler.writeInvToFile("inventory.txt", inv);
			fileHandler.writeUserToFile("users.txt", users);

			fileHandler.readFromFile("inventory.txt");
			fileHandler.readFromFile("users.txt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
