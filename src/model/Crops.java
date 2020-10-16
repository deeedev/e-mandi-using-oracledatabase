package model;

public class Crops {

	long cropID;
	String name;
	long price;
	long msp;
	long quantity;

	public Crops(int sellId, String name2, int price2, int msp2, int quantity2) {

		this.cropID = sellId;
		this.name = name2;
		this.price = price2;
		this.msp = msp2;
		this.quantity = quantity2;
	}

	public Crops(int sellId, int price, int quantity2) {
		// TODO Auto-generated constructor stub
		this.cropID = sellId;
		this.price = price;
		this.quantity = quantity2;
	}

	public long getCropID() {
		return cropID;
	}

	public void setCropID(long cropID) {
		this.cropID = cropID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getMsp() {
		return msp;
	}

	public void setMsp(long msp) {
		this.msp = msp;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}