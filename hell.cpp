#include <vector>
#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <cstdlib>
#include <stdlib.h>
#include <time.h>

using namespace std;
#include "hell.h"

HellMonty::HellMonty(int doors, int win_door) {
	num_doors = doors;
	winning_door = win_door;
}

int HellMonty::SwitchDoor(int chosen_door) {
	if(chosen_door == winning_door) {
		srand(time(NULL));
		int switch_door = winning_door;
		while(switch_door == winning_door) {
			switch_door = rand()%num_doors + 1;
		}
		return switch_door;
	}
	else {
		return -1;
	}
}

bool HellMonty::winner(int chosen_door) {
	if(chosen_door == winning_door) {
		return true;
	}
	else {
		return false;
	}
}

void HellMonty::execute(ostream& dbfile) {
	dbfile << "hell" << endl;
	cout << "TESTING: WINNING DOOR IS " << winning_door << endl;
	HellMonty Monty(num_doors,winning_door);
	int chosen_door = -1;
	while(chosen_door < 1 or chosen_door > num_doors) {
		cout << "What door do you pick?" << endl;
		cin >> chosen_door;
	}
	int goat_door = Monty.SwitchDoor(chosen_door);
	if(goat_door != -1) {
		cout << "Looks like door " << goat_door << " is a goat." << endl;
		cout << "I'll let you pick another door if you want." << endl;
		chosen_door = -1;
		while((chosen_door < 1 or chosen_door > num_doors or chosen_door == goat_door)) {
			cout << "What door do you pick?" << endl;
			cin >> chosen_door;
			if(chosen_door == goat_door) {
				cout << "I said that door contains a goat." << endl;
			}
		}
		if(Monty.winner(chosen_door)){
			cout << "You Win!" << endl;
			dbfile << "win" << endl;
		}
		else {
			cout << "You Lose!" << endl;
			dbfile << "lose" << endl;
		}
	}
	else {
		cout << "You Lose!" << endl;
		dbfile << "lose" << endl;
	}
}