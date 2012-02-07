import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.util.Collections;


public class AntColony extends Actor
{
    private ArrayList<Ant> workers = new ArrayList<Ant>();
    private int age;
    private boolean fed = false;
    
    public AntColony()
    {
	this(100);
    }
    
    public AntColony(int numAnts)
    {
	for(int i=0; i<numAnts; i++)
	{
	    workers.add(new Ant(this));
	}
	setColor(null);
	age = 0;
    }

    public void act()
    {
	if(fed) return;

	// the best Ant is the one closest to food. see Ant.compareTo
	Ant best = Collections.max(workers);
	Location closest = best.followPathFrom(getLocation());
	System.out.println(best+" at "+closest);

	if(best.distanceFromFood() == 0){
	    System.out.println("FOOD LOCATED! Solution took "+age+" generations.");
	    fed = true;
	}

	Grid gr = getGrid();
	gr.put(closest, best);

	// since we haven't found food yet, create a new generation of
	// Ants based on the previous generation's best
	int numAnts = workers.size();
	workers.clear();
	for(int i=0; i<numAnts; i++)
	{
	    Ant a = new Ant(this);
	    a.setPath(best.getPathWithMutations());
	    workers.add(a);
	}
	age++;
    }

}