import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

import java.util.ArrayList;

public class GeneticGrid<E> extends BoundedGrid<E>
{
    public GeneticGrid(int rows, int cols)
    {
	super(rows, cols);
    }

    public int getDistanceToFood(Location loc)
    {
	ArrayList<Location> foodLocs = new ArrayList<Location>();
	for(Location l : getOccupiedLocations())
	{
	    if(get(l) instanceof FoodSource) foodLocs.add(l);
	}

	if (foodLocs.size() < 1) return Integer.MAX_VALUE;

	Location firstFood = foodLocs.get(0);
	int minDist = getDistanceBetween(loc, firstFood);
	for(Location f : foodLocs)
	{
	    if(getDistanceBetween(loc, f) < minDist) minDist = getDistanceBetween(loc, f);
	}
	return minDist;
    }

    // since we're on a grid, this is going to be manhattan distance
    public int getDistanceBetween(Location loc1, Location loc2)
    {
	return Math.abs(loc1.getCol() - loc2.getCol()) + Math.abs(loc1.getRow() - loc2.getRow());
    }

}