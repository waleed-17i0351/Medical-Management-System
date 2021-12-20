package UI;

import java.io.IOException;
import java.util.Arrays;

import businessLogic.Inventory;
import businessLogic.InventoryFullException;
import businessLogic.Medicine;
import databaseFiles.HandleFiles;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class ScreenSwapper {

	// Setting up
	private Stage stage;
	private Scene scene;
	private Parent root;

	// Accessories - always write @FXML for the fx:ids you get from Scene Builder
	@FXML
	Button loginBtn;
	@FXML
	TextField emailTf;
	@FXML
	TextField passwordTf;
	@FXML
	TextField midTf;
	@FXML
	TextField mnTf;
	@FXML
	TextField mpTf;
	@FXML
	TextField msTf;
	@FXML
	Text actiontarget;

	// Switchers
	public void switchr(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AccountSelector.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switcherLogin(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/*
	 * public void switcherCUI(ActionEvent e) throws IOException { Parent root =
	 * FXMLLoader.load(getClass().getResource("CustomerUI.fxml")); stage = (Stage)
	 * ((Node) e.getSource()).getScene().getWindow(); scene = new Scene(root);
	 * stage.setScene(scene); stage.show(); }
	 */

	public void switcherMUI(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ManagerUI.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switcherCheckout(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AccountSelector.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switcherAddMed(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AddMedicine.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// ButtonActions

	public void eventHandlerLogin(ActionEvent e) throws IOException {
		String email = emailTf.getText();
		String password = passwordTf.getText();
		HandleFiles fH = new HandleFiles();
		boolean[] validate;
		validate = fH.userInFile("users.txt", email, password);

		if (validate[0] == true) {
			Parent root;
			try {
				if (validate[1] == true) {
					root = FXMLLoader.load(getClass().getResource("ManagerUI.fxml"));
				} else {
					root = FXMLLoader.load(getClass().getResource("CustomerUI.fxml"));
				}

				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Problem with login page");
			}
		} else {
			actiontarget.setOpacity(1);
			// actiontarget.setText("Incorrect email or password");
		}
	}

	public void eventHandlerAddMed(ActionEvent e) throws IOException {
		int id = Integer.parseInt(midTf.getText());
		String name = mnTf.getText();
		int price = Integer.parseInt(mpTf.getText());
		int stock = Integer.parseInt(msTf.getText());
		Medicine newMed = new Medicine(id, name, price, stock);
		
		HandleFiles fH = new HandleFiles();
		String[] medsInInv = fH.readFromFile("inventory.txt");
		Inventory inv;
		try {
			inv = fH.stringArrayToInv(medsInInv);
			inv.addMed(newMed);
			fH.writeInvToFile("inventory.txt", inv);
		} catch (InventoryFullException e1) {
			e1.printStackTrace();
			System.out.println("Couldn't convert String Array to inventory...");
		}
		System.out.println("Displaying AddMed Prompts");
		
		Parent root = FXMLLoader.load(getClass().getResource("ManagerUI.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		/*if (validate[0] == true) {
			Parent root;
			try {
				if (validate[1] == true) {
					root = FXMLLoader.load(getClass().getResource("ManagerUI.fxml"));
				} else {
					root = FXMLLoader.load(getClass().getResource("CustomerUI.fxml"));
				}

				stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Problem with login page");
			}
		} else {
			actiontarget.setOpacity(1);
			// actiontarget.setText("Incorrect email or password");
		}*/
	}
}
