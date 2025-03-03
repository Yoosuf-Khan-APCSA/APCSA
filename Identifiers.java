/**
 *	Calculator for basic arithmetic Expressions can contain: integers or decimal numbers 
 *	arithmetic operators +, -, *, /, %, ^ parentheses '(' and ')'
 *
 *	@author	Yoosuf Khan
 *	@since	February 26 2025
 */
public class Identifiers {
    private double val;
    private String name;
    public Identifiers(String str, double num){
        val=num;
        name=str;
    }
    public double getVal(){
        return val;
    }
    private void setVal(double num){
        val = num;
    }
    public String getName(){
        return name;
    }
    private void setName(String str){
        name = str;
    }
}