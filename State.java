/**
 *	The object to store US state information.
 *
 *	@author	
 *	@since	
 */
public class State implements Comparable<State>
{
	private String name;			// state name
	private String abbreviation;	// state abbreviation
	private int population;			// state population
	private int area;				// state area in sq miles
	private int reps;				// number of House Reps
	private String capital;			// state capital city
	private int month;				// month joined Union
	private int day;				// day joined Union
	private int year;				// year joined Union
	
	public State(String name1){
		name=name1;
	}
	public State(String name1, String abrv1, int pop, int area1, int reps1, String cap, int mon, int day1, int yr) { 
		name=name1;
		abbreviation=abrv1
		population=pop;
		area=area1;
		reps=reps1;
		capital=cap;
		month=mon;
		day=day1;
		year=yr;
	}
	
	@Override
	public int compareTo(State other) 
	{	return name.compareTo(other.getName()); }
	
	public String getName ( )
	{	return name; }
	
	@Override
	public String toString() 
	{	return System.out.printf("%-20s%-7s%-10d%-5d%-20s%-3d%-3d%-3d",name,abbreviation,population, area, reps,cap,mon,day,year); }
}
