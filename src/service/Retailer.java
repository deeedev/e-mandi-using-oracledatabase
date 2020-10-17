package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.CropDAO;
import dao.LicenseDAO;
import dao.RetailerDAO;

public class Retailer {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	RetailerDAO retaildao = new RetailerDAO();
	CropDAO cropdao = new CropDAO();
	LicenseDAO lndao = new LicenseDAO();

	public void Login() throws NumberFormatException, IOException {

		System.out.println("<--------------------------------------------------->");
		System.out.println("<         Welcome to the Retailer Portal               ");
		System.out.println("<--------------------------------------------------->");

		int ch;

		do {

			System.out.println("1. Buy Corps");
			System.out.println("2. Know Buy Price");
			System.out.println("3. Apply for License Renewal/Status");
			System.out.println("4. FeedBack/Complaint");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(br.readLine());
			ch = choice;

			switch (choice) {
			case 1:
				retaildao.Buy();
				break;

			case 2:
				cropdao.getPrice();
				break;

			case 3:

				System.out.println("1. Apply for License Renew");
				System.out.println("2. Check Status");
				System.out.println("Enter your choice: ");

				int ch1 = Integer.parseInt(br.readLine());
				final String type = "Retailer";

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

			case 4:

				System.out.println("1.Feedback");
				System.out.println("2.Complaint");
				System.out.println("Enter your choice: ");

				int i = Integer.parseInt(br.readLine());
				switch (i) {
				case 1:
					retaildao.fillFeedback();
					break;

				case 2:
					retaildao.fillComplaint();
					break;
				default:
					break;
				}
				break;

			case 5:
				break;

			}
		} while (ch != 5);

	}

}
