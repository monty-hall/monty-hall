package monty;

public class MontyHall {
    
    // Set monty m as a global variables.
    Monty m = null;

    public static void main(String[] args) {
        

    }

    // For pre-defined monty.
    public int[] startGame(String mode, int n_doors, int door_1) {
        if (mode.equalsIgnoreCase("standard"))
            m = new StandardMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("hell"))
            m = new MontyFromHell(n_doors, door_1);
        else if (mode.equalsIgnoreCase("evil"))
            m = new EvilMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("angelic"))
            m = new AngelicMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("ignorant"))
            m = new IgnorantMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("secretive"))
            m = new SecretiveMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("indifferent"))
            m = new IndifferentMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("lazy"))
            m = new LazyMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("healthy"))
            m = new HealthyMonty(n_doors, door_1);
        else if (mode.equalsIgnoreCase("strategic"))
        {
        	LearningAgent a = new LeftLearningAgent(n_doors);
        	m = new QLearningMonty(n_doors,door_1,a);
        }

        m.generateVec();
        int[] result = new int[2];
        result[0] = m.getOpenDoor();
        result[1] = m.getPrize();
        return result;
    }

    // Behavioral Blueprint
    public int[] startGame(String winning_mode, String losing_mode, int n_doors, int door_1) {
        Monty m = new BehavioralBlueprintMonty(n_doors, door_1, winning_mode, losing_mode);
        m.generateVec();
        int[] result = new int[2];
        result[0] = m.getOpenDoor();
        result[1] = m.getPrize();
        return result;
    }
} 