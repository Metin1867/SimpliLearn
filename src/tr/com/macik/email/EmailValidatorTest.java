package tr.com.macik.email;

import tr.com.macik.tools.UserConsoleInput;

public class EmailValidatorTest {
	
	public static void main(String[] args) {
		UserConsoleInput userInput = new UserConsoleInput();

		String[] emails = userInput.getLines("Give List of Emails separated by comma", ",");
		
		for (String email : emails)
			System.out.println(email);
		
		EmailValidator validator = new EmailValidator(emails);
		
		System.out.println("To exit, enter 'exit' or '.' as input!");
		String email = "";
		do {
			email = userInput.getText("Email to verify");
			if (email.equals("exit") || email.equals("."))
				break;
			else {
				if (validator.verify(email))
					System.out.println("Email is verified!");
				else {
					if (validator.validate(email))
						System.out.println("Warning: Email doesn't exists!");
					else
						System.out.println("Validation-Error: Email doesn't permit by RFC 5322!");
				}
			}
		} while (true);
		
	}

}
