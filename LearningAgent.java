package monty;

import java.util.Random;


//Implement LearningAgent for QLearningMonty
//This agent mimics the behavior of user (player) i.e.
//it does the initial pick and then it also switches
//a door based on its first choice and the door that Monty opened. 
//This is needed for QLearning to learn the behavior of opponent.
//
//Note: A good idea is to try to implement LearningAgent based on 
//player's statistics. So, you will pick a door(with some probability) according to
//probability real player chose this door. Same for switchDoor, but it will be conditional
//probability on initial_pick and monty_door
//
//Another good idea is ask user to specify LearningAgent by itself. i.e. Ask what would user do
//in this or that situation. An example of what it might look like is LeftLearningAgent
//This would be ever better, but might be too complicated for user to enter.
//
//You are free to come up with your own LearningAgents to help test the algorithm

abstract class LearningAgent {
	
	protected int n_doors;
	
	LearningAgent(int n_d)
	{
		n_doors = n_d;
	}
	
	abstract protected int chooseDoor();
	
	abstract protected int switchDoor(int initial_pick, int monty_door);

}

//Sample agent to implement
//This one tries to choose the initial door at random
//But then tries to switch to the leftmost door (door 1
//or if door1 is open by Monty, door 2)
class LeftLearningAgent extends LearningAgent
{

	LeftLearningAgent(int n_d) {
		super(n_d);
	}
	
	@Override
	protected int chooseDoor()
	{
		Random ran = new Random();
		return ran.nextInt(n_doors)+1;
	}
	
	@Override
	protected int switchDoor(int initial_pick,int monty_door)
	{
		//always left agent for example
		if(monty_door == 1)
			return 2;
		else
			return 1;
	}

}
