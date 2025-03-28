
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class RR extends Critter{
	public SickCoyote(){
		setColor(null);
		setDirection(Location.NORTH);
	}
	public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs ={0, 45, 90, 135, 180, 215, 270, 315};
        for (Location loc : getLocationsInDirections(dirs,3))
            if (getGrid().get(loc) == null||getGrid().get(loc) instanceof Boulder
					|| getGrid().get(loc) instanceof Coyote)
                locs.add(loc);
		if(locs.size()==0){
			for (Location loc : getLocationsInDirections(dirs,2))
            if (getGrid().get(loc) == null||getGrid().get(loc) instanceof Boulder
					|| getGrid().get(loc) instanceof Coyote)
                locs.add(loc);
		}
		if(locs.size()==0){
			for (Location loc : getLocationsInDirections(dirs,1))
            if (getGrid().get(loc) == null||getGrid().get(loc) instanceof Boulder
					|| getGrid().get(loc) instanceof Coyote)
                locs.add(loc);
		}
        return locs;
    }
	
	/**
     * Moves this critter to the given location <code>loc</code>, or removes
     * this critter from its grid if <code>loc</code> is <code>null</code>.
     * An actor may be added to the old location. If there is a different actor
     * at location <code>loc</code>, that actor is removed from the grid.
     * Override this method in subclasses that want to carry out other actions
     * (for example, turning this critter or adding an occupant in its previous
     * location). <br />
     * Postcondition: (1) <code>getLocation() == loc</code>. (2) The state of
     * all actors other than those at the old and new locations is unchanged.
     * @param loc the location to move to
     */
    public void makeMove(Location loc)
    {
        if (loc == null){
            removeSelfFromGrid();
            return;
		}
        else if(getGrid().get() instanceOf Boulder){
			Kaboom c = new Kaboom();
			c.putSelfInGrid(getGrid(),loc)
			removeSelfFromGrid();
			return;
		}
		else if(getGrid().get() instanceOf Coyote){
			Coyote c = new Coyote();
			ArrayList locs = getEmptyAdjacentLocations();
			Location locC = locs.get((int)(Math.random*locs.size()));
			c.putSelfInGrid(getGrid(),locC)
		}
        else
            moveTo(loc);
    }
	/**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @param dist - distance of locations from starting position
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions, int dist)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighbor1Loc = loc.getAdjacentLocation(getDirection() + d);
            Location neighbor2Loc = neighbor1Loc.getAdjacentLocation(getDirection() + d);
            Location neighbor3Loc = neighbor2Loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighbor1Loc)&& dist==1)
                locs.add(neighbor1Loc);
            if (gr.isValid(neighbor2Loc)&& dist==2)
                locs.add(neighbor2Loc);
            if (gr.isValid(neighbor3Loc)&& dist==3)
                locs.add(neighbor3Loc);
        }
        return locs;
    }    
}
