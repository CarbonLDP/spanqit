package pers.aprakash.spanqit.constraint;

import pers.aprakash.spanqit.core.Util;

class UnaryOperation extends Operation<UnaryOperation> {
	UnaryOperation(UnaryOperator operator) {
		super(operator, 1);
	}

	@Override
	public String getQueryString() {
		StringBuilder expression = new StringBuilder();
		
		expression.append(operator.getQueryString());
		String op = "";
		if (!elements.isEmpty()) {
			op = getOperand(0).getQueryString();
		}
		expression.append(Util.getParenthesizedString(op));
		
		return expression.toString();
	}
}