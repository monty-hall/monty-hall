
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
	
	//This method will determine which startegy to choose (based on player's choice)
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

