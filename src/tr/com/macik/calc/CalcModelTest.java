package tr.com.macik.calc;

public class CalcModelTest {

	public static void main(String[] args) {
		CalcModel<String> model = new CalcModel<>();		// model contain only data
		
		try {
			model.enter("ABC");
			
			throw new Exception("Unexpected behaviour!");
		} catch (NumberFormatException e) {
			System.out.println("Warning: Expected exception behaviour.");;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.enter("21");
		//model.enter(new Integer(12));
		model.enter("321");
		//model.enter(new Long(123l));
		model.enter("3.141");
		//model.enter(new Float(2.71828f));
		model.enter("1234567890.55");
		//model.enter(new Double(567_890.0123456789d));
		System.out.println(model.toStringStack());

		model.clear();
		
		model.msgOnOff();

		model.enter("1");
		//model.enter(new Integer(12));
		model.enter("2");
		//model.enter(new Long(123l));
		model.enter("3");
		//model.enter(new Float(2.71828f));
		model.enter("4");
		//model.enter(new Double(567_890.0123456789d));
		//System.out.println("4x ENTER: " + model);
		model.toStringStack();

		model.drop();
		model.swap();
		model.rot();
		model.dup();
		model.swap(3);
		model.dup(3);
		model.rot(4);
		model.dup(5);
	}

}
