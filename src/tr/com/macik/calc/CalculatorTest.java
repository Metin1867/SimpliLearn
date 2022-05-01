package tr.com.macik.calc;

public class CalculatorTest {

	public static void main(String[] args) {
		CalcModel<String> model = new CalcModel<>();		// model contain only data
		Calculator calc = new Calculator();		// mathematical logics

		model.enter("21000");	// A
		model.enter("321");		// B
		model.enter("3141");	// C
		model.enter("55");		// D
		System.out.println("Enter Data: " + model);

		//calc.add(model);		// A + B + C + D
		//calc.sub(model);		// A - B - C - D = A - ( B + C + D ) 
		calc.mlt(model);		// A * B * C * D
		//calc.div(model);		// A / ( B * C * D )
		System.out.println(calc.lastOperation + ": " + model);

	}

}
