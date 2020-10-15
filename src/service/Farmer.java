package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Farmer {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void Login() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int ch;

		do {

			System.out.println("<--------------------------------------------------->");
			System.out.println("<         Welcome to the Farmer Portal               ");
			System.out.println("<--------------------------------------------------->");

			System.out.println("1. Sell Corps");
			System.out.println("2. Know Minimum Support Price");
			System.out.println("3. Apply for License Renewal/Status");
			System.out.println("4. FeedBack/Complaint");
			System.out.println("5. Exit");

			int choice = Integer.parseInt(br.readLine());
			ch = choice;

			switch (choice) {
			case 1:

				System.out.println("1. Buy Order Posted by WholeSeller");
				System.out.println("2. Post your sell order");
				System.out.println("3. Exit");
				System.out.println("Enter your choice: ");

				break;

			case 2:

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
		} while (ch != 6);

	}

}
