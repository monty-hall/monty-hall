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

  handleClickMystery = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Mystery");
  }

  render () {
    return (
      <div className="main1col">
        <h1>Let's Play</h1>
        <p>Select your game mode</p>
        <div class="buttons">
          <div class="rowButton" id="lightBlue" onClick={this.handleClickStd}>
            <span>Standard Monty</span>
          </div>
          <div class="rowButton" id="red" onClick={this.handleClickHell}>
            <span>Hell Monty</span>
          </div>
          <div class="rowButton" id="skyBlue" onClick={this.handleClickRandom}>
            <span>Random Monty</span>
          </div>
          <div class="rowButton" id="navy" onClick={this.handleClickMystery}>
            <span>Mystery Monty</span>
          </div>
          <div class="rowButton" >
            <span>??? Monty</span>
          </div>
          <div class="rowButton" >
            <span>??? Monty</span>
          </div>
          <div class="rowButton" >
            <span>??? Monty</span>
          </div>
          <div class="rowButton" >
            <span>??? Monty</span>
          </div>
          <div class="rowButton" >
            <span>??? Monty</span>
          </div>
        </div>
      </div>
    );
  }
}

export default Play;