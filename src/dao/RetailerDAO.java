package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Crops;
import model.Retailer;
import utility.ConnectionManager;
import utility.FeedBack;
import utility.License;
import utility.Trade;

public class RetailerDAO extends FeedBack implements License, Trade {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CropDAO cropdao = new CropDAO();
	TradeDAO tradedao = new TradeDAO();
	UserDAO userdao = new UserDAO();

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
					System.out.println("Assign License Number to User" + userdao.getUserById(rs.getInt(1)) + ": ");
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
			System.out.println("*** No New Account Request for Retailer ***");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(Retailer retail) {
		// TODO Auto-generated method stub

		String sql = "Insert into Retailer values (?,?)";
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
		System.out.println("*** Retailers are successfully added ***");
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
					"Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join retailer on retailer.retailerid = userdetails.id where retailer.licensenumber = ?");
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

	public void removeRetailer(String readLine) {
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

	@Override
	public void Buy() {

		String sql = "select cropid,msp,sellprice from rate";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			System.out.println("CropID \t CropName \t Price");
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = cropdao.getCropbyid(id);
				int price = rs.getInt(3);

				System.out.println(id + "\t" + name + "\t" + price);

			}

			System.out.println("Enter Crop Id to Buy");
			int sellId = Integer.parseInt(br.readLine());
			System.out.println("Enter Quantity: ");
			int quantity = Integer.parseInt(br.readLine());
			int price = cropdao.getSellPriceByID(sellId);

			Crops crop = new Crops(sellId, price, quantity);
			boolean result = tradedao.Buycrop(crop);

			if (result)
				System.out.println("Order Sent to the WholeSeller");
			else
				System.out.println("Unable to Process Order. Please try again later!!");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Sell() {

	}

	@Override
	public void licenseRenewal() {

	}

}
