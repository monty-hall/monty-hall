// page for selecting game mode

import React from "react";
import "./styles.css";

class Play extends React.Component {
  handleClick = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Standard");
  }

  render () {
    return (
      <div className="main">
        <h1>Let's Play</h1>
        <p>Select your game mode</p>
        <div>
          <button onClick={this.handleClick}>Standard</button>
        </div>
      </div>
    );
  }
}

export default Play;