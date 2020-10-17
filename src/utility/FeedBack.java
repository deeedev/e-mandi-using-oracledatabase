package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class FeedBack {

	String type;
	String name;
	String phone;
	long licenseNumber;
	String message;
	int i = getid();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	private int getid() {

		String sql = "select id from feedback where rownum<=1 order by id desc";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void fillFeedback() throws IOException {

		final String type = "Feedback";

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

		String sql = "insert into feedback values(?,?,?,?,?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setString(2, name);
			st.setString(3, phone);
			st.setLong(4, licenseNumber);
			st.setString(5, message);
			st.setString(6, usertype);
			st.setString(7, type);
			int result = st.executeUpdate();

			if (result == 0)
				System.out.println("Please Try Again Later");
			if (result == 1)
				System.out.println("*** Thanks for your valualble feedback. We will reach you shortly ***");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fillComplaint() throws IOException {

		final String type = "Complaint";

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

		String sql = "insert into feedback values(?,?,?,?,?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setString(2, name);
			st.setString(3, phone);
			st.setLong(4, licenseNumber);
			st.setString(5, message);
			st.setString(6, usertype);
			st.setString(7, type);
			int result = st.executeUpdate();

			if (result == 0)
				System.out.println("Please Try Again Later");
			if (result == 1)
				System.out.println("*** Thanks for your valualble feedback. We will reach you shortly ***");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
