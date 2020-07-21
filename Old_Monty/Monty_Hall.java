//package monty;

import java.util.*;

public class Monty_Hall {

    public static void main(String[] args) {

        // DEFAULT ARGUMENT VALUES
        int n_doors = 3;
        boolean chosen = false;
        int chosen_door = 1;
        boolean monty_type_secret = false; //true if monty type is secret, false otherwise
        double probability = 0.5;
        
        //Game Set-up

        Random rand = new Random(System.currentTimeMillis());
        int prize = rand.nextInt(n_doors) + 1;
        Monty m = null;
        
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
            else
            {
            	MontyFactory mf= new MontyFactory();
            	m = mf.getMonty(n_doors, chosen_door, prize, probability,args[i]);
            }
        }   
        
        m.execute();

    }
} 
