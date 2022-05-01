package tr.com.macik.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class UserConsoleInput provide methods to get values from console
 */
public class UserConsoleInput {
	static final Scanner inpScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		UserConsoleInput userInput = new UserConsoleInput();

		HashMap<Integer, String> userInputMap = new HashMap<>();
		userInputMap.put(1, "Texteingabe");
		userInputMap.put(2, "Scannereingabe integer value");
		userInputMap.put(3, "Fliesskommazahl");
		userInputMap.put(4, "Ganze Zahl");
		userInputMap.put(5, "Emailliste");
		userInputMap.put(999, "Exit");
		
		int choice = new Integer(userInput.getChoice(userInputMap));
		System.out.println("Auswahl: >" + choice + "<");
		
		switch (choice) {
		case 1:	String s = userInput.getText("Eingabe ein Text");
				System.out.println("Bestätigung Eingabe: >" + s + "<");
				break;
		case 2:	System.out.print("Eingabe direkt über Scanner: ");
				int i = inpScanner.nextInt();
				System.out.println("Bestätigung Eingabe: >" + i + "<");
				break;
		case 3:	Double d = userInput.getNumber("Eingabe Fliesskommazahl");
				System.out.println("Bestätigung Eingabe: >" + d + "<");
				break;
		case 4:	Long w = userInput.getWholeNumber("Eingabe ganze Zahl");
				System.out.println("Bestätigung Eingabe: >" + w + "<");
				break;
		case 5:	String[] emails = userInput.getLines("Email-Liste", ",");
				for (String email : emails)
					System.out.println(">" + email + "<");
				break;
		default:
				;	// exit the main-methode
		}
	}

	/**
	 * Get a choice from a menu
	 * 
	 * @param	prompt	menu with items to choice in form of key/value
	 * @return			selected menu key
	 */
	public <K, V> String getChoice(HashMap<K, V> prompt) {
		for (Map.Entry<K,V> entry : prompt.entrySet()) {
			  K key = entry.getKey();
			  V value = entry.getValue();
			  
			  System.out.print("[" + key + "]	");
			  System.out.println(value);
		}
		return getFromList(prompt.keySet().toArray());
	}

	/**
	 * Get an item from a given list
	 * 
	 * @param	list	list of choices to select one
	 * @return			the selected one from the list
	 */
	public String getFromList(Object[] list) {
		String input = "";
		while (!isInList(input, list))
			input = getText("Choice");
		return input;
	}

	/**
	 * Get a double value from console
	 * 
	 * @param	prompt	Ask user for input with a prompt
	 * @return			A double value
	 */
	private boolean isInList(String input, Object[] list) {
		for (Object item : list)
			if (item.toString().equals(input))
				return true;
		return false;
	}

	/**
	 * Get a list of text from console
	 * 
	 * @param	prompt	Ask user for input with a prompt
	 * @return			A string array
	 */
	public String[] getLines(String prompt, String separator) {
		String[] list = getText(prompt).split(separator);
		ArrayList<String> result = new ArrayList<>();
		for (String item : list)
			result.add(item.trim());
		return result.toArray(list);
	}

	/**
	 * Get a whole number value from console
	 * 
	 * @param	prompt	Ask user for input with a prompt
	 * @return			A long value
	 */
	public Long getWholeNumber(String prompt) {
		String input = "";
		while (!isNumeric(input))
			input = getText(prompt);
		return (new Double(input)).longValue();
	}

	/**
	 * Get a double value from console
	 * 
	 * @param	prompt	Ask user for input with a prompt
	 * @return			A double value
	 */
	public Double getNumber(String prompt) {
		String input = "";
		while (!isNumeric(input))
			input = getText(prompt);
		return new Double(input);
	}

	/**
	 * Get any text from console
	 * 
	 * @param	prompt	Ask user for input with a prompt
	 * @return			A string value
	 */
	public String getText(String prompt) {
		if (prompt!=null) {
			System.out.print(prompt);
			System.out.print(':');
		}
		return inpScanner.nextLine();
	}

	/**
	 * Check the given text if it is a numeric value
	 * 
	 * @param	checkText	given any text
	 * @return				if the given text is a numeric value then true otherwise false
	 */
	private static boolean isNumeric(String checkText) {
		if ("".equals(checkText))
			return false;
		char[] checkCharacters = checkText.toCharArray();
		int idx = 0;
		boolean foundFDecimalPoint = false;
		boolean theFirstCharacterSign = false;
		// check the sign
		if (checkCharacters[idx] == '-' || checkCharacters[idx] == '+') {
			theFirstCharacterSign = true;
			if (checkCharacters.length==1)
				// if only a sign character exists, no number
				return false;
			idx++;
		}
		
		// check each character for non numeric and floating point
		while (idx < checkCharacters.length) {
			// check floating point
			if (checkCharacters[idx] == '.') {
				if (foundFDecimalPoint)
					// if a second decimal point found, no number
					return false;
				else
					foundFDecimalPoint = true;
			} else {
				// check character
				int digit = checkCharacters[idx]-'0';
				if (digit < 0 || digit > 9)
					// if no number character found
					return false;
			}	
			idx++;
		}
		
		// it is a numeric value
		return true;
	}
}