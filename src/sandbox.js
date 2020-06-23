import React from "react";
import "./App.css";

class Sandbox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      options: false,
      autoPlay: false,
      auto_count: 10,
    };
  }

  changeDisplay = (m) => {
    if (this.state.autoPlay) {
      this.props.autoPlay(this.state.auto_count);
    } else {
      this.props.updateWinningDoor();
      this.props.updateDisplayCB("Standard");
    }
  };

  handleClickStd = (e) => {
    e.preventDefault();
    this.props.updateMonty("Standard");
  };

  handleClickHell = (e) => {
    e.preventDefault();
    this.props.updateMonty("Hell");
  };

  handleClickAngelic = (e) => {
    e.preventDefault();
    this.props.updateMonty("Angelic");
  };

  handleClickMind = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Standard");
    this.props.updateMonty("Mind");
  };

  handleClickRandom = (e) => {
    var modes = ["Standard", "Hell", "Angelic", "Mind"];
    e.preventDefault();
    var monty = modes[Math.floor(Math.random() * modes.length)];
    this.props.updateMonty(monty);
  };

  handleClickMystery = (e) => {
    e.preventDefault();
    this.props.updateMystery(true);
    var modes = ["Standard", "Hell", "Angelic", "Mind"];
    var monty = modes[Math.floor(Math.random() * modes.length)];
    this.props.updateMonty(monty);
  };

  changeDoors = (e) => {
    console.log(e.target.value);
    const re = /^[0-9\b]+$/;
    if (re.test(e.target.value)) {
      this.props.updateDoors(parseInt(e.target.value));
    }
    this.props.updateWinningDoor();
  };

  render() {
    var options_button;
    if (this.state.options) {
      options_button = (
        <button onClick={() => this.setState({ options: false })}>
          {" "}
          LESS OPTIONS{" "}
        </button>
      );
    } else {
      options_button = (
        <button onClick={() => this.setState({ options: true })}>
          {" "}
          MORE OPTIONS{" "}
        </button>
      );
    }
    return (
      <div className="main1col">
        <h1>Sandbox Mode</h1>
        <button onClick={this.changeDisplay}> PLAY</button>
        <div className="inlineDiv">
          <input
            id="numDoors"
            value={this.props.doors}
            onChange={this.changeDoors}
          />
          <label for="numDoors">Number of Doors</label>
        </div>
        <h2>Select your game mode</h2>
        <div className="buttons">
          <div
            className="rowButton"
            id="lightBlue"
            onClick={this.handleClickStd}
          >
            <span>Standard Monty</span>
          </div>
          <div className="rowButton" id="red" onClick={this.handleClickHell}>
            <span>Hell Monty</span>
          </div>
          <div
            className="rowButton"
            id="gold"
            onClick={this.handleClickAngelic}
          >
            <span>Angelic Monty</span>
          </div>
          <div className="rowButton" id="violet" onClick={this.handleClickMind}>
            <span>Mind-Reading Monty</span>
          </div>
          <div
            className="rowButton"
            id="skyBlue"
            onClick={this.handleClickRandom}
          >
            <span>Random Monty</span>
          </div>
          <div
            className="rowButton"
            id="navy"
            onClick={this.handleClickMystery}
          >
            <span>Mystery Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
          <div className="rowButton">
            <span>??? Monty</span>
          </div>
        </div>
        {options_button}
        {this.state.options && (
          <div>
            <input
              name="autoPlay"
              type="checkbox"
              checked={this.state.autoPlay}
              onChange={(e) => this.setState({ autoPlay: e.target.checked })}
            />
            <span> Number of automatic games </span>
            <input
              value={this.state.auto_count}
              onChange={(event) =>
                this.setState({
                  auto_count: event.target.value.replace(/\D/, ""),
                })
              }
            />
          </div>
        )}
      </div>
    );
  }
}

export default Sandbox;
