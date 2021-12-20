package databaseFiles;

// File Handling
import java.io.File;

// File Writing
import java.io.FileWriter;
import java.io.IOException;

// File reading
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import businessLogic.Inventory;
import businessLogic.InventoryFullException;
import businessLogic.Medicine;
import businessLogic.User;

public class HandleFiles {

	public void writeInvToFile(String filename, Inventory inv) {
		try {
			File myObj = new File(filename); // create file
			FileWriter writer = new FileWriter(filename);
			for (int i = 0; i < inv.getCurrent(); i++) {
				int id = inv.getMeds()[i].getId();
				String name = inv.getMeds()[i].getName();
				int price = inv.getMeds()[i].getPrice();
				int stock = inv.getMeds()[i].getStock();

				String delimiter = ", ";
				String line = id + delimiter + name + delimiter + price + delimiter + stock + "%n";
				line = String.format(line);
				// System.out.println("Written to " + filename + ": " + line);

				writer.write(line);
			}
			writer.close();
			System.out.println("Writing to file successful!");
		} catch (IOException e) {
			System.out.println("Some error occured during writing to file" + filename);
			e.printStackTrace();
		}

	}

	public void writeUserToFile(String filename, User[] users) {
		try {
			File myObj = new File(filename); // create file
			FileWriter writer = new FileWriter(filename);

			for (int i = 0; i < users.length; i++) {
				int id = users[i].getId();
				String email = users[i].getEmail();
				String username = users[i].getUserName();
				String password = users[i].getPassword();
				String type = users[i].getType();

				String delimiter = ", ";
				String line = id + delimiter + email + delimiter + username + delimiter + password + delimiter + type
						+ "%n";
				line = String.format(line);
				// System.out.println("Written to " + filename + ": " + line);

				writer.write(line);
			}

			writer.close();
			System.out.println("Writing to file successful!");
		} catch (IOException e) {
			System.out.println("Some error occured during writing to file" + filename);
			e.printStackTrace();
		}
	}

	public int fileLength(String filename) {
		try {
			File myObj = new File(filename);
			Scanner reader = new Scanner(myObj);
			System.out.println("(fl)Reading from " + filename);

			int length = 0;
			while (reader.hasNextLine()) {
				reader.nextLine();
				length++;
			}
			reader.close();
			return length;
		} catch (FileNotFoundException e) {
			System.out.println("Error occured during reading of file " + filename);
			e.printStackTrace();
		}
		return 0;
	}

	public String[] readFromFile(String filename) {
		String[] result = new String[fileLength(filename)];
		int index = 0;
		try {
			File myObj = new File(filename);
			Scanner reader = new Scanner(myObj);
			System.out.println("Reading from " + filename);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				result[index] = data;
				index++;
				System.out.println(data);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured during reading of file " + filename);
			e.printStackTrace();
		}

		return result;
	}

	public boolean[] userInFile(String filename, String email, String password) {
		boolean[] valid_type = new boolean[2];
		try {
			File myObj = new File(filename);
			Scanner reader = new Scanner(myObj);
			System.out.println("Reading from " + filename);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] splitData = data.split(", ");
				if (splitData[1].equals(email) && splitData[3].equals(password)) {
					if (splitData[4].equals("m")) {
						valid_type[0] = true; // User found
						valid_type[1] = true; // It's a manager
						return valid_type;
					} else if (splitData[4].equals("c")) {
						valid_type[0] = true; // User found
						valid_type[1] = false; // It's a customer
						return valid_type;
					}
				}
				System.out.println(Arrays.toString(splitData));
				System.out.println(data);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured during reading of " + filename);
			e.printStackTrace();
		}
		// Nothing found
		valid_type[0] = false; // No user found
		valid_type[1] = false; // Wrong user type
		return valid_type;
	}

	public Inventory stringArrayToInv(String[] str) throws InventoryFullException {
		Inventory inv = new Inventory();
		Medicine[] medz = new Medicine[str.length];

		for (int i = 0; i < str.length; i++) {
			String[] data = str[i].split(", ");
			int id = Integer.parseInt(data[0]);
			String name = data[1];
			int price = Integer.parseInt(data[2]);
			int stock = Integer.parseInt(data[3]);

			medz[i] = new Medicine(id, name, price, stock);
		}

		for (int i = 0; i < medz.length; i++) {
			inv.addMed(medz[i]);
		}

		return inv;
	}
}
