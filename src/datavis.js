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

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

export default class Datavis extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      data: generateData(),
      crosshairValues: []
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

  async updateData(plays) {
    var i;
    for (i = 0; i < plays; i++) {
      const win = Math.floor(Math.random() * 3);
      if (win > 0) {
        num_wins++;
      }
      const point = {x: num_games, y: num_wins/num_games};
      initData[0].push(point);
      num_games++;
      this.setState({data: initData[0]});
      var sleepTime = 1/plays;
      await sleep(sleepTime);
    }
  }

  render() {
    const data = this.state.data;
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
        <button onClick={() => this.updateData(1)}>run 1 time</button>
        <button onClick={() => this.updateData(10)}>run 10 times</button>
        <button onClick={() => this.updateData(100)}>run 100 times</button>
        <button onClick={() => this.updateData(1000)}>run 1000 times</button>
        <p>Current win percentage: {Math.round(data[data.length-1].y*100000)/1000}%</p>
      </div>
    );
  }
}