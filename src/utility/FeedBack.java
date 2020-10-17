package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.FeedbackDAO;

public abstract class FeedBack {

	String usertype;
	String name;
	String phone;
	long licenseNumber;
	String message;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String type) {
		this.usertype = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		name = Name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(long licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FeedbackDAO fdao = new FeedbackDAO();

	public void fillFeedback() throws IOException {

		System.out.println("Please specify WholeSeller/Farmer/Retailer: ");
		String usertype = br.readLine();

		System.out.println("Enter Name: ");
		String name = br.readLine();

		System.out.println("Enter Phone: ");
		String phone = br.readLine();

		System.out.println("Enter License Number: ");
		long licenseNumber = Long.parseLong(br.readLine());

		System.out.println("Please Enter your message: ");
		String message = br.readLine();

		fdao.feedback(name, phone, licenseNumber, usertype, message);

	}

	public void fillComplaint() throws IOException {

		System.out.println("Please specify WholeSeller/Farmer/Retailer: ");
		String usertype = br.readLine();

		System.out.println("Enter Name: ");
		String name = br.readLine();

		System.out.println("Enter Phone: ");
		String phone = br.readLine();

		System.out.println("Enter License Number: ");
		long licenseNumber = Long.parseLong(br.readLine());

		System.out.println("Please Enter your message: ");
		String message = br.readLine();

		fdao.complaint(name, phone, licenseNumber, usertype, message);

	}
}
