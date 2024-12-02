/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private int population;
	private String city;
	private String state;
	private String type;
	// constructor
	
	public City(String state1,String city1,String type1,int pop1){
		state= state1;
		city = city1;
		type = type1;
		pop = pop1
		
	}
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int CompareTo (City other){
		if(this.population!=other.population) return this.population - other.population;
		else if (this.state!=other.state)return this.state.compareTo(other.state);
		else return this.name.compareTo(other.name);
	}
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals (City other){
		if(this.population!=other.population) return false;
		if(this.city!=other.city) return false;
		if(this.type!=other.type) return false;
		if(this.state!=other.state) return false;
		return true;
	}
	/**	Accessor methods */
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
