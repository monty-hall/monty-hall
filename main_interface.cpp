#include <vector>
#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <cstdlib>
#include <stdlib.h>
#include <time.h>

#include "regular.h"
using namespace std;

int main(int argc, char* argv[]) {
	srand(time(NULL));
	string monty_type = argv[1];
	int num_doors = atoi(argv[2]);
	int winning_door = rand()%num_doors + 1;

	if(monty_type == "regular") {
		cout << "TESTING: WINNING DOOR IS " << winning_door << endl;
		RegularMonty Monty(num_doors,winning_door);
		int chosen_door = -1;
		while(chosen_door < 1 or chosen_door > num_doors) {
			cout << "What door do you pick?" << endl;
			cin >> chosen_door;
		}
		int goat_door = Monty.SwitchDoor(chosen_door);
		cout << "Looks like door " << goat_door << " is a goat." << endl;
		cout << "I'll let you pick another door if you want." << endl;
		chosen_door = -1;
		while((chosen_door < 1 or chosen_door > num_doors)) {
			cout << "What door do you pick?" << endl;
			cin >> chosen_door;
			if(chosen_door == goat_door) {
				cout << "I said that door contains a goat." << endl;
			}
		}
		if(Monty.winner(chosen_door)){
			cout << "You Win!" << endl;
		}
		else {
			cout << "You Lose!" << endl;
		}
	}	
	return 0;
}