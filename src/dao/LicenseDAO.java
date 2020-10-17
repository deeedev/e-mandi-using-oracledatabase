package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;
import utility.License;

public class LicenseDAO implements License {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void licenseRenewal(String type) {
		final String status = "open";
		// final String type = "Farmer";
		int i = getId();
		int ln = 0;
		System.out.println("Enter Your License Number: ");
		try {
			ln = Integer.parseInt(br.readLine());

		} catch (NumberFormatException e) {
			System.out.println("Please Enter Valid License Number");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "insert into license values(?,?,?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setInt(2, ln);
			st.setString(3, type);
			st.setString(4, status);
			st.executeUpdate();
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

		System.out.println("*** Your request has been sent ***");

	}

	@Override
	public void licenseStatus(String type) {

		int ln = 0;
		System.out.println("Enter your license Number: ");
		try {
			ln = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Please Enter Valid License Number");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "Select status from license where licensenumber = " + ln;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				String status = rs.getString(1);

				if (status == "open") {
					System.out.println("*** License Renewal is Pending ***\n");
				} else if (status == "Closed") {
					System.out.println("*** License Renewal Rejected ***\n");
				} else if (status == "Completed") {
					System.out.println("*** License Renewal Approved ***\n");
				}

			}
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

	private int getId() {

		String sql = "Select requestid from license where rownum<=1 order by requestid desc";

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

	public void checkLicenseRenew() {

		String sql = "Select * from license where status = 'open'";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				int id = rs.getInt(1);
				int licenseN = rs.getInt(2);
				String type = rs.getString(3);
				String status = rs.getString(4);

				System.out.println("***************************");
				System.out.println("Request ID: \t" + id);
				System.out.println("License Number: \t" + licenseN);
				System.out.println("User Tyepe: \t" + type);
				System.out.println("Status: \t" + status);
				System.out.println("***************************");
			}
			System.out.println("Select Request Id to Approve/Reject");
			int id1 = Integer.parseInt(br.readLine());
			System.out.println("1. Approve \t 2. Reject");
			int s = Integer.parseInt(br.readLine());
			completeRenewal(id1, s);
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

	private void completeRenewal(int id, int s) {

		if (s == 1) {

			String sql = "update license set status = 'Completed' where requestid = " + id;

			try {
				Statement st = ConnectionManager.getConnection().createStatement();
				st.executeQuery(sql);

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

		} else {
			String sql = "update license set status = 'Closed' where requestid = " + id;

			try {
				Statement st = ConnectionManager.getConnection().createStatement();
				st.executeQuery(sql);

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

		System.out.println("*** License Renwal Status Updated ***");
	}

}
