import React from "react";
import "./App.css";
import {XYPlot, 
      LineSeries, 
      VerticalGridLines,
      HorizontalGridLines,
      XAxis,
      YAxis} from 'react-vis';

const initData = [
  {x: 0, y: 0}
];

let num_games = 1;
let num_wins = 0;

function generateData() {
  return initData;
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

class Datavis extends React.Component {

  constructor() {
    super();
    this.state = {data: generateData()};
  }

  async updateData(plays) {
    var i;
    for (i = 0; i < plays; i++) {
      const win = Math.floor(Math.random() * 3);
      if (win > 0) {
        num_wins++;
      }
      const point = {x: num_games, y: num_wins/num_games};
      initData.push(point);
      num_games++;
      this.setState({data: initData});
      var sleepTime = 1/plays;
      await sleep(sleepTime);
    }
  }

  render() {
    const data = this.state.data;
    return (
      <div className="App">
        <XYPlot height={300} width= {300}
          yDomain={[0, 1]}>
          <VerticalGridLines />
          <HorizontalGridLines />
          <XAxis />
          <YAxis />
          <LineSeries data={data} />
        </XYPlot>
        {/* <form action={() => this.updateData()}>
          <label for="fname"># runs:</label>
          <input type="text" id="fname" name="fname" />
          <input type="submit" value="Run" />
        </form> */}
        <button onClick={() => this.updateData(1)}>run 1 time</button>
        <button onClick={() => this.updateData(10)}>run 10 times</button>
        <button onClick={() => this.updateData(100)}>run 100 times</button>
        <button onClick={() => this.updateData(1000)}>run 1000 times</button>
      </div>
    );
  }
}

export default Datavis;