
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class SickCoyote extends Actor{
	private int threshold;
	private int lifetime;
	public SickCoyote(int life){
		threshold=10;
		lifetime=l=life;
	}
	public SickCoyote(){
		threshold=10;
		lifetime=threshold;
	}
	public act(){
		if(lifetime<=0){
			Coyote c = new Coyote();
			ArrayList locs = getEmptyAdjacentLocations();
			Location loc = locs.get((int)(Math.random*locs.size()));
			c.putSelfInGrid(getGrid(),getLocation)
		}
		else lifetime--;
	}
}
