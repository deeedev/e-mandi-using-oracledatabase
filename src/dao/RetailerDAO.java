package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Retailer;
import utility.ConnectionManager;

public class RetailerDAO {

	public void addRetailer() {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String sql = "select userdetails.id id_1, retailer.retailerID from userdetails left join retailer on userdetails.id = retailer.retailerid where type = 'Retailer'";
		ResultSet rs = null;
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			rs = st.executeQuery(sql);
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

		try {
			while (rs.next()) {
				Retailer retail = new Retailer();
				String id = rs.getString(2);

				if (id == null) {

					retail.setRetailerID(rs.getInt(1));
					System.out.println("Enter License Number: ");
					try {
						retail.setLicenseNumber(Long.parseLong(br.readLine()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					add(retail);
				}

			}
			System.out.println("*** WholeSellers are successfully added ***");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(Retailer retail) {
		// TODO Auto-generated method stub

		String sql = "Insert into WholeSeller values (?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, retail.getLicenseNumber());
			st.setLong(2, retail.getRetailerID());
			st.executeUpdate();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("License Number is already Assigned");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void viewList() {
		// TODO Auto-generated method stub

		String sql = "Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join Retailer on retailer.retailerid = userdetails.id";
		Statement st;
		ResultSet rs = null;
		try {
			st = ConnectionManager.getConnection().createStatement();
			rs = st.executeQuery(sql);
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

		try {
			while (rs.next()) {
				System.out.println("*************************");
				System.out.println("FirstName: " + rs.getString(1) + "\nLastName: " + rs.getString(2) + "\nEmail: "
						+ rs.getString(3) + "\nPhone Number: " + rs.getString(4) + "\nAddress: " + rs.getString(5)
						+ "\nLicense Number: " + rs.getString(6));
				System.out.println("*************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void searchByLicenseNumber(String readLine) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = ConnectionManager.getConnection().prepareStatement(
					"Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join retailer on retailer.retailer.id = userdetails.id where retailer.licensenumber = ?");
			preparedStatement.setLong(1, Long.parseLong(readLine));
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

		try {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				System.out.println("*************************");
				System.out.println("FirstName: " + rs.getString(1) + "\nLastName: " + rs.getString(2) + "\nEmail: "
						+ rs.getString(3) + "\nPhone Number: " + rs.getString(4) + "\nAddress: " + rs.getString(5));
				System.out.println("*************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeFarmer(String readLine) {
		// TODO Auto-generated method stub

		String sql = "Delete from retailer where licensenumber = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, Long.parseLong(readLine));
			st.executeUpdate();
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

		System.out.println("Retailer is removed by License Number: " + readLine);
		try {
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

	}

}
