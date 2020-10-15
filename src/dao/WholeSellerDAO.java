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

public class WholeSellerDAO {

	public void AddWholeSeller() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String sql = "select userdetails.id id_1, wholeseller.wholesellerid from userdetails left join wholeseller on userdetails.id = wholeseller.wholesellerid where type = 'WholeSeller'";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(WholeSeller ws) {
		// TODO Auto-generated method stub

		String sql = "Insert into WholeSeller values (?,?)";
		try {
			PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
			st.setLong(1, ws.getLicenseNumber());
			st.setLong(2, ws.getWholeSellerID());
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
		String sql = "Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join WholeSeller on WholeSeller.WholeSellerID = userdetails.id";
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
					"Select Firstname,lastname,email,phonenumber,address,licensenumber from userdetails join wholeseller on WholeSeller.WholeSellerID = userdetails.id where WholeSeller.licensenumber = ?");
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

	public void removeWholeSeller(String readLine) {
		// TODO Auto-generated method stub

		String sql = "Delete from wholeseller where licensenumber = ?";

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

		System.out.println("WholeSeller is remover by License Number: " + readLine);
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
