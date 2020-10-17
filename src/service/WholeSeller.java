package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.LicenseDAO;
import dao.WholeSellerDAO;

public class WholeSeller {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	WholeSellerDAO wsdao = new WholeSellerDAO();
	LicenseDAO lndao = new LicenseDAO();

	public void Login() {
		// TODO Auto-generated method stub
		int c;

		do {

			System.out.println("<--------------------------------------------------->");
			System.out.println("<         Welcome to the WholeSeller Portal               ");
			System.out.println("<--------------------------------------------------->");

			System.out.println("1. Set Price");
			System.out.println("2. Buy");
			System.out.println("3. Sell");
			System.out.println("4. Apply for License Renewal/Status");
			System.out.println("5. FeedBack/Complaint");
			System.out.println("6. Exit");

			System.out.println("\nEnter your choice");
			int ch = 0;
			try {
				ch = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			c = ch;

			switch (ch) {

			case 1:

				System.out.println("1. Set Buy Price: ");
				System.out.println("2. Set Sell Price: ");

				int p = 0;
				try {
					p = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				switch (p) {
				case 1:
					wsdao.setBuyPrice();
					break;

				case 2:
					wsdao.setSellPrice();
					break;
				default:
					break;
				}

				System.out.println("\n***** Price Successfully updated *****\n");
				break;
			case 2:
				wsdao.Buy();
				break;

			case 3:
				wsdao.Sell();
				break;

			case 4:

				System.out.println("1. Apply for License Renew");
				System.out.println("2. Check Status");
				System.out.println("Enter your choice: ");

				int ch1 = 0;
				try {
					ch1 = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Please Enter Valid Choice");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				final String type = "WholeSeller";
				switch (ch1) {
				case 1:
					lndao.licenseRenewal(type);
					break;

				case 2:
					lndao.licenseStatus(type);
					break;

				default:
					break;
				}

				break;

			case 5:
				System.out.println("1.Feedback");
				System.out.println("2.Complaint");
				System.out.println("Enter your choice: ");

				int i = 0;
				try {
					i = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Please Enter Valid Choice");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (i) {
				case 1:
					try {
						wsdao.fillFeedback();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 2:
					try {
						wsdao.fillComplaint();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
				break;

			case 6:
				break;

			default:
				System.out.println("Invalid Input");

			}

		} while (c != 6);
	}

}
