package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Crops;
import utility.ConnectionManager;

public class TradeDAO {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int i = getId();

	public boolean sellcrop(Crops crop) throws NumberFormatException, IOException {

		final String status = "Open";
		final String type = "Sell";
		int result = 0;

		System.out.println("Enter You License Number: ");
		long ln = Long.parseLong(br.readLine());

		String sql = "Insert into trade values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setLong(2, crop.getCropID());
			st.setLong(3, ln);
			st.setString(4, type);
			st.setLong(5, crop.getPrice());
			st.setLong(6, crop.getQuantity());
			st.setString(7, status);

			result = st.executeUpdate();
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

		if (result == 0)
			return false;
		else
			return true;

	}

	private int getId() {

		int i = 0;

		String sql = "select id from trade where rownum <= 1 order by id desc";
		Statement st = null;
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
				i = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return i;

	}

	public boolean Buycrop(Crops crop) {

		final String status = "Open";
		final String type = "Buy";
		int result = 0;

		System.out.println("Enter You License Number: ");
		long ln = 0;
		try {
			ln = Long.parseLong(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "Insert into trade values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setInt(1, ++i);
			st.setLong(2, crop.getCropID());
			st.setLong(3, ln);
			st.setString(4, type);
			st.setLong(5, crop.getPrice());
			st.setLong(6, crop.getQuantity());
			st.setString(7, status);

			result = st.executeUpdate();

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

		if (result == 0)
			return false;
		else
			return true;

	}

}
