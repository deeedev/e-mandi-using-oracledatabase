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

		String sql = "Insert into crops value(?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			System.out.println("Enter Crops ID: ");
			st.setInt(1, Integer.parseInt(br.readLine()));
			System.out.println("Enter Crops Name: ");
			st.setString(2, br.readLine());

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Crop is removed by ID: " + id);

	}

	public void setMSP() {
		// TODO Auto-generated method stub
		String sql = "select * from crops";
		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);

				System.out.println("Enter Minimum Support Price for " + name);
				int msp = Integer.parseInt(br.readLine());
				addrate(id, msp);
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

	}

	private void addrate(int id, int msp) {
		// TODO Auto-generated method stub
		String sql = "Insert into rate(cropid,msp) values(?,?)";

		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, id);
			st.setInt(2, msp);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
