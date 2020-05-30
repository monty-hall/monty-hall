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
  	dbfile.open ("montydata.txt", ios_base::app);

	while(try_again == true) {
		//regular monty simulation
		if(monty_type == "regular") {
			RegularMonty Monty(num_doors,winning_door);
			Monty.execute(dbfile);
		}
		//hell_monty
		if(monty_type == "hell") {
			HellMonty Monty(num_doors,winning_door);
			Monty.execute(dbfile);
		}
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