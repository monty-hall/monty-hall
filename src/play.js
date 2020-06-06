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

  handleClickAngelic = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Angelic");
  }

  handleClickMind = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Mind");
  }

  handleClickRandom = (e) => {
    var modes = ["Standard","Hell","Angelic","Mind"];
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
        <h2>Select your game mode</h2>
        <div className="buttons">
          <div className="rowButton" id="lightBlue" onClick={this.handleClickStd}>
            <span>Standard Monty</span>
          </div>
          <div className="rowButton" id="red" onClick={this.handleClickHell}>
            <span>Hell Monty</span>
          </div>
          <div className="rowButton" id="gold" onClick={this.handleClickAngelic}>
            <span>Angelic Monty</span>
          </div>
          <div className="rowButton" id="violet" onClick={this.handleClickMind}>
            <span>Mind-Reading Monty</span>
          </div>
          <div className="rowButton" id="skyBlue" onClick={this.handleClickRandom}>
            <span>Random Monty</span>
          </div>
          <div className="rowButton" id="navy" onClick={this.handleClickMystery}>
            <span>Mystery Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
          <div className="rowButton" >
            <span>??? Monty</span>
          </div>
        </div>
      </div>
    );
  }
}

export default Play;