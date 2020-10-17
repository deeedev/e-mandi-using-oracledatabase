package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Crops;
import model.Farmer;
import utility.ConnectionManager;
import utility.FeedBack;
import utility.License;
import utility.Trade;

public class FarmerDAO extends FeedBack implements Trade, License {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CropDAO cropdao = new CropDAO();
	TradeDAO tradedao = new TradeDAO();
	UserDAO userdao = new UserDAO();

	public void addFarmer() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String sql = "select userdetails.id id_1, farmer.farmerid from userdetails left join farmer on userdetails.id = farmer.farmerid where type = 'Farmer'";
		ResultSet rs = null;
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			rs = st.executeQuery(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Farmer fam = new Farmer();
				String id = rs.getString(2);

				if (id == null) {

					fam.setFarmerID(rs.getInt(1));
					System.out.println("Assign License Number to User" + userdao.getUserById(rs.getInt(1)) + ": ");
					try {
						fam.setLicenseNumber(Long.parseLong(br.readLine()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					add(fam);
				}

			}
			System.out.println("*** No New Account Request for Farmer ***");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void add(Farmer fam) {

		String sql = "Insert into Farmer values (?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, fam.getLicenseNumber());
			st.setLong(2, fam.getFarmerID());
			st.executeUpdate();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("License Number is already Assigned");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("*** Farmers are successfully added ***");
	}

	public void viewList() {

		String sql = "Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join Farmer on Farmer.FarmerID = userdetails.id";
		Statement st;
		ResultSet rs = null;
		try {
			st = ConnectionManager.getConnection().createStatement();
			rs = st.executeQuery(sql);
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		}

	}

	public void searchByLicenseNumber(String readLine) {

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = ConnectionManager.getConnection().prepareStatement(
					"Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join Farmer on Farmer.FarmerID = userdetails.id where Farmer.licensenumber = ?");
			preparedStatement.setLong(1, Long.parseLong(readLine));
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		}

	}

	public void removeFarmer(String readLine) {

		String sql = "Delete from Farmer where licensenumber = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, Long.parseLong(readLine));
			st.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Farmer is removed by License Number: " + readLine);
		try {
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void licenseRenewal() {
		final String status = "open";
		final String type = "Farmer";
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

	@Override
	public void Buy() {

	}

	@Override
	public void Sell() {

		String sql = "select cropid,msp,buyprice from rate";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			System.out.println("CropID \t CropName \t MSP \t SellPrice");
			while (rs.next()) {

				int id = rs.getInt(1);
				String name = cropdao.getCropbyid(id);
				int msp = rs.getInt(2);
				int price = rs.getInt(3);

				System.out.println(id + "\t" + name + "\t" + msp + "\t" + price);

			}

			System.out.println("Enter Crop Id to Sell");
			int sellId = Integer.parseInt(br.readLine());
			System.out.println("Enter Quantity: ");
			int quantity = Integer.parseInt(br.readLine());
			int price = cropdao.getBuyPriceByID(sellId);

			Crops crop = new Crops(sellId, price, quantity);
			boolean result = tradedao.sellcrop(crop);

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
	public void licenseStatus() {

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

				if (status == "Open")
					System.out.println("*** License Renewal is Pending ***");
				if (status == "Closed")
					System.out.println("*** License Renewal Rejected ***");
				if (status == "Completed")
					System.out.println("*** License Renewal Approved ***");
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

}
