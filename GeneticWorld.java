import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

public class GeneticWorld extends ActorWorld
{
    public GeneticWorld(int rows, int cols)
    {
	super( new GeneticGrid<Actor>(rows, cols) );
    }

}