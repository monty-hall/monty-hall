#include <vector>
#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <cstdlib>
#include <stdlib.h>
#include <time.h>

using namespace std;
#include "regular.h"

RegularMonty::RegularMonty(int doors, int win_door) {
	num_doors = doors;
	winning_door = win_door;
}

int RegularMonty::SwitchDoor(int chosen_door) {
	srand(time(NULL));
	int switch_door = winning_door;
	while(switch_door == winning_door or switch_door == chosen_door) {
		switch_door = rand()%num_doors + 1;
	}
	return switch_door;
}

bool RegularMonty::winner(int chosen_door) {
	if(chosen_door == winning_door) {
		return true;
	}
	else {
		return false;
	}
}

void RegularMonty::execute(ostream& dbfile) {
	dbfile << "regular" << endl;
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
	int prev_door = chosen_door;
	chosen_door = -1;
	while((chosen_door < 1 or chosen_door > num_doors or chosen_door == goat_door)) {
		cout << "What door do you pick?" << endl;
		cin >> chosen_door;
		if(chosen_door == goat_door) {
			cout << "I said that door contains a goat." << endl;
		}
	}
	if(prev_door == chosen_door) {
		dbfile << "same" << endl;
	}
	if(prev_door != chosen_door) {
		dbfile << "switch" << endl;
	}
	if(Monty.winner(chosen_door)){
		cout << "You Win!" << endl;
		dbfile << "win" << endl;
	}
	if(!Monty.winner(chosen_door)) {
		cout << "You Lose!" << endl;
		dbfile << "lose" << endl;
	}

}