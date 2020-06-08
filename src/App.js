import React from 'react';
import Play from "./play";
import StandardPlay from "./Standard.js";
import Home from "./Home.js";
import Answers from "./Answers.js";
import Warning from "./Warning.js";
import StdAnswer from "./StandardAnswer.js";
import HellAnswer from "./HellAnswer.js";




import './App.css';


// top navbar
class Menu extends React.Component {
	handleClickHome = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Home")
	}

	handleClickPlay = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Play");
	}

	handleClickAnswers = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Answers");
	}

	render() {
		return (
			<div id="menu" ref="menu">
			{/* <a id="menuTitle"><img src="../img/logo.png" id="logo"/></a> */}
			<img src="../img/logo.png" id="logo" onClick={this.handleClickHome}/>
			<a href ='#' onClick={this.handleClickHome}>Home</a>
			<a href='#' onClick={this.handleClickAnswers}>Answers</a>
			<a href='#'>About</a>
			<a href='#'>Instructions</a>
			<a href='#' onClick={this.handleClickPlay}>Play</a>
			</div>
		);
	}
}



class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			text: "Play",   // display screen
			mode: "Standard",  // warning screen mode
			doors: 3,
			winning_door: null,
			door_1: null,
			m_door: null,
			door_2: null,
			mystery: false
		};
		this.updateDisplay = (txt) => {this.setState({text:txt})}
		this.updateMode = (mde) => {this.setState({mode:mde})}
		this.updateDoors = (d) => {this.setState({doors:d})}
		this.updateDoor1 = (d) => {
			this.setState({door_1:d});
			/*do api call asyncronously using axios -> update m_door*/
		}
		this.updateMontyDoor = (d) => {this.setState({m_door:d})}
		this.updateDoor2 = (d) => {this.setState({door_2:d})}
		this.getMode = () => {return this.state.mode}
		this.updateMystery = (b) => {this.setState({mystery:b})}
	}



	// determines which screen takes up main display
	render() {
		var updateDoor;
		if (this.state.door_1 == null){
			updateDoor = this.updateDoor1 
		} else{
			updateDoor = this.updateDoor2
		}
		let display;
		if (this.state.text == "Home"){
			display = <Home />
		} else if (this.state.text === "Play") {
			display = <Play updateDisplayCB={this.updateDisplay} updateMystery={this.updateMystery} 
			updateMonty={this.updateMode} updateDoors={this.updateDoors} />;
		} else if (this.state.text === "Standard") {
			display = <StandardPlay monty={this.state.mode} doors={this.state.doors} updateDoor={updateDoor}
			door_1={this.state.door_1} monty_door={this.state.m_door} mystery={this.state.mystery}/>;
		} else if (this.state.text == "Standard" && this.state.door_1 != null && this.state.door_2 != null) {
			/* display winning / loosing screen + restart
			*/
		} else if (this.state.text === "Answers") {
			display = <Answers updateDisplayCB={this.updateDisplay}
			updateModeCB={this.updateMode}/>;
		} else if (this.state.text === "Warning") {
			display = <Warning updateDisplayCB={this.updateDisplay} 
			getModeCB={this.getMode}/>
		} else if (this.state.text === "StandardAnswer") {
			display = <StdAnswer />;
		} else if (this.state.text === "HellAnswer") {
			display = <HellAnswer />
		} else {
			display = <Home />;
		}

		return (
			<div>
			<Menu updateDisplayCB={this.updateDisplay}/>,
			{display}
			</div>
		);
	}
}


export default App;
