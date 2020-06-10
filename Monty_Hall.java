import java.util.*;


public class Monty_Hall {

	public static final int REGULAR = 1;
	public static final int EVIL = 2;
	public static final int ANGELIC = 3;


	public static void main(String[] args) {

		// DEFAULT ARGUMENT VALUES

    	int n_doors = 3;
    	boolean chosen = false;
    	int chosen_door = 0;
    	int monty_type = 0;
    	boolean debug = false;


    	// PARSE ARGUMENTS

    	for(int i = 0; i < args.length; ++i) {
    		if (args[i].equals("--n_doors")) {
    			i++;
    			assert i < args.length;
    			n_doors = Integer.parseInt(args[i]);
    		} else if (args[i].equals("--chosen_door")) {
    			chosen = true;
    			i++;
    			assert i < args.length;
    			chosen_door = Integer.parseInt(args[i]);
    			assert chosen_door <= n_doors;
    		} 
    		else if (args[i].equals("--regular")) { monty_type = REGULAR; }
    		else if (args[i].equals("--evil")) { monty_type = EVIL; }
    		else if (args[i].equals("--angelic")) { monty_type = ANGELIC; }
    		else {
    			System.out.println("ERROR: UNKNOWN ARGUMENT " + args[i]);
    			System.exit(1);
    		}
    	}	


    	// GAME SET-UP

    	Random rand = new Random(System.currentTimeMillis());
    	int prize = rand.nextInt(n_doors) + 1;

    	Monty m = new RegularMonty(n_doors, chosen_door, prize);
    	// if (monty_type == REGULAR) { m = new RegularMonty(n_doors, chosen_door, prize); }
    	if (monty_type == EVIL) { m = new EvilMonty(n_doors, chosen_door, prize); }
    	else if (monty_type == ANGELIC) { m = new AngelicMonty(n_doors, chosen_door, prize); }

        m.execute();
    	System.out.println("prize = " + prize);



	}
}