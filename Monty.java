package monty;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//To run this class with the old main code, rename it to be
//Monty (replace all New_Monty with Monty and change file name to
//Monty.java).
//
//Also note, that the input/output will be a little bit different
//Now, we only ask for a new door number(which may be the same) instead of asking to switch (Y/N etc).
//So, adjust your user input accordingly.
abstract class Monty {
	
	protected int n_doors, chosen_door, prize, open_door;
	
	//Vector of probabilities for Monty to choose
	//any door (note: door 0 is reserved for technical things
	//actual real doors are doors 1,2,...,n_doors
	protected double ProbVec[];

	Monty(int n_doors, int chosen_door, int prize) 
	{
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		this.prize = prize;
		ProbVec = new double[n_doors+1];
	}
	
	Monty(int n_doors,int chosen_door)
	{
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		Random rand = new Random(System.currentTimeMillis());
		this.prize = rand.nextInt(n_doors) + 1;
		ProbVec = new double[n_doors+1];
	}
	public boolean check_win() 
	{
			return chosen_door == prize;
	}

	protected void switch_door(int d) 
	{
			chosen_door = d;
	}

	protected boolean check_door(int door) 
	{
			return door == prize;
	}
	public int get_chosen_door()
	{
		return chosen_door;
	}
	
	public int get_open_door()
	{
		return open_door;
	}
	public int get_prize()
	{
		return prize;
	}
	
	
	//implement a method for player initially picking the winning door
	abstract protected void generateWinVec();
	
	//implement a method for player initially picking the loosing door
	abstract protected void generateLoseVec();
	
	//This method will determine which startegy to choose (based on player's choice)
	protected void generateVec()
	{
		if(check_door(chosen_door))
			generateWinVec();
		else
			generateLoseVec();
	}
	//returns true if game continues after start, false
	//if game ends (you should not call finishGame() in this case)
	public boolean startGame()
	{
		generateVec();
		open_door = openDoor();
		if(check_door(open_door)) {
			return false;
		}
		return true;
	}
	public void finishGame(int new_choice)
	{
		if(new_choice < 1 || new_choice > this.n_doors)
		{
			System.out.println("Your have chosen an invalid door");
			System.exit(1);
		}
		switch_door(new_choice);
	}
	//return true if win, else if lose
	//mostly to simplify testing, but also may be useful
	//in the future 
	public boolean execute() 
	{
		boolean result;
		generateVec();
		open_door = openDoor();
		if (open_door != 0) 
		{
			System.out.println("Monty opens the door " + open_door);
			if(check_door(open_door)) {
				System.out.println(open_door + " is a car.");
				System.out.println("LOSE");
				return false;
			}
			else {
				System.out.println(open_door + " is not a car.");
			}
			
			System.out.println("Monty offers you to switch a door. Switch to any new door or stay with your old choice. Your new door = ");
			Scanner scan = new Scanner(System.in);
			int temp = scan.nextInt();
			while(temp == open_door)
			{
				System.out.println("This door is already open, choose some other door. Your new door = ");
				temp = scan.nextInt();
			}
			try {
				System.in.read(new byte[System.in.available()]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assert temp <= n_doors;
			if(temp == open_door)
			if(temp < 1 || temp > n_doors)
			{
				System.out.println("Your have chosen an invalid door");
				//do something else here!
				System.exit(1);
			}
			switch_door(temp);
		}
		else
		{
			System.out.println("Monty did not open any door");
		}
		
		//We also probably need to rethink criteria for winning/losing
		//Consider devious Monty, etc.
		if (check_win()) { System.out.println("WIN"); result=true; }
		else { System.out.println("LOSE");result=false; }
		System.out.println("Prize was behind the door " + prize);
		return result;
	}
	
	
	//Open door always works the same way for all Monty's
	//If 0 is returned, then no door is opened (it may happen for
	//different reasons). Usually, Monty will open one of the doors
	protected int openDoor()
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
}


//StandardMonty type, create other Monty types based on that
//Please, create all the Monty types with names according to Bram's
//presentation, so we have consistency.
class StandardMonty extends Monty {

	StandardMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	StandardMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}
	
	//For standard Monty, if player chooses a correct(winning) door, 
	//We fill the vector with probabilities 1/(n_doors-1)
	//except for the winning door, which is 0. (So, basically we open any other
	//door with equal probability)
	protected void generateWinVec()
	{
		//door 0 is technical, it means Monty does not open any door
		//Almost always should be 0
		ProbVec[0]=0;
		for(int i=1; i<=n_doors; ++i)
		{
			if(i!=chosen_door)
				ProbVec[i]=(double)1/(n_doors-1);
			else
				ProbVec[i]=0;
		}
	}
	
	//For standard Monty, if player chooses a wrong door,
	//We fill the prob Vector with probabilities 1/(n_doors-2)
	//(equal probability) except for the winning door or door 
	//chosen by the player initially (these probabilities are 0)
	//For 3-door Monty, we will always the same door (the one that is not
	//chosen by player and not winning).
	protected void generateLoseVec()
	{
		ProbVec[0]=0;
		for(int i=1; i<=n_doors; ++i)
		{
			if(i!=chosen_door && i!=prize)
				ProbVec[i]=(double)1/(n_doors-2);
			else 
				ProbVec[i]=0;
		}
	}

}

class InYourFaceStandardMonty extends StandardMonty {

	InYourFaceStandardMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceStandardMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}


	protected void generateLoseVec() {
		openPrizeDoor();
	}
}

class TooBadStandardMonty extends StandardMonty {
	TooBadStandardMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadStandardMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class TrickyStandardMonty extends StandardMonty {
	TrickyStandardMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

/******************************************************************************/

class DeviousMonty extends Monty {

	DeviousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	DeviousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		return;
	}
}

class TrickyDeviousMonty extends DeviousMonty {

	TrickyDeviousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TrickyDeviousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

class TooBadDeviousMonty extends DeviousMonty {

	TooBadDeviousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadDeviousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class InYourFaceDeviousMonty extends DeviousMonty {

	InYourFaceDeviousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceDeviousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openPrizeDoor();
	}
}

class GeneralDeviousMonty extends DeviousMonty {

	GeneralDeviousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	GeneralDeviousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}

		Random rand = new Random();
		if (rand.nextBoolean())
			ProbVec[prize] = 1;
		else if (rand.nextBoolean()) 
			ProbVec[chosen_door] = 1;
	}
}

/******************************************************************************/

class EvilMonty extends Monty {

	EvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	EvilMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}


	protected void generateWinVec() {
		notOpenDoor();
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

class TooBadEvilMonty extends EvilMonty {

	TooBadEvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadEvilMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class InYourFaceEvilMonty extends EvilMonty {

	InYourFaceEvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceEvilMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}


	protected void generateLoseVec() {
		openPrizeDoor();
	}
}

/******************************************************************************/

class EquilibriumMonty extends Monty {

	EquilibriumMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	EquilibriumMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		standardWin();
	}

	protected void generateLoseVec() {
		return;
	}
}

class TrickyEquilibriumMonty extends EquilibriumMonty {

	TrickyEquilibriumMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TrickyEquilibriumMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardLose();
		else
			notOpenDoor();
	}
}

class TooBadEquilibriumMonty extends EquilibriumMonty {

	TooBadEquilibriumMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadEquilibriumMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardLose();
		else
			openChosenDoor();
	}
}

class InYourFaceEquilibriumMonty extends EquilibriumMonty {

	InYourFaceEquilibriumMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceEquilibriumMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardLose();
		else
			openPrizeDoor();
	}
}

class GeneralEquilibriumMonty extends EquilibriumMonty {

	GeneralEquilibriumMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	GeneralEquilibriumMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		Random rand = new Random();
		if (rand.nextBoolean())
			standardLose();
		else {
			for(int i = 0; i <= n_doors; ++i) {
				ProbVec[i] = 0;
			}

			if (rand.nextBoolean())
				ProbVec[prize] = 1;
			else if (rand.nextBoolean()) 
				ProbVec[chosen_door] = 1;
		}
	}
}

/******************************************************************************/

class GeneralGenerousMonty extends Monty {

	GeneralGenerousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	GeneralGenerousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		openPrizeDoor();
	}

	protected void generateLoseVec() {
		return;
	}
} 

class InYourFaceGenerousMonty extends GeneralGenerousMonty {

	InYourFaceGenerousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceGenerousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openPrizeDoor();
	}
} 

class TooBadGenerousMonty extends GeneralGenerousMonty {

	TooBadGenerousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadGenerousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class TrickyGenerousMonty extends GeneralGenerousMonty {

	TrickyGenerousMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TrickyGenerousMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

/******************************************************************************/

class GeneralSecretiveMonty extends Monty {

	GeneralSecretiveMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	GeneralSecretiveMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		notOpenDoor();
	}

	protected void generateLoseVec() {
		return;
	}
}

class InYourFaceSecretiveMonty extends GeneralSecretiveMonty {

	InYourFaceSecretiveMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	InYourFaceSecretiveMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openPrizeDoor();
	}
}

class TooBadSecretiveMonty extends GeneralSecretiveMonty {

	TooBadSecretiveMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TooBadSecretiveMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		openChosenDoor();
	}
}

class TrickySecretiveMonty extends GeneralSecretiveMonty {

	TrickySecretiveMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	TrickySecretiveMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateLoseVec() {
		notOpenDoor();
	}
}

/******************************************************************************/

class IndifferentMonty extends Monty {

	IndifferentMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

class IgnorantMonty extends Monty {

	IgnorantMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

class LazyMonty extends Monty {

	LazyMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

	SuperLazyMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

	HealthyMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

	SuperHealthyMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
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

class RandomMonty extends Monty {

	RandomMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	RandomMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			ProbVec[i] = (double)1/n_doors;
		}
	}

	protected void generateLoseVec() {
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			ProbVec[i] = (double)1/n_doors;
		}
	}
}

class StrictlyPreferentialMonty extends Monty {

	StrictlyPreferentialMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	StrictlyPreferentialMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	int[] rank_order = new int[n_doors];

	protected void setRankOrder() {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < rank_order.length; ++i) {
			rank_order[i] = scan.nextInt();
		}
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		for(int i = 0 ; i < rank_order.length; ++i) {
			if (rank_order[i] != chosen_door) {
				ProbVec[rank_order[i]] = 1;
				break;
			}
		}
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		for(int i = 0 ; i < rank_order.length; ++i) {
			if (rank_order[i] != chosen_door && rank_order[i] != prize) {
				ProbVec[rank_order[i]] = 1;
				break;
			}
		}
	}
}

class SuperStrictlyPreferentialMonty extends StrictlyPreferentialMonty {

	SuperStrictlyPreferentialMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	SuperStrictlyPreferentialMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}

	protected void generateWinVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[rank_order[1]] = 1;
	}

	protected void generateLoseVec() {
		for(int i = 0; i <= n_doors; ++i) {
			ProbVec[i] = 0;
		}
		ProbVec[rank_order[1]] = 1;
	}
}

class PreferentialMonty extends Monty {
	PreferentialMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}
	PreferentialMonty(int n_doors, int chosen_door) {
		super(n_doors, chosen_door);
	}


	protected void generateWinVec() {
		Scanner scan = new Scanner(System.in);
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			ProbVec[i] = scan.nextDouble();
		}
	}

	protected void generateLoseVec() {
		Scanner scan = new Scanner(System.in);
		ProbVec[0] = 0;
		for(int i = 1; i <= n_doors; ++i) {
			ProbVec[i] = scan.nextDouble();
		}
	}
}