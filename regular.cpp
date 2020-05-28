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