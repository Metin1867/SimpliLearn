package tr.com.macik.calc;

public class Calculator implements CalculatorInterface<String> {
	public String lastOperation;

	@Override
	public String add(CalcModel<String> model) {
		lastOperation = "ADD";
		for (int i = model.lastIndex(); i > 0; --i) {
			model.set(i-1, String.valueOf(model.getValue(i-1) + model.getValue(i)));
			model.drop();
		}
		return model.get(0);
	}

	@Override
	public String sub(CalcModel<String> model) {
		lastOperation = "SUB";
		for (int i = model.lastIndex(); i > 1; --i) {
			model.set(i-1, (String.valueOf(model.getValue(i-1) + model.getValue(i))));
			model.drop();
		}
		model.set(0, (String.valueOf(model.getValue(0) - model.getValue(1))));
		model.drop();
		return model.get(0);
	}

	@Override
	public String mlt(CalcModel<String> model) {
		lastOperation = "MLT";
		for (int i = model.lastIndex(); i > 0; --i) {
			model.set(i-1, String.valueOf(model.getValue(i-1) * model.getValue(i)));
			model.drop();
		}
		return model.get(0);
	}

	@Override
	public String div(CalcModel<String> model) {
		lastOperation = "DIV";
		for (int i = model.lastIndex(); i > 1; --i) {
			model.set(i-1, String.valueOf(model.getValue(i-1) * model.getValue(i)));
			model.drop();
		}
		model.set(0, String.valueOf(model.getValue(0) / model.getValue(1)));
		model.drop();
		return model.get(0);
	}

	@Override
	public String add2(CalcModel<String> model) {
		lastOperation = "ADD2";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sub2(CalcModel<String> model) {
		lastOperation = "SUB2";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mlt2(CalcModel<String> model) {
		lastOperation = "MLT2";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String div2(CalcModel<String> model) {
		lastOperation = "DIV2";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addN(CalcModel<String> model, int n) {
		lastOperation = "ADDn";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String subN(CalcModel<String> model, int n) {
		lastOperation = "SUBn";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mltN(CalcModel<String> model, int n) {
		lastOperation = "MLTn";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String divN(CalcModel<String> model, int n) {
		lastOperation = "DIVn";
		// TODO Auto-generated method stub
		return null;
	}

	
}
