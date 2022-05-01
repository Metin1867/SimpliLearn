package tr.com.macik.calc;

import java.util.ArrayList;

public class CalcModel<T>{
	private ArrayList<T> stack = new ArrayList();
	private int lastIdx = -1;				// stack empty
	private boolean msgOn = false;

	private void msg(String text) {
		System.out.println(text + ": " + toString());
	}
	
	public void enter(T obj) {				// push a new object to stack
		if (! isNumeric(obj.toString()) )
			throw new NumberFormatException("NumberFormatException ");
		stack.add(obj);
		lastIdx++;

		// msg("ENTER");
	}

	private boolean isNumeric(String checkText) {
		char[] checkCharacters = checkText.toCharArray();
		int idx = 0;
		boolean foundFDecimalPoint = false;
		boolean theFirstCharacterSign = false;
		if (checkCharacters[idx] == '-' || checkCharacters[idx] == '+') {
			theFirstCharacterSign = true;
			idx++;
		}
		
		while (idx < checkCharacters.length) {
			if (checkCharacters[idx] == '.') {
				if (foundFDecimalPoint)
					return false;
				else
					foundFDecimalPoint = true;
			} else {
				int digit = checkCharacters[idx]-'0';
				if (digit < 0 || digit > 9)
					return false;
			}	
			idx++;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "CalcModel [stack["+lastIdx+"]=" + stack + "]";
	}

	public int size() {
		return stack.size();
	}

	public T get(int i) {
		return (T) stack.get(i);
	}

	public void set(int i, T obj) {			// modify an existing object
		stack.set(i, obj);
	}

	public int lastIndex() {				// return the last index
		return lastIdx;
	}

	public Object drop() {
		lastIdx--;

		msg("DROP");
		return stack.remove(stack.size()-1);
	}

	public void swap() {
		int swapIdx = lastIndex()-1;
		// System.out.println(stack.get(swapIdx) + "[" + swapIdx + "] -> [" + stack.size() + "]");
		enter(stack.get(swapIdx));
		stack.remove(swapIdx);
		lastIdx--;

		msg("SWAP");
	}

	public void rot() {
		enter(stack.get(0));
		stack.remove(0);
		lastIdx--;

		msg("ROT");
	}

	public void dup() {
		int dupIdx = lastIndex();
		enter(stack.get(dupIdx));

		msg("DUP");
	}

	public void swap(int i) {
		int swapIdx = getIdx(i);
		// System.out.println(stack.get(swapIdx) + "[" + swapIdx + "] -> [" + stack.size() + "]");
		enter(stack.get(swapIdx));
		stack.remove(swapIdx);
		lastIdx--;
		
		msg("SWAP-nth");
	}

	// stack 			0 | 1 | 2 | 3 | 4 | 5
	// i-th element		6 | 5 | 4 | 3 | 2 | 1
	// size = 6, 6-i    0   1   2   3   4   5
	private int getIdx(int i) {
		return size() - i;
	}

	public void dup(int n) {
		int offset = 0;
		for (int i=n ; i>0; i--) {
			enter(stack.get(getIdx(i+offset)));
			offset++;
		}

		msg("DUP-n");
	}

	public void rot(int n) {
		enter(stack.get(getIdx(n)));
		stack.remove(getIdx(n+1));
		lastIdx--;
		
		msg("ROT-n");
	}

	public void clear() {
		stack.clear();
		lastIdx = -1;				// stack empty

		msg("CLEAR");
	}

	public float getValue(int i) {
		return Float.valueOf(stack.get(i).toString());
	}

	public void msgOnOff() {
		msgOn  = !msgOn;	
	}

	public String toStringStack() {
		StringBuilder sb = new StringBuilder();
		int stackSize = stack.size();
		for (int i=0; i<stack.size(); i++) {
			sb.append("["); sb.append(stackSize-i); sb.append("]:");
			sb.append(stack.get(i).toString());
			sb.append("\n");
		}
		if (msgOn) {
			System.out.print("STACK (size=");
			System.out.print(stackSize);
			System.out.print("):\n");
			System.out.print(sb);
		}
		return sb.toString();
	}
}
