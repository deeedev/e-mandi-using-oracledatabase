package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class FeedBack {

	String type;
	String Name;
	String phone;
	String licenseNumber;
	String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Map<String, ArrayList<String>> getFeedback() {
		return feedback;
	}

	public static Map<String, ArrayList<String>> getComplaint() {
		return complaint;
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	ArrayList<String> listFeedback = new ArrayList<String>();
	ArrayList<String> listComplaint = new ArrayList<String>();

	public static Map<String, ArrayList<String>> feedback = new TreeMap<String, ArrayList<String>>();
	public static Map<String, ArrayList<String>> complaint = new TreeMap<String, ArrayList<String>>();

	public void fillFeedback() throws IOException {

		System.out.println("Please specify WholeSeller/Farmer: ");
		listFeedback.add(br.readLine());

		System.out.println("Enter Name: ");
		listFeedback.add(br.readLine());

		System.out.println("Enter Phone: ");
		listFeedback.add(br.readLine());

		System.out.println("Enter License Number: ");
		listFeedback.add(br.readLine());

		System.out.println("Please Enter your message: ");
		listFeedback.add(br.readLine());

		feedback.put(listFeedback.get(3), listFeedback);
	}

	public void fillComplaint() throws IOException {

		System.out.println("Please specify WholeSeller/Farmer: ");
		listComplaint.add(br.readLine());

		System.out.println("Enter Name: ");
		listComplaint.add(br.readLine());

		System.out.println("Enter Phone: ");
		listComplaint.add(br.readLine());

		System.out.println("Enter License Number: ");
		listComplaint.add(br.readLine());

		System.out.println("Please Enter your message: ");
		listComplaint.add(br.readLine());

		complaint.put(listComplaint.get(3), listComplaint);
	}
}
