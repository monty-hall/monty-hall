//package monty;

import java.util.*;

public class Monty_Hall {

    public static final int REGULAR = 1;
    public static final int EVIL = 2;
    public static final int ANGELIC = 3;
    public static final int PREPARED = 4;
    public static final int PROBABILISTIC = 5;
    public static final int RANDOM = 6;
    public static final int SANDBOX = 7;


    public static void main(String[] args) {

        // DEFAULT ARGUMENT VALUES
        int n_doors = 3;
        boolean chosen = false;
        int chosen_door = 1;
        int monty_type = 0;
        boolean monty_type_secret = false; //true if monty type is secret, false otherwise
        double probability = 0.5;


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
            else if (args[i].equals("--prepared")) { monty_type = PREPARED; }
            else if (args[i].equals("--probabilistic")) { monty_type = PROBABILISTIC; }
            else if (args[i].equals("--random")) { monty_type = RANDOM; }
            else if (args[i].equals("--sandbox")) {
                monty_type = SANDBOX; 
                i++;
                assert i < args.length;
                probability = Double.parseDouble(args[i]);
                assert probability <= 1;
                assert probability >= 0;
            }
            else {
                System.out.println("ERROR: UNKNOWN ARGUMENT " + args[i]);
                System.exit(1);
            }
        }   


        // GAME SET-UP
        Random rand = new Random(System.currentTimeMillis());
        int prize = rand.nextInt(n_doors) + 1;


        if(monty_type == RANDOM) 
        { 
            monty_type = rand.nextInt(5) + 1;
            if(!monty_type_secret)
            {
                System.out.println("Type of random Monty is " +  monty_type);
            }
        }


        Monty m = null;
        if (monty_type == REGULAR) { m = new RegularMonty(n_doors, chosen_door, prize); }
        else if (monty_type == EVIL) { m = new EvilMonty(n_doors, chosen_door, prize); }
        else if (monty_type == ANGELIC) { m = new AngelicMonty(n_doors, chosen_door, prize); }
        else if (monty_type == PREPARED) { m = new PreparedMonty(n_doors, chosen_door, prize); }
        else if (monty_type == PROBABILISTIC) { m = new ProbabilisticMonty(n_doors, chosen_door, prize); }
        else if (monty_type == SANDBOX) { m = new SandboxMonty(n_doors, chosen_door, prize, probability); }

        m.execute();
        System.out.println("Prize was behind the door " + prize);
    }
} 