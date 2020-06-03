// page for selecting game mode

import React from "react";
import "./styles.css";

class Play extends React.Component {
  handleClickStd = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Standard");
  }

  handleClickHell = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Hell");
  }

  handleClickRandom = (e) => {
    var modes = ["Standard","Hell"];
    e.preventDefault();
    this.props.updateDisplayCB(modes[Math.floor(Math.random()*modes.length)]);
  }

  render () {
    return (
      <div className="main">
        <h1>Let's Play</h1>
        <p>Select your game mode</p>
        <div>
          <button onClick={this.handleClickStd}>Standard</button>
          <button onClick={this.handleClickHell}>Hell</button>
          <button onClick={this.handleClickRandom}>Random</button>
        </div>
      </div>
    );
  }
}

export default Play;