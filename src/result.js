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
		return (
			<div>
			<button onClick={this.handleReset}> Play Again </button>
			<button onClick={this.handleFinish}> Play a new gaim </button>
			</div>
		)
	}
}

export default Result;
