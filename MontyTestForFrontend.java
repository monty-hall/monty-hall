package monty;

import java.util.Random;

public class MontyTestForFrontend {
	
	public static void main(String[] args) {

		//No Monty selection function for now, will add later.
		//For now, you can manually change types
		Monty m = new StandardMonty(3,2);
		boolean cont = m.startGame();
		int prize_door = m.get_prize();
		int open_door = m.get_open_door();
		//new choice from frontend should go here
		int new_choice = 1;
		if(cont)
		{
			m.finishGame(new_choice);
		}
    }

}
