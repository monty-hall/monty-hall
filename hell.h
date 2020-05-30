#include <vector>
#include <string>

using namespace std;


class HellMonty {
public:
	HellMonty(int doors, int win_door);
	int SwitchDoor(int chosen_door);
	bool winner(int chosen_door);
	int num_doors;
private:
	int winning_door;
};