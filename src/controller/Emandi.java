package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import businessLogic.SignUpUser;
import dao.UserDAO;
import service.Admin;
import service.Farmer;
import service.Retailer;
import service.WholeSeller;

public class Emandi {

	public static void main(String[] args)
			throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserDAO userdao = new UserDAO();

		int n1;

		do {
			System.out.println("<------------------------------------------------------------------->");
			System.out.println("               Welcome to the e-mandi service portal                 ");
			System.out.println("<------------------------------------------------------------------->");

			System.out.println("\n1. SignUp User");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("\nEnter your choices");

			int n = Integer.parseInt(br.readLine());
			n1 = n;

			switch (n) {
			case 1:

				SignUpUser signup = new SignUpUser();
				signup.getSignUpDetails();
				break;

			case 2:
				int o1;
				do {

					System.out.println("1. Admin Login");
					System.out.println("2. WholeSeller Login");
					System.out.println("3. Farmer Login");
					System.out.println("4. Retailer Login");
					System.out.println("5. Exit");
					System.out.println("\nEnter your choices");

					int o = Integer.parseInt(br.readLine());
					o1 = o;

					switch (o) {
					case 1:
						Admin admin = new Admin();

						System.out.println("Enter Username: ");
						String Aduser = br.readLine();
						System.out.println("Enter Password: ");
						String Adpass = br.readLine();

						boolean validate = userdao.loginAdminUser(Aduser, Adpass);

						if (validate)
							admin.Login();
						else
							System.out.println("Invalid UserName/Password\n");
						break;

					case 2:
						WholeSeller WS = new WholeSeller();

						// final String type1 = "WholeSeller";

						System.out.println("Enter Username: ");
						String Wsuser = br.readLine();
						System.out.println("Enter Password: ");
						String Wspass = br.readLine();

						boolean validate1 = userdao.loginWholeSeller(Wsuser, Wspass);

						if (validate1)
							WS.Login();
						else
							System.out.println("Invalid UserName/Password\n");
						break;

					case 3:
						Farmer fam = new Farmer();

						System.out.println("Enter Username: ");
						String Fuser = br.readLine();
						System.out.println("Enter Password: ");
						String Fpass = br.readLine();

						boolean validate2 = userdao.loginFarmer(Fuser, Fpass);
						if (validate2)
							fam.Login();
						else
							System.out.println("Invalid UserName/Password\n");
						break;

					case 4:
						Retailer retail = new Retailer();

						System.out.println("Enter Username: ");
						String Ruser = br.readLine();
						System.out.println("Enter Password: ");
						String Rpass = br.readLine();

						boolean validate3 = userdao.loginRetailer(Ruser, Rpass);
						if (validate3)
							retail.Login();
						else
							System.out.println("Invalid UserName/Password\n");
						break;
					default:
						break;
					}

				} while (o1 != 5);
				break;

			default:
				break;

			}

		} while (n1 != 3);
	}

}
