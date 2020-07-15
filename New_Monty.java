//package monty;

import java.util.Random;
import java.util.Scanner;

//To run this class with the old main code, rename it to be
//Monty (replace all New_Monty with Monty and change file name to
//Monty.java).
//
//Also note, that the input/output will be a little bit different
//Now, we only ask for a new door number(which may be the same) instead of asking to switch (Y/N etc).
//So, adjust your user input accordingly.
abstract class New_Monty {

	protected int n_doors, chosen_door, prize;
	
	//Vector of probabilities for Monty to choose
	//any door (note: door 0 is reserved for technical things
	//actual real doors are doors 1,2,...,n_doors
	protected double ProbVec[];

	New_Monty(int n_doors, int chosen_door, int prize) 
	{
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		this.prize = prize;
		ProbVec = new double[n_doors+1];
	}
	protected boolean check_win() 
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

	public void execute() 
	{
		generateVec();
		int open_door = open_door();
		if (open_door != 0) 
		{
			System.out.println("Monty opens the door " + open_door);
			if(check_door(open_door)) {
				System.out.println(open_door + " is a car.");
				System.out.println("LOSE");
				return;
			}
			else {
				System.out.println(open_door + " is not a car.");
			}
			
			System.out.println("Monty offers you to switch a door. Switch to any new door or stay with your old choice. Your new door = ");
			Scanner scan = new Scanner(System.in);
			int temp = scan.nextInt();
			assert temp <= n_doors;
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
		if (check_win()) { System.out.println("WIN"); }
		else { System.out.println("LOSE");}
		System.out.println("Prize was behind the door " + prize);
	}
	
	
	//Open door always works the same way for all Monty's
	//If 0 is returned, then no door is opened (it may happen for
	//different reasons). Usually, Monty will open one of the doors
	protected int open_door()
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
}


//StandardMonty type, create other Monty types based on that
//Please, create all the Monty types with names according to Bram's
//presentation, so we have consistency.
class StandardMonty extends New_Monty {

	StandardMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
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
