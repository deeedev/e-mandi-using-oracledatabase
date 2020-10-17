package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class CropDAO {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void addcrops() {

		String sql = "Insert into crops(id,name) values(?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			System.out.println("Enter Crops ID: ");
			int id = Integer.parseInt(br.readLine());
			st.setInt(1, id);
			System.out.println("Enter Crops Name: ");
			String name = br.readLine();
			st.setString(2, name);

			st.executeUpdate();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("Crop ID is already Registered!!");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void removecrops() {

		String sql = "Delete from crops where id = ?";
		int id = 0;
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			System.out.println("Enter Crops ID: ");
			id = Integer.parseInt(br.readLine());
			st.setInt(1, id);
			st.executeUpdate();

			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Crop is removed by ID: " + id);

	}

	public void setMSP() {
		String sql = "select crops.id,crops.name,rate.cropid from crops left join rate on crops.id = rate.cropid";
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String cropid = rs.getString(3);

				if (cropid != null) {
					System.out.println("Enter Minimum Support Price for " + name);
					int msp = Integer.parseInt(br.readLine());
					updaterate(id, msp);
				}
				if (cropid == null) {
					System.out.println("Enter Minimum Support Price for " + name);
					int msp = Integer.parseInt(br.readLine());
					addrate(id, msp);
				}
			}
			System.out.println("*** Minimum Support Price Updated ***");
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void updaterate(int id, int msp) {

		String sql = "update rate set msp =" + msp + " where cropid =" + id;

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			st.executeQuery(sql);
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addrate(int id, int msp) {
		String sql = "Insert into rate(cropid,msp) values(?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, id);
			st.setInt(2, msp);
			st.executeUpdate();

			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCropbyid(int id) {

		String sql = "select name from crops where id =" + id;
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString(1);
				return name;
			}

			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getMSP() {
		String sql = "Select cropid,msp from rate";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			int i = 1;
			System.out.println(" Crop Name \t MSP");
			while (rs.next()) {

				System.out.println(i + ". " + getCropbyid(rs.getInt(1)) + "\t" + rs.getInt(2));
				i++;
			}
			System.out.println();
			ConnectionManager.getConnection().close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getPrice() {

		String sql = "Select cropid,sellprice from rate";

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Crop ID \t Crop Name \t Price");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + getCropbyid(rs.getInt(1)) + "\t" + rs.getInt(2));

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

	public int getSellPriceByID(int sellId) {

		String sql = "select sellprice from rate where cropid = ?";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, sellId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int sellPrice = rs.getInt(1);
				return sellPrice;
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
		return 0;
	}

	public int getBuyPriceByID(int sellId) {

		String sql = "select buyprice from rate where cropid = ?";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, sellId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int buyPrice = rs.getInt(1);
				return buyPrice;
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
		return 0;
	}

}
