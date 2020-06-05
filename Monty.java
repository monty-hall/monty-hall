import java.util.*;


abstract class Monty {
	public int n_doors, chosen_door, prize;

	Monty(int n, int c, int p) {
		n_doors = n;
		chosen_door = c;
		prize = p;
	}

	public boolean check_win() {
		return chosen_door == prize;
	}

	public void switch_door(int d) {
		chosen_door = d;
	}

	public void execute() {
		System.out.println("The door you pick is " + chosen_door);
		int open_door = open_door();
		if (open_door != 0) {
			System.out.println("Open door " + open_door);
			System.out.print("Switch door Y/N ");
			Scanner scan = new Scanner(System.in);
			String t = scan.next();
			if (t.equalsIgnoreCase("Y")) {
				System.out.print("switch door = ");
				int temp = scan.nextInt();
				assert temp <= n_doors;
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


