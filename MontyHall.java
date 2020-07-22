//package monty;

import java.util.*;

public class MontyHall {

    public static void main(String[] args) {

        /* DEFAULT ARGUMENT VALUES */
        int n_doors = 3;
        int chosen_door = 0;
        String winning_behavior = "";
        String losing_behavior = "";
        String monty_type = "";
    

/****************************************************************************/
/* PARSE ARGUMENTS                                                          */
/* The program has reasonable default values for all parameters, but you    */
/* can override the defaults by providing any or all of these command line  */
/* arguments (in any order):                                                */
/*                                                                          */
/* -n_doors <number of doors>                                               */
/* -chosen_door <player's initial pick door>                                */
/* -w <winning behavior>                                                    */
/* -l <losing behavior>                                                     */
/* -m <default monty behavior>                                              */
/*                                                                          */
/* Here are some sample command lines:                                      */
/* java MontyHall -n_doors 3 -chosen_door 2 -m standard                     */
/* java MontyHall -n_doors 3 -chosen_door 2 -w standard -l in_your_face     */
/* java MontyHall -n_doors 3 -chosen_door 2 -w devious -l in_your_face      */
/****************************************************************************/

        for(int i = 0; i < args.length; ++i) {
            if (args[i].equals("-n_doors")) {
                i++;
                assert i < args.length;
                n_doors = Integer.parseInt(args[i]);
            } 
            else if (args[i].equals("-chosen_door")) {
                i++;
                assert i < args.length;
                chosen_door = Integer.parseInt(args[i]);
                assert chosen_door <= n_doors;
            }
            else if (args[i].equals("-w")) {
                i++;
                assert i < args.length;
                winning_behavior = args[i];
            }
            else if (args[i].equals("-l")) {
                i++;
                assert i < args.length;
                losing_behavior = args[i];
            }
            else if (args[i].equals("-m")) {
                i++;
                assert i < args.length;
                monty_type = args[i];
            }
            else {
                System.out.println("ERROR: UNKNOWN ARGUMENT" + args[i]);
                System.exit(1);
            }
        }


        /* Game Set-up */
        Random rand = new Random(System.currentTimeMillis());
        int prize = rand.nextInt(n_doors) + 1;
        if(monty_type.equals(""))
            monty_type = losing_behavior + " " + winning_behavior;
        Monty m = getMonty(n_doors, chosen_door, prize, monty_type); 
    
        m.execute();

    }


    public static Monty getMonty(int n_doors, int chosen_door, int prize, String monty_type) {
        Monty m = null;
        if (monty_type.equalsIgnoreCase("standard standard")) 
            m = new StandardMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("standard")) 
            m = new StandardMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face standard")) 
            m = new InYourFaceStandardMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad standard"))
            m = new TooBadStandardMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("tricky standard"))
            m = new TrickyStandardMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face devious")) 
            m = new InYourFaceDeviousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad devious"))
            m = new TooBadDeviousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("tricky devious"))
            m = new TrickyDeviousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("general devious"))
            m = new GeneralDeviousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("devious"))
            m = new GeneralDeviousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("evil"))
            m = new EvilMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face evil"))
            m = new InYourFaceEvilMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad evil"))
            m = new TooBadEvilMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face equilibrium"))
            m = new InYourFaceEquilibriumMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad equilibrium"))
            m = new TooBadEquilibriumMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("tricky equilibrium"))
            m = new TrickyEquilibriumMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("general equilibrium"))
            m = new GeneralEquilibriumMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face generous"))
            m = new InYourFaceGenerousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad generous"))
            m = new TooBadGenerousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("tricky generous"))
            m = new TrickyGenerousMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("in_your_face secretive"))
            m = new InYourFaceSecretiveMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("too_bad secretive"))
            m = new TooBadSecretiveMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("tricky secretive"))
            m = new TrickySecretiveMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("indifferent"))
            m = new IndifferentMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("ignorant"))
            m = new IgnorantMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("lazy"))
            m = new LazyMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("super_lazy"))
            m = new SuperLazyMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("healthy"))
            m = new HealthyMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("super_healthy"))
            m = new SuperHealthyMonty(n_doors, chosen_door, prize);
        else if (monty_type.equalsIgnoreCase("random"))
            m = new RandomMonty(n_doors, chosen_door, prize);
        else 
            System.out.println("ERROR: UNKNOWN MONTY TYPE " + monty_type);

        return m;
    }
} 