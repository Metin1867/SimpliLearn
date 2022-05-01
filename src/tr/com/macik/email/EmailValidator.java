package tr.com.macik.email;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class EmailValidator to verify and validate an email from the existing email list.
 * This class can only once instantiated.
 * Thereafter the once instance can be retrieved with the method getInstance().
 */
public class EmailValidator {
	private static EmailValidator validator;
	private static String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static Pattern pattern;

	private ArrayList<String> emails;

	/**
	 * Constructor to store the email list only once
	 * 
	 * @param	emailList	email list to verify
	 */
	public EmailValidator(String[] emailList) {
		if (validator == null) {
			validator = this;
			
			pattern = Pattern.compile(regex);
			
			emails = new ArrayList<String>();
			for (String email : emailList)
				emails.add(email);
		} else
			throw new RuntimeException("One Instance can be instantieted! Use getInstance static method to get the one instance.");
	}
	
	/**
	 * Get reference to one instance
	 * 
	 * @return			reference to one instance
	 */
	public static EmailValidator getInstance() {
		if (validator == null)
			throw new RuntimeException("Instance isn't instantieted! Use first constructor to create one Instance.");
		return validator;
	}

	/**
	 * Verify an email by the email list
	 * 
	 * @param	email	email which will be verified
	 * @return			if email exists true else false
	 */
	public boolean verify(String email) {
		/*for (String existingEmail : emails)
			if (existingEmail.equals(email))
				return true;*/
		return emails.contains(email);
	}

	/**
	 * validate an email with a pattern
	 * 
	 * @param	email	email which will be validated
	 * @return			if email valid true else false
	 */
	public boolean validate(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
