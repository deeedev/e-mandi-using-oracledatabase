package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.CropDAO;
import dao.FarmerDAO;

public class Farmer {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FarmerDAO famdao = new FarmerDAO();
	CropDAO cropdao = new CropDAO();

	public void Login() throws NumberFormatException, IOException {

		System.out.println("<--------------------------------------------------->");
		System.out.println("<         Welcome to the Farmer Portal               ");
		System.out.println("<--------------------------------------------------->");
		int ch;

		do {

			System.out.println("1. Sell Corps");
			System.out.println("2. Know Minimum Support Price");
			System.out.println("3. Apply for License Renewal/Status");
			System.out.println("4. FeedBack/Complaint");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(br.readLine());
			ch = choice;

			switch (choice) {
			case 1:
				famdao.Sell();
				break;

			case 2:
				cropdao.getMSP();
				break;

			case 3:

				System.out.println("1. Apply for License Renew");
				System.out.println("2. Check Status");
				System.out.println("Enter your choice: ");

				break;

			case 4:

				System.out.println("1.Feedback");
				System.out.println("2.Complaint");
				System.out.println("Enter your choice: ");

				break;

			case 5:
				break;

			}
		} while (ch != 5);

	}

}
