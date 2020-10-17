package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class FeedbackDAO {

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

	public void feedback(String name, String phone, long licenseNumber, String usertype, String message) {
		final String type = "Feedback";
		int i = getid();
		final String status = "Open";

//		System.out.println(++i);
//		System.out.println(name);
//		System.out.println(phone);
//		System.out.println(licenseNumber);
//		System.out.println(message);
//		System.out.println(usertype);
//		System.out.println(type);

		String sql = "insert into feedback values(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setString(2, name);
			st.setString(3, phone);
			st.setLong(4, licenseNumber);
			st.setString(5, message);
			st.setString(6, usertype);
			st.setString(7, type);
			st.setString(8, status);
			int result = st.executeUpdate();

			if (result == 0)
				System.out.println("Please Try Again Later");
			if (result == 1)
				System.out.println("\n*** Thanks for your valualble feedback. We will reach you shortly ***\n");
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

	public void complaint(String name, String phone, long licenseNumber, String usertype, String message) {
		final String type = "Complaint";
		int i = getid();
		final String status = "Open";

		String sql = "insert into feedback values(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setString(2, name);
			st.setString(3, phone);
			st.setLong(4, licenseNumber);
			st.setString(5, message);
			st.setString(6, usertype);
			st.setString(7, type);
			st.setString(8, status);
			int result = st.executeUpdate();

			if (result == 0)
				System.out.println("Please Try Again Later");
			if (result == 1)
				System.out.println("\n*** Thanks for your valualble feedback. We will reach you shortly ***\n");
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

	public void checkFeedback() {

		String sql = "Select * from feedback where type = 'Feedback";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				long ln = rs.getLong(4);
				String message = rs.getString(5);
				String usertype = rs.getString(6);

				System.out.println("*************************************");
				System.out.println("FeedBack ID: \t" + id + "\nUser Type: \t" + usertype + "\nName: \t" + name
						+ "\nPhone Number: \t" + phone + "\nLicense Number: \t" + ln + "\nMessage: \t" + message);
				System.out.println("*************************************");

			}
			System.out.println();
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

	public void checkComplaint() {
		String sql = "Select * from feedback where type = 'Complaint";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				long ln = rs.getLong(4);
				String message = rs.getString(5);
				String usertype = rs.getString(6);

				System.out.println("*************************************");
				System.out.println("FeedBack ID: \t" + id + "\nUser Type: \t" + usertype + "\nName: \t" + name
						+ "\nPhone Number: \t" + phone + "\nLicense Number: \t" + ln + "\nMessage: \t" + message);
				System.out.println("*************************************");

			}
			System.out.println();
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
