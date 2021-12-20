package businessLogic;

import java.sql.SQLException;
import java.util.Scanner;

import databaseFiles.connectDB;

public class User {
	protected int id;
	protected String email;
	protected String userName;
	protected String password;
	protected String type;

	
	public User(int ID, String em, String un, String pass, String typ) { 
		id = ID;
		email = em;
		userName = un;
		password = pass;
		type = typ;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	User() { 
		id = -1;
		email = "placeholder";
		userName = "placeholder";
		password = "password";
		type = "u";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	Scanner inp1 = new Scanner(System.in);

	public Medicine addMed() {
		int medID, medPrice, medStock;
		String medName;

		System.out.println("Enter medicine ID: ");
		medID = inp1.nextInt();
		System.out.println("Enter medicine Name: ");
		medName = inp1.next();
		System.out.println("Enter medicine price: ");
		medPrice = inp1.nextInt();
		System.out.println("Enter medicine to be added to stock: ");
		medStock = inp1.nextInt();

		Medicine resultMed = new Medicine();
		resultMed.setId(medID);
		resultMed.setName(medName);
		resultMed.setPrice(medPrice);
		resultMed.setStock(medStock);

		return resultMed;

	}
	

}
