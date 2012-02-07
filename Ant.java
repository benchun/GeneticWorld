import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;


public class Ant extends Actor implements Comparable<Ant>
{
    private static final int PATH_LENGTH = 32;
    private static final double MUTATION_RATE = 0.05;

    private String path;
    private AntColony colony;

    public Ant(AntColony c)
    {
	colony = c;
	path = randomPath();
    }

    // define an ordering such that the Ant whose path get it closest
    // to the food is greatest
    public int compareTo(Ant other)
    {
	return other.distanceFromFood() - distanceFromFood();
    }

    // the number of steps it will take us to get to food from the end
    // of our path.  imagine that we can smell it or something
    public int distanceFromFood()
    {
	Grid g = colony.getGrid();
	
	// this casting thing is ugly but i don't see a way around it
	// unless we put getDistanceToFood in this class which seems
	// wrong. TBD: move the method here anyway.
	if(!(g instanceof GeneticGrid)) return Integer.MAX_VALUE;
	GeneticGrid gr = (GeneticGrid)g;

	Location end = followPathFrom(colony.getLocation());
	if(end == null) return Integer.MAX_VALUE;
	if(!gr.isValid(end)) return Integer.MAX_VALUE;
	return gr.getDistanceToFood(end);
    }

    public void setPath(String newPath)
    {
	path = newPath;
    }

    public String getPathWithMutations()
    {
	String mutatedPath = "";

	if(path.length() < PATH_LENGTH && Math.random() < MUTATION_RATE)
	    path += (int)(Math.random()*4);

	for(int i=0; i<path.length(); i++)
	{
	    String c = path.substring(i,i+1);
	    if(Math.random() < MUTATION_RATE)
	    {
		mutatedPath += (int)(Math.random()*4);
		// a rare mutation causes shorter paths
		if(Math.random() < MUTATION_RATE) break;
	    }
	    else
	    {
		mutatedPath += c;
	    }
	}
	return mutatedPath;
    }

    public String randomPath()
    {
	String randPath = "";
	for(int i=0; i<PATH_LENGTH; i++)
	{
	    int r = (int)(Math.random()*4);
	    randPath += r;
	}

	return randPath;
    }
    
    public Location followPathFrom(Location start)
    {
	Grid gr = colony.getGrid();
	Location end = new Location(start.getRow(), start.getCol());
	for(int i=0; i<path.length(); i++)
	{
	    int p = Integer.parseInt(path.substring(i,i+1));
	    end = end.getAdjacentLocation(p * 90);
	    if(!gr.isValid(end))
	    {
		System.out.println("invalid path because " + end);
		return null;
	    }
	}
	return end;
    }

    public String toString()
    {
	return "ANT (path "+path+")";
    }

}