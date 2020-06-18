//package monty;

import java.util.*;


abstract class Monty {
	public int n_doors, chosen_door, prize;

	Monty(int n_doors, int chosen_door, int prize) {
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		this.prize = prize;
		// if(chosen_door<1 || chosen_door > n_doors)
		// {
		// 	System.out.println("Your have chosen an invalid door");
		// 	//do something else here!
		// 	System.exit(1);
		// }
	}

	public boolean check_win() {
		return chosen_door == prize;
	}

	public void switch_door(int d) {
		chosen_door = d;
	}

	public boolean check_door(int door) {
		return door == prize;
	}

	public void execute() {
		// System.out.println("What door do you pick?");
		// Scanner door = new Scanner(System.in);
		// chosen_door = Integer.parseInt(door.next());
		int open_door = open_door();
		if (open_door != 0) {
			System.out.println("Monty opens the door " + open_door);
			if(check_door(open_door)) {
				System.out.println(open_door + " is a car.");
				System.out.println("LOSE");
				return;
			}
			else {
				System.out.println(open_door + " is not a car.");
			}
			System.out.print("Monty offers you to switch a door. Do you want to switch? Y/N: ");
			Scanner scan = new Scanner(System.in);
			String t = scan.next();
			if (t.equalsIgnoreCase("Y")) {
				System.out.print("What door do you want to switch to? Door = ");

				int temp = scan.nextInt();
				assert temp <= n_doors;
				if(temp < 1 || temp > n_doors)
				{
					System.out.println("Your have chosen an invalid door");
					//do something else here!
					System.exit(1);
				}
				if(temp == chosen_door)
				{
					System.out.println("You cannot switch to the same door");
					System.exit(1);
				}
				switch_door(temp);
			}
		}

		if (check_win()) { System.out.println("WIN"); }
		else { System.out.println("LOSE");}
	}

	abstract public int open_door();
}


class RegularMonty extends Monty {

	RegularMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		int result = 0;
		while (result == 0 || result == chosen_door || result == prize) {
			Random rand = new Random();
    		result = rand.nextInt(n_doors) + 1;
		}
		return result;
	}
}


class EvilMonty extends Monty {
	EvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		int result = 0;
		if (chosen_door == prize) {
			while (result == 0 || result == chosen_door || result == prize) {
				Random rand = new Random();
	    		result = rand.nextInt(n_doors) + 1;
			}
		}
		return result;
	}
}


class AngelicMonty extends Monty {
	AngelicMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		int result = 0;
		if (chosen_door != prize) {
			while (result == 0 || result == chosen_door || result == prize) {
				Random rand = new Random();
	    		result = rand.nextInt(n_doors) + 1;
			}
		}
		return result;
	}
}

class SandboxMonty extends Monty {
	private double probability;
	SandboxMonty(int n_doors, int chosen_door, int prize, double probability) {
		super(n_doors, chosen_door, prize);
		this.probability = probability;
	}

	public int open_door() {
		Random rand = new Random();
		// Open leftmost door with a probability p
		if (rand.nextDouble() < this.probability) {
			for (int i = 1; i <= n_doors; ++i) {
				if (i != prize && i != chosen_door)
					return i;
			}
		}
		// Open right most door with a prbability 1-p
		else {
			for (int i = n_doors; i > 0; --i) {
				if (i != prize && i != chosen_door)
					return i;
			}
		}
		return 0;
	}
}


/**************************************************************************************************/

//Names for the next two Monty's are not final, feel free to change them to something that sounds better

class PreparedMonty extends Monty{ //this is monty #7 from wikipedia, right after Monty Fall
	PreparedMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door()
	{
		int result = 0;
		int possible_result = 0;
		while (possible_result == 0 || possible_result == prize) {
			Random rand = new Random();
	    	possible_result = rand.nextInt(n_doors) + 1;

		}
		if(possible_result!= chosen_door)
			result = possible_result;
		return result;
	}
}

class ProbabilisticMonty extends Monty{ //this is monty #8 from wikipedia, right after PreparedMonty
	ProbabilisticMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door()
	{
		int result = 0;
		if (chosen_door == prize) 
		{
			while (result == 0 || result == chosen_door || result == prize) {
				Random rand = new Random();
	    		result = rand.nextInt(n_doors) + 1;
			}
		}
		else 
		{
			Random random = new Random();
			if(random.nextBoolean())
				while (result == 0 || result == chosen_door || result == prize) {
					Random rand = new Random();
		    		result = rand.nextInt(n_doors) + 1;
				}
		}
		return result;
	}
}