import "./App.css";
import React from 'react';


class Result extends React.Component {
	handleReset = (e) => {
		this.props.resetDoors();
	}

	handleFinish = (e) => {
		this.props.toPlayScreen();
	}

	render () {
		var result;
		if (this.props.winningDoor === this.props.door_2){
			result = "YOU HAVE WON!!!!";
		} else {
			result = "You lost.";
		}
		return (
			<div>
			<h1> {result} </h1>
			<h3> Play again? </h3>
			<button onClick={this.handleReset}> Play Again </button>
			<button onClick={this.handleFinish}> {this.props.t} </button>
			</div>
		)
	}
}

export default Result;
