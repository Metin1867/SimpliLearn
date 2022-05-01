package tr.com.macik.simplecalc;

import java.util.ArrayList;

public class Calculator<T> {
	private final ArrayList<T> stack;
	
	public Calculator(ArrayList<T> stack) {
		this.stack = stack;
	}

}
