package businessLogic;

public class Medicine {
	private int id;
	private String name;
	private int price;
	protected int stock;
	
	public Medicine(int ID, String n, int p, int s) {
		id = ID;
		name = n; 
		price = p;
		stock = s;
	}
	
	public Medicine() {
		id = -1;
		name = "placeholder";
		price = 0;
		stock = 0;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void displayMed() {
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
		System.out.println("Stock: " + stock);
	}
	
}
