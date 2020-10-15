package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.FarmerDAO;
import dao.RetailerDAO;
import dao.WholeSellerDAO;

public class Admin {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	WholeSellerDAO wsdao = new WholeSellerDAO();
	FarmerDAO famdao = new FarmerDAO();
	RetailerDAO retaildao = new RetailerDAO();

	public void Login() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		int c1;

		do {
			System.out.println();
			System.out.println("1. Add/Remove WholeSellers");
			System.out.println("2. Add/Remove Farmers");
			System.out.println("2. Add/Remove Retailer");
			System.out.println("3. Set Minimum Support Price");
			System.out.println("4. Approve License Renewals");
			System.out.println("5. View FeedBack or Complaint");
			System.out.println("6. Exit");
			System.out.println("\nEnter your choices: ");

			int ch = Integer.parseInt(br.readLine());
			c1 = ch;
			switch (ch) {

//Admin case 1 starts here!!!!

			case 1:
				int c2;

				do {
					System.out.println("\n1. Add WholeSeller");
					System.out.println("2. View WholeSeller List / By Liscense Number");
					System.out.println("3. Remove WholeSeller By Liscense Number");
					System.out.println("4. Exit\n");
					System.out.println("Enter your choices: ");

					int c = Integer.parseInt(br.readLine());
					c2 = c;

					switch (c) {

					case 1:
						wsdao.AddWholeSeller();
						break;

					case 2:

						System.out.println("1. View Whole Seller List");
						System.out.println("2. Search By License Number\n");

						int n = Integer.parseInt(br.readLine());

						switch (n) {

						case 1:

							wsdao.viewList();
							break;

						case 2:

							System.out.println("Enter the License Number: ");
							wsdao.searchByLicenseNumber(br.readLine());
							break;

						default:
							System.out.println("Invalid Input");
							break;
						}
						break;

					case 3:

						System.out.println("Enter the License Number: ");
						wsdao.removeWholeSeller(br.readLine());
						break;

					case 4:
						break;

					default:
						System.out.println("Invalid Input");
						break;
					}

				} while (c2 != 4);
				break;

//Admin Case 2 starts here !!!

			case 2:

				int d1;
				do {

					System.out.println("1. Add Farmer");
					System.out.println("2. View Farmer List / By Liscense Number");
					System.out.println("3. Remove Farmer By Liscense Number");
					System.out.println("4. Exit\n");
					System.out.println("Enter your choices: ");

					int d = Integer.parseInt(br.readLine());
					d1 = d;
					switch (d) {

					case 1:
						famdao.addFarmer();
						break;

					case 2:

						System.out.println("1. View Farmer List");
						System.out.println("2. Search By License Number\n");

						int d2 = Integer.parseInt(br.readLine());

						switch (d2) {

						case 1:

							famdao.viewList();
							break;

						case 2:

							System.out.println("Enter License Number: ");
							famdao.searchByLicenseNumber(br.readLine());
							break;

						default:
							System.out.println("Invalid Input");
							break;

						}
						break;

					case 3:

						System.out.println("Enter License Number: ");
						famdao.removeFarmer(br.readLine());
						break;

					case 4:
						break;

					default:
						System.out.println("Invalid Input");
						break;
					}

					break;

				} while (d1 != 4);
				break;

//ADmin case 3 starts here!!!

			case 3:

				int e1;

				do {

					System.out.println("1. Add Retailer");
					System.out.println("2. View Retaier List / By Liscense Number");
					System.out.println("3. Remove Retailer By Liscense Number");
					System.out.println("4. Exit\n");
					System.out.println("Enter your choices: ");

					int e = Integer.parseInt(br.readLine());
					d1 = e;
					switch (e) {

					case 1:
						retaildao.addRetailer();
						break;

					case 2:

						System.out.println("1. View Retailer List");
						System.out.println("2. Search By License Number\n");

						int d2 = Integer.parseInt(br.readLine());

						switch (d2) {

						case 1:

							retaildao.viewList();
							break;

						case 2:

							System.out.println("Enter License Number: ");
							retaildao.searchByLicenseNumber(br.readLine());
							break;

						default:
							System.out.println("Invalid Input");
							break;

						}
						break;

					case 3:

						System.out.println("Enter License Number: ");
						retaildao.removeFarmer(br.readLine());
						break;

					case 4:
						break;

					default:
						System.out.println("Invalid Input");
						break;
					}

					break;

				} while (e1 != 4);
				break;

//ADmin case 4 starts here !!!
			case 4:
				break;

			}
		} while (c1 != 6);
	}
}
