package businessLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class SignUpUser {

	UserDAO userdao = new UserDAO();

	int i = userdao.getId();

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void getSignUpDetails() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		User user = new User();
		user.setId(++i);

		boolean flag;

//Code to validate the First Name

		System.out.println("Enter Your First Name(Fisrt Letter In Caps): ");
		do {

			String regex = "^[A-Z][a-z]*$";
			String input = br.readLine();

			flag = input.matches(regex);

			if (!flag)
				System.out.println("Please Enter Valid First Name(Do not Use Number or Special Character): ");
			else
				user.setFirstName(input);

		} while (!flag);

//Code to validate Last Name

		System.out.println("Enter Your Last Name(Fisrt Letter In Caps): ");

		do {

			String regex = "^[A-Z][a-z]*$";
			String input = br.readLine();

			flag = input.matches(regex);

			if (!flag)
				System.out.println("Please Enter Valid Last Name(Do not Use Number or Special Character): ");
			else
				user.setLastName(input);

		} while (!flag);

//Code to validate email input

		System.out.println("Enter Your Email: ");
		do {
			String emailregex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

			String input = br.readLine();

			flag = input.matches(emailregex);
			if (!flag)
				System.out.println("Please Enter valid Email");
			else
				user.setEmail(input);
		} while (!flag);

// Code to validate Username Input	

		System.out.println("Enter Username: ");

		do {
			String regex = "\\w+";

			String input = br.readLine();

			flag = input.matches(regex);

			if (!flag)
				System.out.println("Please Enter Valid UserName(Username Must Not Contain Special Characters");
			else
				user.setUsername(input);

		} while (!flag);

//Code to validate the password input

		System.out.println(
				"Enter Password \nMin 1 uppercase letter.\nMin 1 lowercase letter.\nMin 1 special character.\nMin 1 number.\nMin 8 characters.\nMax 30 characters.: ");
		do {
			String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$";

			String input = br.readLine();

			flag = input.matches(regex);

			if (flag)
				user.setPassword(input);
			else
				System.out.println(
						"Password Requirment doesn't meet \nMin 1 uppercase letter.\\nMin 1 lowercase letter.\\nMin 1 special character.\\nMin 1 number.\\nMin 8 characters.\\nMax 30 characters.");
		} while (!flag);

// Code to validate Type Input

		System.out.println("Enter Type(Farmer/WholeSeller/Retailer): ");

		do {

			String input = br.readLine();

			flag = input.equals("Farmer") || input.equals("WholeSeller") || input.equals("Retailer");

			if (flag)
				user.setType(input);
			else
				System.out.println("Please Enter Type as Above");

		} while (!flag);

//Code to validate the phone number

		System.out.println("Enter Your Phone Number: ");

		do {

			String regex = "(0/91)?[7-9][0-9]{9}";

			String input = br.readLine();

			flag = input.matches(regex);

			if (!flag)
				System.out.println("Please Enter a valid Phone Number: ");
			else
				user.setPhoneNumber(input);

		} while (!flag);

		System.out.println("Enter Your Address: ");
		user.setAddress(br.readLine());

		UserDAO userdao = new UserDAO();
		userdao.signUp(user);

		System.out.println("**** Sign Up Successfull ****\n");
		System.out.println("**** Please wait untill the account is approved ****");

	}

}
