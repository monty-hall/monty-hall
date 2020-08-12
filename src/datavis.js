import React from "react";
import "./App.css";
import 'react-vis/dist/style.css'
import {XYPlot, 
      LineSeries, 
      VerticalGridLines,
      HorizontalGridLines,
      XAxis,
      YAxis,
      Crosshair} from 'react-vis';

const initData = [[
  {x: 0, y: 0}
]];

let num_games = 1;
let num_wins = 0;

function generateData() {
  return initData[0];
}

function getMontyDoor(selectedDoor, carDoor) {
  // console.log(`-----selected ${selectedDoor}`);
  // console.log(`-----car ${carDoor}`);
  var montyDoor = selectedDoor;
  while ((montyDoor == selectedDoor) || (montyDoor == carDoor)) {
    montyDoor = Math.floor(Math.random()*3);
  }
  // console.log(`Monty door: ${montyDoor}`);
  return montyDoor;
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

export default class Datavis extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      data: generateData(),
      crosshairValues: [],
      switch: false,
      carDoor: Math.floor(Math.random()*3),
      setDoor: 0, // chosen from radio buttons
      selectedDoor: 0,
      montyDoor: 1,
      openDoors: [false,false,false],
      step: false,  // for step checkbox
      numSteps: 0,  // for actual stepping
      stepState: 0, // state of current game (0-4)
      message: "Choose a door!"
    };
  }

  /**
   * Event handler for onMouseLeave.
   * @private
   */
  _onMouseLeave = () => {
    this.setState({crosshairValues: []});
  };

  /**
   * Event handler for onNearestX.
   * @param {Object} value Selected value.
   * @param {index} index Index of the value in the data array.
   * @private
   */
  _onNearestX = (value, {index}) => {
    this.setState({crosshairValues: initData.map(d => d[index])});
  };

  setDoor = (e) => {
    this.setState({setDoor: e.target.value, selectedDoor: e.target.value, montyDoor: getMontyDoor(e.target.value,this.state.carDoor)});
  }

  updateSelectedDoor(door) {
    this.setState({selectedDoor: door});
  }

  // execute switching doors if chosen by user
  switchDoor(montyDoor, selectedDoor) {
    this.setState({
      montyDoor: montyDoor,
      selectedDoor: 3-montyDoor-selectedDoor
    })
  }

  updateMontyDoor(door) {
    this.setState({montyDoor: door});
  }

  updateCarDoor(door) {
    this.setState({carDoor: door});
  }

  updateOpenDoors(doors) {
    this.setState({openDoors: doors});
  }

  handleCheck = (e) => {
    this.setState({switch: !this.state.switch});
  }

  handleStep = (e) => {
    this.setState({step: !this.state.step});
  }

  // FOR SERVER
  takeStep = (e) => {
    if (this.state.numSteps > 0) {  // make sure there are more games remaining to be played
      if (this.state.stepState == 0) {
        // door not chosen yet -> show selected door
        this.updateCarDoor(Math.floor(Math.random()*3));
        this.updateSelectedDoor(this.state.setDoor);
        var trueDoorNum = Number(this.state.setDoor)+1; // add 1 bc zero-indexing
        this.setState({stepState: 1, 
          message: `You picked Door ${trueDoorNum}.`});
        /* example JSON output to server
        {
          "mode": "standard",
          "ndoors": 3,
          "door_1": trueDoorNum
        }
        */
      } else if (this.state.stepState == 1) {
        // door selected -> Monty reveals door
        var newMontyDoor = getMontyDoor(this.state.setDoor, this.state.carDoor);
        this.updateMontyDoor(newMontyDoor);
        this.state.openDoors[newMontyDoor] = true;
        this.setState({stepState: 2,
          message: `Monty opened Door ${Number(newMontyDoor)+1}.`});
        /* receive JSON from server
          this.updateMontyDoor(<monty's door from JSON>)
        */
      } else if (this.state.stepState == 2) {
        // Monty opened door -> offer switch
        /* receive JSON from server about switching options
          this.setState({switch: <JSON data>})
        */
        if (this.state.switch) {
          var newDoor = 3-this.state.montyDoor-this.state.selectedDoor;
          this.switchDoor(this.state.montyDoor, this.state.selectedDoor);
          this.updateSelectedDoor(newDoor);
          this.setState({message: `You switched to Door ${Number(newDoor)+1}.`})
          /* send JSON object to server about 2nd choice
            {
              "door_2": newDoor+1
            }
          */
        } else {
          this.setState({message: `You stayed with Door ${Number(this.state.setDoor)+1}.`})
          /* send JSON object to notify about staying
            {
              "door_2": this.state.setDoor+1
            }
          */
        }
        this.setState({stepState: 3});
      } else if (this.state.stepState == 3) {
        // Switch was offered -> reveal doors
        this.updateOpenDoors([true,true,true]);
        var win = Boolean(this.state.selectedDoor == this.state.carDoor);
        if (win) {
          num_wins++;
          this.setState({message: "You won!"})
        } else {
          this.setState({message: "You lost!"})
        }
        /* Receive JSON from server about win/loss
          this.setState({<JSON win/loss info>})
        */
        const point = {x: num_games, y: num_wins/num_games};
        initData[0].push(point);
        num_games++;
        this.setState({data: initData[0]});
        this.setState({stepState: 4});
      } else if (this.state.stepState == 4) {
        // Doors were revealed -> reset game
        this.updateOpenDoors([false,false,false]);
        this.setState({stepState: 0});
        this.setState({numSteps: this.state.numSteps-1});
        this.updateSelectedDoor(-1); // no door selected
        this.setState({message: "Game over.  Play again?"})
      }
    }
  } 

  async updateData(plays) {
    this.setState({numSteps: this.state.numSteps+plays});
    // only enter if step checkbox isn't checked
    if (!this.state.step) {
      var i;
      var sleepTime = 1/plays;
      for (i = 0; i < plays; i++) {
        // const win = Math.floor(Math.random() * 3);
        // door animation
        // open Monty's door
        this.state.openDoors[this.state.montyDoor] = true;
        await sleep(sleepTime);
        // console.log(`pre - selected door: ${this.state.selectedDoor}`);
        // console.log(`pre - monty door: ${this.state.montyDoor}`);
        if (this.state.switch) {
          // switch to remaining door
          // console.log(this.state.selectedDoor);
          // console.log(this.state.montyDoor);
          // this.updateSelectedDoor(3-(this.state.montyDoor+this.state.selectedDoor));
          this.switchDoor(this.state.montyDoor, this.state.selectedDoor);
          await sleep(sleepTime);
        } else {
          this.updateSelectedDoor(this.state.selectedDoor);
          this.updateMontyDoor(this.state.montyDoor);
        }
        // console.log(`selected door: ${this.state.selectedDoor}`);
        // console.log(`car door: ${this.state.carDoor}`);
        // open all doors
        // this.setState({openDoors: [true,true,true]});
        this.updateOpenDoors([true,true,true]);
        await sleep(sleepTime);
        var win = Boolean(this.state.selectedDoor == this.state.carDoor);
        if (win) {
          num_wins++;
        }
        const point = {x: num_games, y: num_wins/num_games};
        initData[0].push(point);
        num_games++;
        this.setState({data: initData[0]});
        // var sleepTime = 1/plays;
        // await sleep(sleepTime);
        // reset doors
        var newCarDoor = Math.floor(Math.random()*3);
        this.updateSelectedDoor(this.state.setDoor);
        this.updateMontyDoor(getMontyDoor(this.state.setDoor,newCarDoor));
        this.updateCarDoor(newCarDoor);
        this.updateOpenDoors([false,false,false]);
        this.setState({numSteps: this.state.numSteps-1});
        // this.setState({selectedDoor: this.state.setDoor, carDoor: newCarDoor, montyDoor: getMontyDoor(this.state.setDoor,newCarDoor),
        //  openDoors: [false,false,false]});
      }
    }
  }

  render() {
    const data = this.state.data;
    const door_items = [];
    for (var i = 0; i < 3; i++) {
      if (this.state.openDoors[i] && this.state.carDoor == i) {
        // open car door
        door_items.push(<img src="../img/door_open_car.png" className="open-door" id={i} />)
      } else if (this.state.openDoors[i] && this.state.carDoor != i) {
        // open goat door
        door_items.push(<img src="../img/open_door_goat.png" className="open-door" id={i} />)
      } else if (this.state.selectedDoor == i) {
        // highlight selected door red
        door_items.push(<img src="../img/selected-door.png" className="door" id={i} />)
      } else {
        // default closed door
        door_items.push(<img src="../img/closed-door.png" className="door" id={i} />)
      }
    }

    return (
      <div className="main1col">
        <XYPlot height={400} width= {600}
          yDomain={[0, 1]}
          onMouseLeave={this._onMouseLeave}>
          <VerticalGridLines />
          <HorizontalGridLines />
          <XAxis title="Number of Games Played"/>
          <YAxis title="Win Percentage"/>
          <LineSeries
            onNearestX={this._onNearestX}
            data={data}
            color="#d6001c"
          />
          <Crosshair
            values={this.state.crosshairValues}
            className={'test-class-name'}
          />
        </XYPlot>
        <div>
          {door_items}
        </div>
        <p>{this.state.message}</p>
        <div onChange={this.setDoor.bind(this)}>
          <input type="radio" value="0" name="door"/> Door 1
          <input type="radio" value="1" name="door"/> Door 2
          <input type="radio" value="2" name="door"/> Door 3
        </div> 
        <div>
          <input 
            type="checkbox" 
            onChange={this.handleCheck} 
            defaultChecked={this.state.switch}/>
          Switch?&nbsp;&nbsp;
          <input 
            type="checkbox" 
            onChange={this.handleStep} 
            defaultChecked={this.state.step}/>
          Step Through Games?&nbsp;&nbsp;
          <div className="navyButton" onClick={() => this.takeStep()}>Step</div>
        </div>
        <div className="blueButton" onClick={() => this.updateData(1)}>run 1 time</div>
        <div className="blueButton" onClick={() => this.updateData(10)}>run 10 times</div>
        <div className="blueButton" onClick={() => this.updateData(100)}>run 100 times</div>
        <div className="blueButton" onClick={() => this.updateData(1000)}>run 1000 times</div>
        <h3>Games left: {this.state.numSteps}</h3>
        <h3>Current win percentage: {Math.round(data[data.length-1].y*100000)/1000}%</h3>
      </div>
    );
  }
}