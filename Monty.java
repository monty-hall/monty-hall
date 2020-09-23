package monty;
import java.util.HashMap;
import java.util.Random;

abstract class Monty {

	protected int n_doors, chosen_door, prize;

	//Vector of probabilities for Monty to choose
	//any door (note: door 0 is reserved for technical things
	//actual real doors are doors 1,2,...,n_doors
	protected double ProbVec[];


	Monty(int n_doors, int chosen_door) {
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		Random rand = new Random();
		prize = rand.nextInt(n_doors) + 1;
		ProbVec = new double[n_doors+1];
	}

	protected boolean checkWin() {
		return chosen_door == prize;
	}

	protected void switchDoor(int new_door) {
		chosen_door = new_door;
	}

	protected int getPrize() {
		return prize;
	}

	//implement a method for player initially picking the winning door
	abstract protected void generateWinVec();
	
	//implement a method for player initially picking the loosing door
	abstract protected void generateLoseVec();
	
	//This method will determine which strategy to choose (based on player's choice)
	protected void generateVec()
	{
		if(checkWin())
			generateWinVec();
		else
			generateLoseVec();
	}
	
	//Open door always works the same way for all Monty's
	//If 0 is returned, then no door is opened (it may happen for
	//different reasons). Usually, Monty will open one of the doors
	protected int getOpenDoor()
	{
		generateVec();
		//Create a random number from 0.0 to 1.0
		double prob = Math.random();
		double CumulativeProb = 0.0;
		for(int i=0; i<=n_doors; ++i)
		{
			CumulativeProb+=ProbVec[i];
			if(CumulativeProb >= prob)
				return i;
		}
		return 0;
	}

/******************************************************************************/

	protected void standardWin() {
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			if(i != chosen_door)
				ProbVec[i] = (double)1/(n_doors-1);
			else
				ProbVec[i] = 0;
		}
	}

	protected void standardLose() {
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			if(i != chosen_door && i!=prize)
				ProbVec[i] = (double)1/(n_doors-2);
			else 
				ProbVec[i] = 0;
		}
	}

	protected void openChosenDoor() {
		/* aka too bad */
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[chosen_door] = 1;
	}

	protected void openPrizeDoor() {
		/* aka in your face */ 
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[prize] = 1;
	}

	protected void notOpenDoor() {
		/* aka tricky */
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
	}

	protected void random() {
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			ProbVec[i] = (double)1/n_doors;
		}
	}

}

class StandardMonty extends Monty {

	StandardMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}
	
	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		standardLose();
	}
}

class MontyFromHell extends Monty {

	MontyFromHell(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class EvilMonty extends Monty {

	EvilMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		openPrizeDoor();
	}
}

class AngelicMonty extends Monty {

	AngelicMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		openChosenDoor();
	}

	protected void generateLoseVec() {
		standardLose();
	}
}

class IgnorantMonty extends Monty {

	IgnorantMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		standardWin();
	}
}

class SecretiveMonty extends Monty {

	SecretiveMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		notOpenDoor();
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

class IndifferentMonty extends Monty {

	IndifferentMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardWin();
		else
			notOpenDoor();
	}

	protected void generateLoseVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardLose();
		else
			notOpenDoor();
	}
}

class LazyMonty extends Monty {

	LazyMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}

		for (int i = 1; i <= n_doors; ++i) {
			if (i != chosen_door) {
				ProbVec[i] = 1;
				break;
			}
		}
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}

		for(int i = 1; i <= n_doors; ++i) {
			if (i != chosen_door && i != prize) {
				ProbVec[i] = 1;
				break;
			}
		}
	}
}

class SuperLazyMonty extends Monty {

	SuperLazyMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[1] = 1;
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[1] = 1;
	}
}

class HealthyMonty extends Monty {

	HealthyMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}

		for(int i = n_doors; i > 0; --i) {
			if (i != chosen_door) {
				ProbVec[i] = 1;
				break;
			}
		}
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}

		for(int i = n_doors; i > 0; --i) {
			if (i != chosen_door && i != prize) {
				ProbVec[i] = 1;
				break;
			}
		}
	}
}

class SuperHealthyMonty extends Monty {

	SuperHealthyMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[n_doors] = 1;
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[n_doors] = 1;
	}
}

class BehavioralBlueprintMonty extends Monty {

	private String winning_behavior, losing_behavior;

	BehavioralBlueprintMonty(int n_doors, int chosen_door, String winning_behavior, String losing_behavior) {
		super(n_doors, chosen_door);
		this.winning_behavior = winning_behavior;
		this.losing_behavior = losing_behavior;
	}

	protected void generateWinVec() {
		if (winning_behavior.equalsIgnoreCase("standard") || 
			winning_behavior.equalsIgnoreCase("Ignorant Random"))
			standardWin();
		else if (winning_behavior.equalsIgnoreCase("Totally Random"))
			random();
		else if (winning_behavior.equalsIgnoreCase("Generous"))
			openChosenDoor();
		else if (winning_behavior.equalsIgnoreCase("Secretive"))
			notOpenDoor();
	}

	protected void generateLoseVec() {
		if (losing_behavior.equalsIgnoreCase("standard"))
			standardLose();
		else if (losing_behavior.equalsIgnoreCase("Ignorant Random"))
			standardWin();
		else if (losing_behavior.equalsIgnoreCase("Totally Random"))
			random();
		else if (losing_behavior.equalsIgnoreCase("Too Bad"))
			openChosenDoor();
		else if (losing_behavior.equalsIgnoreCase("In your Face"))
			openPrizeDoor();
		else if (losing_behavior.equalsIgnoreCase("Secretive"))
			notOpenDoor();
	}

}

//pair class that I wrote for simplicity
//useful for QLearningMonty
class Pair<T1,T2> 
{
  T1 p1;
  T2 p2;
  Pair(T1 v1, T2 v2)
  {
	  p1=v1;
	  p2=v2;
  }
  @Override
  public boolean equals(Object obj) 
  { 
	  if (!(obj instanceof Pair)) { 
          return false; 
      } 
	  
      return ((this.p1.equals(((Pair<?,?>)obj).p1)) && (this.p2.equals(((Pair<?,?>)obj).p2))); 
  } 
  
  @Override
  public int hashCode() {
	  
      return p1.hashCode()+p2.hashCode(); 
  }
  
}

// QLearningMonty based completely on the famous
// active reinforcement learning algorithm called
// "Q - Learning"
// search online for more info or ask questions
//
// General idea:
// It learns the best strategy by playing games
// each game played affects the q-values (q_map)
// at the end, the best choice conditioned on specified state is chosen
// state = (prize_door,initial_door_picked_by_player)
// action = door chosen
//
// Algorithm currently learns over all possible states
//
// Once the algorithm is done with learning, it applies its results
// to the real problem (some fixed state given in constructor).
// In reality we will train 1 time and then can run the program many times
// (code will have to change a little bit) instead of learning every time.
//
// genWinVec() and genLoseVec() just choose
// the action (monty choice) with maximum q-value for given state
// and give this door prob of 1. (All other doors are 0)
//
// The result of q-learning completely depends on 
// LearningAgent (see the corresponding class) i.e.
// the behavior of opponent
// 
// Try to play around with different LearningAgents (1 is provided) and convince 
// yourself that it works
//
// For simplicity, I print the final q-values (will be removed in real scenario). You should check which gives 
// q-value the best and understand why. Note that prize door each time is randm.
//
//
class QLearningMonty extends Monty{
	
	private HashMap<Pair<Pair<Integer,Integer>,Integer>, Double> q_map = new HashMap<Pair<Pair<Integer,Integer>,Integer>, Double>(); 
	
	double learningRatio = 0.3;
	double explorationRatio = 0.5;
	int iterations = 1000;
	LearningAgent learningAgent;
	
	QLearningMonty(int n_doors, int chosen_door, LearningAgent learningAg) {
		super(n_doors, chosen_door);
		
		learningAgent = learningAg;
		
		for(int i = 1; i<=n_doors; ++i)
		{
			for(int j=1; j<=n_doors; ++j)
			{
				for(int k=1; k<=n_doors; ++k)
				{
					Pair<Pair<Integer,Integer>,Integer> obj = new Pair<Pair<Integer,Integer>,Integer>(new Pair<Integer,Integer>(i,j),k);
					q_map.put(obj,0.0);
				}
			}
		}
		//learn here
		qLearning();
	}
	
	
	protected void qLearning()
	{
		for(int iter=0; iter<iterations; ++iter)
		{
			Random ran = new Random();
			int train_prize = ran.nextInt(n_doors)+1;
			System.out.println(train_prize);
			int train_chosen_door = learningAgent.chooseDoor();
			System.out.println(train_chosen_door);
			double max = -1000000000;
			int max_action = 0;
			
			//exploitation
			double prob = Math.random();
			
			if(prob>explorationRatio)
			{
			
				//loop over the values with the correct state (initial door choice, player's choice)
				for(int i = 1; i <= n_doors; ++i) {
					double temp = q_map.get(new Pair<Pair<Integer,Integer>,Integer>(new Pair<Integer,Integer>(train_prize,train_chosen_door),i));
					if(temp > max)
					{
						max = temp;
						max_action = i;
					}
						
				}
			}
			//exploration
			else
			{
				Random ran2 = new Random();
				max_action = ran2.nextInt(n_doors)+1;
				max = q_map.get(new Pair<Pair<Integer,Integer>,Integer>(new Pair<Integer,Integer>(train_prize,train_chosen_door),max_action));
				
			}
			int result = 0;
			//if prize == monty's choice (max action) then we lose immediately
			if(max_action == train_prize)
				result = -1;
			else
			{
				int final_door = learningAgent.switchDoor(train_chosen_door, max_action);
				//if player chooses the correct door, we lose
				if(final_door == train_prize)
					result = -1;
				//otherwise we win
				else
					result = 1;
			}
			//update part
			//check update rule for q-learning.
			//in this problem, it is very simplified since the game itself is very simple
			q_map.put(new Pair<Pair<Integer,Integer>,Integer>(new Pair<Integer,Integer>(train_prize,train_chosen_door),max_action),(1-learningRatio)*max + learningRatio * result);
		}
		
	}
	
	protected void generateWinVec() {
		//initialize all values to 0
		for(int i = 1; i <= n_doors; ++i) 
		{
			ProbVec[i] = 0;
		}
		double max = -1000000000;
		int max_action = 0;
		//loop over the values with the correct state (initial door choice, player's choice)
		for(int i = 1; i <= n_doors; ++i) 
		{
			double temp = q_map.get(new Pair<Pair<Integer,Integer>,Integer>(new Pair<Integer,Integer>(prize,chosen_door),i));
			System.out.print("prize=");
			System.out.println(prize);
			System.out.print("chosen_door=");
			System.out.println(chosen_door);
			System.out.print("action=");
			System.out.println(i);
			System.out.print("Q-value=");
			System.out.println(temp);
			System.out.println();
			if(temp > max)
			{
				max = temp;
				max_action = i;
			}
				
		}
		
		ProbVec[max_action] = 1;
	}

	protected void generateLoseVec() {
		generateWinVec();
	}
}


