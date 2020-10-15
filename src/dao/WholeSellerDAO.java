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
					System.out.println("Enter License Number: ");
					ws.setLicenseNumber(Long.parseLong(br.readLine()));
					add(ws);
				}

			}
			System.out.println("*** WholeSellers are successfully added ***");
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

		String sql = "insert into rate(buyprice) value(?) where id = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, bp);
			st.setInt(2, id);
			st.executeUpdate();
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

		String sql = "insert into rate(sellprice) value(?) where id = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, sp);
			st.setInt(2, id);
			st.executeUpdate();
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

		String sql = "select * from trade where status = null and type = 'Sell'";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				System.out.println("**********************");
				System.out.println("OrderID: " + rs.getInt(1) + "\nCrop Name: " + cropdao.getCropbyid(rs.getInt(2))
						+ "\nLicense Number: " + rs.getInt(3) + "\nPrice: " + rs.getInt(5) + "\nQuantity: "
						+ rs.getInt(6));
				System.out.println("**********************");
			}
			ConnectionManager.getConnection().close();

			System.out.println("Select Order ID to complete the transaction");
			int id = Integer.parseInt(br.readLine());

			completeBuy(id);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void completeBuy(int id) {
		String sql = "insert into trade(status) values(?) where id = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setString(1, "Completed");
			st.setInt(2, id);

			st.executeUpdate();

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

		String sql = "select * from trade where status = null and type = 'Buy'";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				System.out.println("**********************");
				System.out.println("OrderID: " + rs.getInt(1) + "\nCrop Name: " + cropdao.getCropbyid(rs.getInt(2))
						+ "\nLicense Number: " + rs.getInt(3) + "\nPrice: " + rs.getInt(5) + "\nQuantity: "
						+ rs.getInt(6));
				System.out.println("**********************");
			}
			ConnectionManager.getConnection().close();

			System.out.println("Select Order ID to complete the transaction");
			int id = Integer.parseInt(br.readLine());

			completeSell(id);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void completeSell(int id) {

		String sql = "insert into trade(status) values(?) where id = ?";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setString(1, "Completed");
			st.setInt(2, id);

			st.executeUpdate();

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
