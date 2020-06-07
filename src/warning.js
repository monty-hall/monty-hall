import React from "react";
import "./App.css";

class Warning extends React.Component {

  handleClickYes = (e) => {
    e.preventDefault();
    let mode = this.props.getModeCB();
    if (mode === "Standard") {
      this.props.updateDisplayCB("StandardAnswer");
    } else if (mode === "Hell") {
      this.props.updateDisplayCB("HellAnswer");
    } else {
      this.props.updateDisplayCB("Answers");
    }
  }

  handleClickNo = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Answers");
  }

  render() {
    return (
      <div className="main1col">
        <h1 className="center">Warning</h1>
        <h2 className="center">You are about to view the answers 
        to: {this.props.getModeCB()} Monty</h2>
        <h2 className="center">Are you sure you want to continue?</h2>
        <div className="yesNo">
          <div className="yesButton" onClick={this.handleClickYes}>
            <span>Yes</span>
          </div>
          <div className="noButton" onClick={this.handleClickNo}>
            <span>No</span>
          </div>
        </div>
      </div>
    );
  }
}

export default Warning;

