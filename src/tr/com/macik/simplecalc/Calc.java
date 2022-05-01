package tr.com.macik.simplecalc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Calc to evaulate a mathematical term over console or interactive prompt
 * 
 */
public class Calc {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Double> stack = new ArrayList<>();
	static int currentStack = stack.size()-1;
	static boolean once = true;
	static boolean interactive = false;
	
	/**
	 * Static main-method to start the application
	 * 
	 * @param	args[]	arguments to use with the console mode
	 */
	public static void main(String[] args) {
		// Calculator<Double> calc = new Calculator(stack);
		Double tmp = null;
		
		if (args.length > 0) { 				// Input arguments from console, one run
			for (int i=0; i<args.length; i++)
				operate(args[i]);
		} else {							// Interactive input till user defined exit
			interactive = true;
			String userInput = null;
			boolean userInputNumeric = false;
			
			help();
			// while (userInput.equals("exit")) {
			while (true) {
				System.out.print("Eingabe: ");
				userInput = sc.next();
				if (!operate(userInput))
					break;	// break the infinite while loop
			}
			showStack();
		}			
	}

	/**
	 * Operate the input to push a new stack value
	 * or apply a mathematical operation on two stack value
	 * 
	 * @param	input	input value to operate
	 */
	private static boolean operate(String input) {
		if (isNumeric(input)) {
			stack.add(new Double(input));
			currentStack = stack.size()-1;
		} else {					// any math operation or exit, non-case sensitive
			input = input.toLowerCase();
			
			// operation map to simplifier the same multiple operation choice 
			switch (input) {
				case "+" : input = "add"; break;
				case "-" : input = "sub"; break;
				case "*" : ;
				case "x" : input = "mlt"; break;
				case "/" : ;
				case ":" : input = "div"; break;
				case "?" : input = "help"; break;
				}
			
			// to execute each single operation 
			if ("exit".equals(input)) {
				return false;
			} else if ("help".equals(input)) {
				help();
			} else if ("stack".equals(input) || "show".equals(input)) {
				showStack();
			} else if ("clear".equals(input)) {
				stack.clear();
				currentStack = stack.size()-1;
			} else if ("drop".equals(input)) {
				dropStack();
			} else if ("add".equals(input)) {
				setReplaceOperatedValue(stack.get(currentStack-1) + stack.get(currentStack));
			} else if ("sub".equals(input)) {
				setReplaceOperatedValue(stack.get(currentStack-1) - stack.get(currentStack));
			} else if ("mlt".equals(input)) {
				setReplaceOperatedValue(stack.get(currentStack-1) * stack.get(currentStack));
			} else if ("div".equals(input)) {
				setReplaceOperatedValue(stack.get(currentStack-1) / stack.get(currentStack));
			} else {
				System.out.println("Input "+input+" not recognisied!");
			}
		}
		return true;
	}

	/**
	 * Set the operated value on the stack and drop the used values from stack
	 * 
	 * @param	operatedValue	this value is operated with a mathematical operation
	 */
	private static void setReplaceOperatedValue(double operatedValue) {
		dropStack();
		dropStack();
		stack.add(operatedValue);
		currentStack = stack.size()-1;
		System.out.println("[1]:"+stack.get(currentStack));
	}

	/**
	 * Drop the last stack input  
	 */
	private static void dropStack() {
		stack.remove(currentStack);
		currentStack = stack.size()-1;
	}

	/**
	 * Check the given text if it is a numeric value
	 * 
	 * @param	checkText	given any text
	 * @return				if the given text is a numeric value then true otherwise false
	 */
	private static boolean isNumeric(String checkText) {
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

	/**
	 * Show the stack as a list for the user  
	 */
	private static void showStack() {
		StringBuilder sb = new StringBuilder();
		int stackSize = stack.size();
		for (int i=0; i<stack.size(); i++) {
			sb.append("["); sb.append(stackSize-i); sb.append("]:");
			sb.append(stack.get(i).toString());
			sb.append("\n");
		}

		System.out.print("STACK (size=");
		System.out.print(stackSize);
		System.out.print("):\n");
		System.out.print(sb);
	}		

	/**
	 * Show a help description for the user  
	 */
	private static void help() {
		if (once) {
			once = false;		// never show this part again
			
			System.out.println("A nice Simple Calculator");
			System.out.println("The functionality of the calculator aims the RPN logic.");
			if (!interactive) {
				System.out.println("Usage with console arguments:");
				System.out.println("Calc 15 21 +");
				System.out.println("Calc 15 21 sub 3.141 mlt 3 :");
			} else {
				System.out.println("Interactive usage:");
				System.out.println("exit			exit the calculator");
			}
		} else if (interactive) {
			System.out.println("Interactive usage:");
			System.out.println("exit			exit the calculator");
		}
		
		System.out.println();
		System.out.println("help			show the help description");
		System.out.println("stack or show		show the stack");
		System.out.println("drop			drop the last element in der stack");
		System.out.println("clear			clear the stack, after it is empty");
		System.out.println();
		System.out.println("add or +		math operation add");
		System.out.println("sub or -		math operation sub");
		System.out.println("mlt or * or x		math operation multiplication");
		System.out.println("mlt or / or :		math operation division");
		System.out.println();
	}

}
