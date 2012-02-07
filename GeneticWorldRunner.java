import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class GeneticWorldRunner
{
    public static void main(String[] args)
    {
	GeneticWorld world = new GeneticWorld(50,50);
	AntColony colony = new AntColony(10);
	FoodSource food = new FoodSource();

	Location middle = new Location(world.getGrid().getNumRows()/2,world.getGrid().getNumCols()/2);
	world.add( middle, colony );
	world.add( world.getRandomEmptyLocation(), food );

	world.show();
    }
    
}