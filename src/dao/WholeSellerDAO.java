package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.WholeSeller;
import utility.ConnectionManager;
import utility.FeedBack;
import utility.License;
import utility.Trade;

public class WholeSellerDAO extends FeedBack implements Trade, License {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CropDAO cropdao = new CropDAO();
	UserDAO userdao = new UserDAO();

	public void AddWholeSeller() throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String sql = "select userdetails.id id_1, wholeseller.wholesellerid from userdetails left join wholeseller on userdetails.id = wholeseller.wholesellerid where type = 'WholeSeller'";
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
				WholeSeller ws = new WholeSeller();
				String id = rs.getString(2);

				if (id == null) {

					ws.setWholeSellerID(rs.getInt(1));
					System.out.println("Assign License Number to User" + userdao.getUserById(rs.getInt(1)) + ": ");
					ws.setLicenseNumber(Long.parseLong(br.readLine()));
					add(ws);
				}

			}
			System.out.println("*** No New Account Request for WholeSeller ***");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void add(WholeSeller ws) {

		String sql = "Insert into WholeSeller values (?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, ws.getLicenseNumber());
			st.setLong(2, ws.getWholeSellerID());
			st.executeUpdate();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("License Number is already Assigned");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("*** WholeSellers are successfully added ***");

	}

	public void viewList() {

		String sql = "Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join WholeSeller on WholeSeller.WholeSellerID = userdetails.id";
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
					"Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join wholeseller on WholeSeller.WholeSellerID = userdetails.id where WholeSeller.licensenumber = ?");
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

	public void removeWholeSeller(String readLine) {

		String sql = "Delete from wholeseller where licensenumber = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, Long.parseLong(readLine));
			st.executeUpdate();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("WholeSeller is removed by License Number: " + readLine);

	}

	public void setBuyPrice() {

		String sql = "select cropid from rate";
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Enter Buy Price for " + cropdao.getCropbyid(id));
				int bp = Integer.parseInt(br.readLine());
				setbp(id, bp);
			}

			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setbp(int id, int bp) {

		String sql = "update rate set buyprice =" + bp + "where cropid =" + id;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			st.executeQuery(sql);
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setSellPrice() {

		String sql = "select cropid from rate";
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Enter Sell Price for " + cropdao.getCropbyid(id));
				int sp = Integer.parseInt(br.readLine());
				setsp(id, sp);
			}

			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setsp(int id, int sp) {

		String sql = "update rate set sellprice=" + sp + " where cropid = " + id;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			st.executeQuery(sql);
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

	}

	@Override
	public void Buy() {

		String sql = "select * from trade where status = 'Open' and type = 'Sell'";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs == null) {
				System.out.println("\n*** No Sell Orders ***\n");
			}

			while (rs.next()) {
				int orderid = rs.getInt(1);
				int cropid = rs.getInt(2);
				int ln = rs.getInt(3);
				int price = rs.getInt(5);
				int quan = rs.getInt(6);

				System.out.println("**********************");
				System.out.println("OrderID: " + orderid + "\nCrop Name: " + cropdao.getCropbyid(cropid)
						+ "\nLicense Number: " + ln + "\nPrice: " + price + "\nQuantity: " + quan);
				System.out.println("**********************");
			}

			ConnectionManager.getConnection().close();

			System.out.println("Select Order ID to complete the transaction");
			int id = Integer.parseInt(br.readLine());

			completeBuy(id);

		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void completeBuy(int id) {
		String sql = "update trade set status = 'Completed' where id = " + id;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			st.executeQuery(sql);

			ConnectionManager.getConnection().close();

			System.out.println("*** YaY!! Trasaction Completed ***");
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

		String sql = "select * from trade where status = 'Open' and type = 'Buy'";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					int orderId = rs.getInt(1);
					int cropId = rs.getInt(2);
					int ln = rs.getInt(3);
					int price = rs.getInt(5);
					int quan = rs.getInt(6);

					System.out.println("**********************");
					System.out.println("OrderID: " + orderId + "\nCrop Name: " + cropdao.getCropbyid(cropId)
							+ "\nLicense Number: " + ln + "\nPrice: " + price + "\nQuantity: " + quan);
					System.out.println("**********************");
				}
				ConnectionManager.getConnection().close();

				System.out.println("Select Order ID to complete the transaction");
				int id = Integer.parseInt(br.readLine());

				completeSell(id);
			} else
				System.out.println("\n*** No Buy Order ***\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void completeSell(int id) {

		String sql = "update trade set status = 'Completed' where id = " + id;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			st.executeQuery(sql);

			ConnectionManager.getConnection().close();

			System.out.println("*** YaY!! Trasaction Completed ***");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
