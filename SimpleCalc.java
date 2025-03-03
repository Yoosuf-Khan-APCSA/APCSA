import java.util.ArrayList;		// used by expression evaluator
import java.util.List;
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
	private ArrayList<Identifiers> vars;
	// constructor	
	public SimpleCalc() {
		valueStack=new ArrayStack<Double>();
		operatorStack=new ArrayStack<String>();
		utils= new ExprUtils();
		vars=new ArrayList<Identifiers>();
		vars.add(new Identifiers("e",Math.E));
		vars.add(new Identifiers("pi",Math.PI));
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
			List<String> numList=utils.tokenizeExpression(equation);
			
			if (equation.equals("q"));
			else if(equation.equals("l")){
				System.out.println("Identifiers:");
				for(Identifiers x:vars){
					System.out.printf("%-10s = %.7f%n",x.getName(),x.getVal());
				}
			}
			else if(equation.equals("h")) printHelp();
			//else if(hasCorrectSyntax(numList))
			else if(numList.size()>1&&numList.get(1).equals("=")){
				String varName=numList.get(0);
				numList.remove(0);
				numList.remove(0);
				if(hasCorrectSyntax(numList)){
					vars.add(new Identifiers(varName,evaluateExpression(numList)));
					System.out.printf("%-10s = %f%n",varName,evaluateExpression(numList));
				}
			}
			else {
				if(hasCorrectSyntax(numList))
				System.out.printf("%f%n",evaluateExpression(numList));
			}
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
	/**	checks syntax */
	public char opOrNum(String str) {
		if(Character.isDigit(str.charAt(0))||(str.length()>1&&Character.isDigit(str.charAt(1))))
			return 'n';
		switch (str){
			case "(":
			case ")": 
			case "*": 
			case "/": 
			case "-": 
			case "+":
			case "^":
			case "%":
				return 'o';
			default:
				return 'a';
		}
	}
	/**	checks syntax */
	public boolean hasCorrectSyntax(List<String> tokens) {
		//for(String x:tokens) System.out.print(x+" ");
		int unclosedParen =0;
		for(int i=0; i<tokens.size();i++){
			if(opOrNum(tokens.get(i))=='a') {
				for (Identifiers x : vars){
					if(x.getName().equals(tokens.get(i)))
						tokens.set(i,""+x.getVal());
				}
				if(opOrNum(tokens.get(i))=='a') tokens.set(i,""+0);
			}
			else if(tokens.get(i).equals("(")) unclosedParen++;
			else if(tokens.get(i).equals(")")) unclosedParen--;
			else if(tokens.get(i).equals("-")&&((i==0&&opOrNum(tokens.get(i+1))=='n')||(i>0&&opOrNum(tokens.get(i-1))=='o'
					&&!tokens.get(i-1).equals("(")&&!tokens.get(i-1).equals(")")))){
				tokens.set(i+1,"-"+tokens.get(i+1));
				tokens.remove(i);
				if(i!=0) i--;
			}
			
		}
		if(unclosedParen!=0){
			System.err.println("System ERROR: Improper Parenthesis Placement");
			return false;
		}
		if(opOrNum(tokens.get(0))=='o'&&!(tokens.get(0).equals("-")||tokens.get(0).equals("("))){
			System.err.println("System ERROR: Incorrect Start of Equation");
			return false;
		}
		if(opOrNum(tokens.get(tokens.size()-1))=='o'&&!(tokens.get(tokens.size()-1).equals(")"))){
			System.err.println("System ERROR: Incorrect End of Equation");
			return false;
		}
		for(int i=1; i<tokens.size();i++){
			String str1=tokens.get(i);
			String str2=tokens.get(i-1);
			if(opOrNum(str1)=='o'&&!str1.equals("(")&&!str1.equals(")")&&opOrNum(str2)=='o'&&!str2.equals("(")&&!str2.equals(")")){
				System.err.println("System ERROR: Incorrect Operator Placement"+str1+" "+str2);
				return false;
			}
			if(i>0&&str1.equals("(")&&(opOrNum(str2)=='n'||str2.equals(")")))
				tokens.add(i+1,"*");
		}
		//System.out.println("");
		//for(String x:tokens) System.out.print(x+" ");
		return true;
	}
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		if(tokens.size()==1) return Double.parseDouble(tokens.get(0));
		
		double value = 0;
		valueStack=new ArrayStack<Double>();
		operatorStack=new ArrayStack<String>();
		for(int i=0; i<tokens.size(); i++){
			/**
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
