#include <vector>
#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>
#include <fstream>
#include <cstdlib>
#include <stdlib.h>
#include <time.h>

#include "regular.h"
#include "hell.h"
using namespace std;

int main(int argc, char* argv[]) {
	//randomizing seed of random number generator
	srand(time(NULL));
	//command line: monty_type and number of doors
	string monty_type = argv[1];
	int num_doors = atoi(argv[2]);
	//randomized winning door
	int winning_door = rand()%num_doors + 1;
	bool try_again = true;
	ofstream dbfile;
  	dbfile.open ("database.txt");

	while(try_again == true) {
		//regular monty simulation
		if(monty_type == "regular") {
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
			}
			else {
				cout << "You Lose!" << endl;
			}
		}
		//hell_monty
		if(monty_type == "hell") {
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
		//parsing data into textfile
		string repeat;
		while(repeat != "Y" and repeat != "N") {
			cout << "Try again? ENTER Y or N" << endl;
			cin >> repeat;
		}
		if(repeat == "N") {
			break;
		}
	}
	dbfile << endl;
	dbfile.close();
	return 0;
}