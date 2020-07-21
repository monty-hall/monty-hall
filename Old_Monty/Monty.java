//package monty;

import java.util.*;

class MontyFactory{
	
	public Monty getMonty(int n_doors, int chosen_door, int prize, double probability, String monty_type)
	{
		Monty m = null;
		if(monty_type.equals("--random"))
		{
			String[] list = {"--regular","--evil","--angelic","--prepared","--probabilistic","--sandbox"};
			Random r = new Random();
			monty_type = list[r.nextInt(list.length)];
		}
		if (monty_type.equals("--regular")) { m = new RegularMonty(n_doors, chosen_door, prize); }
		else if (monty_type.equals("--evil")) { m = new EvilMonty(n_doors, chosen_door, prize); }
		else if (monty_type.equals("--angelic")) { m = new AngelicMonty(n_doors, chosen_door, prize); }
		else if (monty_type.equals("--prepared")) { m = new PreparedMonty(n_doors, chosen_door, prize); }
		else if (monty_type.equals("--probabilistic")) { m = new ProbabilisticMonty(n_doors, chosen_door, prize); }
		else if (monty_type.equals("--sandbox")) { m = new SandboxMonty(n_doors, chosen_door, prize); }
		else throw new IllegalArgumentException("Incorrect Argument or Monty Type");
		return m;
	}
	
}


abstract class Monty {
	public int n_doors, chosen_door, prize;

	Monty(int n_doors, int chosen_door, int prize) {
		this.n_doors = n_doors;
		this.chosen_door = chosen_door;
		this.prize = prize;
	}

	public boolean check_win() {
		return chosen_door == prize;
	}

	public boolean check_door(int door) {
		return door == prize;
	}

	/* MONTY REACTIONS */

	/* Standard: Monty picks and opens one of the other empty doors with equal likelihood. */
	public int open_empty_door() {
		int result = 0;
		while (result == 0 || result == chosen_door || result == prize) {
			Random rand = new Random();
    		result = rand.nextInt(n_doors) + 1;
		}
		return result;
	}

	/* Too Bad!/Generous: Monty opens the very door you picked and reveals you win/did not win the prize. */
	public int open_chosen_door() {
		return chosen_door;
	}

	/* In your Face!: Monty opens the door with the prize. */
	public int open_prize_door() {
		return prize;
	}

	/* Secretive: Monty does not open any door at all. */ 
	public int not_open_door() {
		return 0;
	}

	/* Lazy: Monty opens the closest (lowest number) empty door. */
	public int open_closest_door() {
		for (int i = 1; i <= n_doors; ++i) {
			if (i != chosen_door && i != prize)
				return i;
		}
		return 0;
	}

	/* Healthy: Monty opens the empty door furthest away. */
	public int open_furthest_door() {
		for (int i = n_doors; i > 0; --i) {
			if (i != chosen_door && i != prize)
				return i;
		}
		return 0;
	}

	public int open_random_door() {
		Random rand = new Random();
		return rand.nextInt(n_doors) + 1;
	}

	public void switch_door(int d) {
		chosen_door = d;
	}

	public void execute() {
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

		if (check_win()) { 
			System.out.println("WIN"); 
		}
		else { 
			System.out.println("LOSE");
		}

		System.out.println("Prize was behind the door " + prize);
	}

	abstract public int open_door();
}

class RegularMonty extends Monty {

	RegularMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		return open_empty_door();
	}
}

class EvilMonty extends Monty {

	EvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		if (check_win())
			return open_empty_door();
		else 
			return open_prize_door();
	}
}

class LessEvilMonty extends EvilMonty {

	LessEvilMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		if (check_win())
			return open_empty_door();
		else
			return open_chosen_door();
	}
}

class AngelicMonty extends Monty {

	AngelicMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
	}

	public int open_door() {
		if (check_win())
			return open_chosen_door();
		else 
			return open_empty_door();
	}
}

class SandboxMonty extends Monty {
	private double[][] probability_matrix;
	SandboxMonty(int n_doors, int chosen_door, int prize) {
		super(n_doors, chosen_door, prize);
		probability_matrix = new double[n_doors][n_doors];
	}

	public void set_prize(int prize) {
		this.prize = prize;
	}

	public void load_matrix() {
		Scanner scan = new Scanner(System.in);
		for (int i = 0 ; i < n_doors; ++i) {
			double check_sum = 0;
			for (int j = 0; j < n_doors; ++j) {
				double temp = scan.nextDouble();

				assert temp <= 1;
				assert temp >= 0;
				if (j == prize - 1) assert temp == 0;

				probability_matrix[i][j] = temp;
				check_sum += temp;
			}
			assert check_sum == 1;
		}
		parse_matrix();
	}

	public void parse_matrix() {
		for (int i = 0; i < n_doors; ++i) {
			double running_sum = 0;
			for (int j = 0; j < n_doors; ++j) {
				running_sum += probability_matrix[i][j];
				probability_matrix[i][j] = running_sum;
			}
		}
	}

	public void display_matrix() {
		for (int i = 0; i < n_doors; ++i) {
			for (int j = 0; j < n_doors; ++j) {
				System.out.print(probability_matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public int open_door() {
		Random rand = new Random();
		double temp = rand.nextDouble();

		for (int i = 0; i < n_doors; ++i) {
			if (i == prize - 1) {
				continue;
			}
			else if (temp < probability_matrix[chosen_door - 1][i]) {
				return i + 1;
			}
		}
		return 0;
	}

	public void execute() {
		System.out.println("\nSANDBOX MODE");
		System.out.print("Set prize behind door #");
		Scanner scan = new Scanner(System.in);
		set_prize(scan.nextInt());
		System.out.println("\nEnter an N x N probability matrix:");
		load_matrix();
		System.out.println();
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
		System.out.println("Prize was behind the door " + prize);
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

