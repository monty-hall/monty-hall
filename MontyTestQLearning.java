package monty;

import java.util.Random;

public class MontyTestQLearning {
	
	public static void main(String[] args) {

		//No Monty selection function for now, will add later.
		//For now, you can manually change types
		LearningAgent a = new LeftLearningAgent(3);
		Monty m = new QLearningMonty(3,2,a);
		int open_door = m.getOpenDoor();
		System.out.println("Final choice of Strategic Monty");
		System.out.println(open_door);
    }

}
