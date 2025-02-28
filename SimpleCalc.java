import java.util.List;		// used by expression evaluator

/**
 *	Calculator for basic arithmetic Expressions can contain: integers or decimal numbers 
 *	arithmetic operators +, -, *, /, %, ^ parentheses '(' and ')'
 *
 *	@author	Yoosuf Khan
 *	@since	February 26 2025
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public SimpleCalc() {
		valueStack=new ArrayStack<Double>();
		operatorStack=new ArrayStack<String>();
		utils= new ExprUtils();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		String equation="";
		while(!equation.equals("q")){
			equation = Prompt.getString("");
			if (equation.equals("q"));
			else if(equation.equals("h")) printHelp();
			else System.out.printf("%.2f%n",evaluateExpression(utils.tokenizeExpression(equation)));
		}
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		double value = 0;
		valueStack=new ArrayStack<Double>();
		operatorStack=new ArrayStack<String>();
		for(int i=0; i<tokens.size(); i++){
			/*
			for(double v:valueStack.get()) System.out.print(v+" ");
			System.out.println();
			for(String v:operatorStack.get()) System.out.print(v+" ");
			System.out.println();
			*/

			if(Character.isDigit(tokens.get(i).charAt(0))||tokens.get(i).length()>1)
				valueStack.push(Double.parseDouble(tokens.get(i)));
			else if(operatorStack.isEmpty()||(((hasPrecedence(operatorStack.peek(),tokens.get(i))||(tokens.get(i).equals("^")&&operatorStack.peek().equals("^")))||tokens.get(i).equals("(")||tokens.get(i).equals(")"))
						&&!hasSamePrecedence(operatorStack.peek(),tokens.get(i)))){
				/** 
				if(!operatorStack.isEmpty()) {System.out.println(!hasSamePrecedence(operatorStack.peek(),tokens.get(i)));
				System.out.println(hasPrecedence(operatorStack.peek(),tokens.get(i))+" "+tokens.get(i).equals("(")+" "+tokens.get(i).equals(")")+" "+!hasSamePrecedence(operatorStack.peek(),tokens.get(i)));
				}
				*/
				if(tokens.get(i).equals(")")){
					while (!operatorStack.peek().equals("(")){
					executeOperation();
					}
					operatorStack.pop();

				}
				else operatorStack.push(tokens.get(i));
			}
			else{
				executeOperation();
				operatorStack.push(tokens.get(i));
			}
			
		}
		while (!operatorStack.isEmpty()){
			executeOperation();
		}
		value=valueStack.peek();
		return value;
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
	 /**
	 *	checks for same precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has  same precedence as op1; false otherwise
	 */
	 private boolean hasSamePrecedence(String op1, String op2) {
		//if (op1.equals("^")&&op2.equals("^")) return true;
		if (op2.equals("(") || op2.equals(")")||op1.equals("(") || op1.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&&( op2.equals("*") || op2.equals("/") || op2.equals("%")))
			return true;
		if ((op1.equals("+") || op1.equals("-"))&&(op2.equals("+") || op2.equals("-"))) return true;
		return false;
	}
	/**
	 * preforms opperation with last operator and last two operands
	 */
	 private void executeOperation() {
		double val2=valueStack.pop();
		double val1=valueStack.pop();
		double val3=0;
		if(operatorStack.peek().equals("+"))val3=val1+val2;
		if(operatorStack.peek().equals("-"))val3=val1-val2;
		if(operatorStack.peek().equals("*"))val3=val1*val2;
		if(operatorStack.peek().equals("/"))val3=val1/val2;
		if(operatorStack.peek().equals("%"))val3=val1%val2;
		if(operatorStack.peek().equals("^"))val3=Math.pow(val1,val2);
		operatorStack.pop();
		valueStack.push(val3);
	 }
}
