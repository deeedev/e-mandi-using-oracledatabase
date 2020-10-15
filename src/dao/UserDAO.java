package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDAO {

	public void signUp(User user) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		String sql = "Insert into userdetails values(?,?,?,?,?,?,?,?,?)";

		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		st.setInt(1, user.getId());
		st.setString(2, user.getFirstName());
		st.setString(3, user.getLastName());
		st.setString(4, user.getEmail());
		st.setString(5, user.getPhoneNumber());
		st.setString(6, user.getAddress());
		st.setString(7, user.getUsername());
		st.setString(8, user.getPassword());
		st.setString(9, user.getType());

		int rs = st.executeUpdate();
		System.out.println(rs);
		ConnectionManager.getConnection().close();
	}

	public int getId() {
		// TODO Auto-generated method stub
		int i = 0;

		Statement st = null;
		try {
			st = ConnectionManager.getConnection().createStatement();
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

		String sql = "select id from userdetails where rownum <= 1 order by id desc";

		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			try {
				ConnectionManager.getConnection().close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (rs.next())
				try {

					i = rs.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	public boolean loginAdminUser(String aduser, String adpass) {
		// TODO Auto-generated method stub
		try {
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from admin where username = ? and password = ? ");

			preparedStatement.setString(1, aduser);
			preparedStatement.setString(2, adpass);

			int rs = preparedStatement.executeUpdate();
			connection.close();
			if (rs != 0) {
				return true;
			}

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;
	}

	public boolean loginWholeSeller(String wsuser, String wspass) {
		// TODO Auto-generated method stub

		try {
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Step 2:Create a statement using connection object
			Statement createStatement = connection.createStatement();

			ResultSet rs = createStatement.executeQuery(
					"Select username, password from userdetails join wholeseller on wholeseller.wholesellerid = userdetails.id");

			while (rs.next()) {

				if (rs.getString(1).equals(wsuser) && rs.getString(2).equals(wspass))
					return true;
			}
			connection.close();
		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;

	}

	public boolean loginFarmer(String Fuser, String Fpass) {
		// TODO Auto-generated method stub
		try {
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Step 2:Create a statement using connection object
			Statement createStatement = connection.createStatement();

			ResultSet rs = createStatement.executeQuery(
					"Select username, password from userdetails join Farmer on Farmer.Farmerid = userdetails.id");

			while (rs.next()) {

				if (rs.getString(1).equals(Fuser) && rs.getString(2).equals(Fpass))
					return true;
			}
			connection.close();
		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;
	}

	public boolean loginRetailer(String ruser, String rpass) {
		// TODO Auto-generated method stub
		try {
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Step 2:Create a statement using connection object
			Statement createStatement = connection.createStatement();

			ResultSet rs = createStatement.executeQuery(
					"Select username, password from userdetails join Retailer on Retailer.Retailerid = userdetails.id");

			while (rs.next()) {

				if (rs.getString(1).equals(ruser) && rs.getString(2).equals(rpass))
					return true;
			}
			connection.close();
		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;
	}

}
