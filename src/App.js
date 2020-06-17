import React from 'react';
import Play from "./play";
import StandardPlay from "./Standard.js";
import Home from "./home.js";
import Result from "./result.js";
import Answers from "./answers.js";
import Warning from "./warning.js";
import StdAnswer from "./standardAnswer.js";
import HellAnswer from "./hellAnswer.js";
import AngelicAnswer from "./angelicAnswer.js";
import MindAnswer from "./mindAnswer.js";
import Sandbox from "./sandbox.js";



import './App.css';
const axios = require("axios");
let axiosConfig = {
  headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      "Access-Control-Allow-Origin": "*",
  }
};


// top navbar
class Menu extends React.Component {
	handleClickHome = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Home")
	}

	handleClickPlay = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Standard");
	}

	handleClickAnswers = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Answers");
	}

	handleClickSandbox = (e) => {
		e.preventDefault();
		this.props.updateDisplayCB("Sandbox");
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
			{this.props.sandbox &&
				<a href='#' onClick={this.handleClickSandbox}>Sandbox</a>
			}
			<a href='#' onClick={this.handleClickPlay}>Play</a>
			</div>
		);
	}
}



class App extends React.Component {
	/*
	 * TODO: move constructor functions to class functions
	 * make session creation add cookie
	 * check for cookie and if it does not exist put in default mode with random monty
	 */
	constructor(props) {
		super(props);
		this.state = {
			session: null, // int unique to each user. Saved to local storge 
			sandboxAvailable: false, // Has the user played the preliminary round untill finished?
			text: "Home",   // display screen
			mode: "Standard",  // what monty are we using?
			doors: 3,
			winning_door: null, 
			door_1: null,	// first door selected
			m_door: null,	// door Monty reveals
			door_2: null,	// second door selected (on switch)
			mystery: true // Do we tell the user what monty they are using
		};
	}
	logOff = () => {
		// call backend to ask for statistics
		// display statistics
		// resets state for next user
		this.setState({
			session: null, 
			sandbox_available: false,
			text: "Stats",
			mode: "Standard",
			doors: 3,
			winning_door: null, 
			door_1: null,	
			m_door: null,	
			door_2: null,	
			mystery: true 
		});
	}
	getSession = () => {
		console.log("get session called");
	};
	updateMontyDoorAPI = async function(d) {
		axios.post("http://127.0.0.1:5000/game", {
			monty: this.state.mode,
			doors: this.state.doors,
			winning_door: this.state.winning_door,
			door_1: d
		} , axiosConfig)
		.then((response) => {
			this.setState({m_door: response.data.monty_door});
		} ,(error) => {console.log(error);}
		);
	};
	addToDb = (d) => {
		axios.post("http://127.0.0.1:5000/end", {
			sess: this.state.session,
			monty: this.state.mode,
			doors: this.state.doors,
			winning_door: this.state.winning_door,
			door_1: this.state.door_1,
			m_door: this.state.m_door,
			door_2: d
		});
	};
	updateDisplay = (txt) => {this.setState({text:txt})};
	updateMode = (mde) => {this.setState({mode:mde})};
	updateDoors = (d) => {this.setState({doors:d})};
	updateDoor1 = (d) => {
		this.setState({door_1:d});
		this.updateMontyDoorAPI(d);
		/*do api call asyncronously using axios -> update m_door*/
	};
	updateMontyDoor = (d) => {this.setState({m_door:d})}
	updateDoor2 = (d) => {
		this.setState({door_2:d});
		this.addToDb(d);
	};
	getMode = () => {return this.state.mode};
	updateMystery = (b) => {this.setState({mystery:b})};
	updateWinner = (d) => {
		this.setState({
			winning_door: Math.floor(Math.random() * this.state.doors),
			door_1: null,
			m_door: null,
			door_2: null
		})
	};
	resetDoors = () => {this.setState({door_1: null, door_2: null, m_door: null, winning_door: null})};
	toSandboxMode = () => {
		this.setState({
			text: "Sandbox", 
			monty: null,
			door_1: null,
			door_2: null,
			m_door: null,
			winning_door: null,
			sandboxAvailable:true
		})
		window.localStorage.setItem('user', JSON.stringify({session: this.state.session, sandboxAvailable: true}));
	};
	toSandboxScreen = () => {
		this.setState({
			text: "Sandbox", 
			monty: null,
			door_1: null,
			door_2: null,
			m_door: null,
			winning_door: null,
			sandboxAvailable:true
		})
	};



	// determines which screen takes up main display
	render() {
		var updateDoor;
		// IF session id is null, check local storage. If both are null, create new session
		if (this.state.session == null) {
			const res = window.localStorage.getItem('user');
			if (res == null){
				axios.get("http://127.0.0.1:5000/new_session")
				.then((response) => {
					window.localStorage.setItem('user', JSON.stringify({
						session: response.data.session_id, sandboxAvailable: false
					}));
					this.setState({session:response.data.session_id});
					} ,(error) => {console.log(error);}
				);
			} else {
				const stored = JSON.parse(res);
				if (stored == null || stored.session == null) {
					axios.get("http://127.0.0.1:5000/new_session")
					.then((response) => {
						window.localStorage.setItem('user', JSON.stringify({
							session: response.data.session_id, sandboxAvailable: false
						}));
						this.setState({session:response.data.session_id});
						} ,(error) => {console.log(error);}
					);
					} else {
						this.setState({session: stored.session, sandboxAvailable: stored.sandboxAvailable});
					}
			}
		}
		if ((this.state.sandboxAvailable === false || this.state.text === "Standard") && this.state.mode == null){
			var modes = ["Standard","Hell","Angelic","Mind"];
			var monty = modes[Math.floor(Math.random()*modes.length)];
			this.setState({mode:monty});
		}
		if (this.state.door_1 == null){
			updateDoor = this.updateDoor1 
		} else{
			updateDoor = this.updateDoor2
		}
		let display;
		if (this.state.sandboxAvailable === false && this.state.door_1 != null && this.state.door_2 != null){
			display = <Result selected={this.state.door_2} correct={this.state.winning_door}
			resetDoors={this.resetDoors} toPlayScreen={this.toSandboxMode} t={"Finish and play sandbox mode"}/>
		} else if (this.state.sandboxAvailable === false){
			display = <StandardPlay monty={this.state.mode} doors={this.state.doors} updateDoor={updateDoor}
			door_1={this.state.door_1} monty_door={this.state.m_door} mystery={this.state.mystery}/>;
		} else if (this.state.text === "Play") {
			display = <Play updateDisplayCB={this.updateDisplay} updateMystery={this.updateMystery} 
			updateMonty={this.updateMode} updateDoors={this.updateDoors} updateWinningDoor={this.updateWinner}/>;
		} else if (this.state.text === "Sandbox") {
			display = <Sandbox updateDisplayCB={this.updateDisplay} updateMystery={this.updateMystery} 
			updateMonty={this.updateMode} updateDoors={this.updateDoors} updateWinningDoor={this.updateWinner}/>;
		} else if (this.state.text === "Standard" && this.state.door_1 != null && this.state.door_2 != null) {
			// display winning / loosing screen + restart
			display = <Result selected={this.state.door_2} correct={this.state.winning_door} 
			resetDoors={this.resetDoors} toPlayScreen={this.toSandboxScreen} t={"New Game"}/>
		} else if (this.state.text === "Standard") {
			display = <StandardPlay monty={this.state.mode} doors={this.state.doors} updateDoor={updateDoor}
			door_1={this.state.door_1} monty_door={this.state.m_door} mystery={this.state.mystery}/>;
		}	else if (this.state.text === "Answers") {
			display = <Answers updateDisplayCB={this.updateDisplay}
			updateModeCB={this.updateMode}/>;
		} else if (this.state.text === "Warning") {
			display = <Warning updateDisplayCB={this.updateDisplay} 
			getModeCB={this.getMode}/>
		} else if (this.state.text === "StandardAnswer") {
			display = <StdAnswer />;
		} else if (this.state.text === "HellAnswer") {
			display = <HellAnswer />;
		} else if (this.state.text === "AngelicAnswer") {
			display = <AngelicAnswer />;
	 	} else if (this.state.text === "MindAnswer") {
	 		display = <MindAnswer />;
	 	} else if (this.state.text === "Stats") {
			// DISPLAY STATISTICS
			display = null;
		} else if (this.state.text === "Home") {
			display = <Home />;
		} else {
			display = <Home />;
		}

		return (
			<div>
			<Menu updateDisplayCB={this.updateDisplay} sandbox={this.state.sandboxAvailable}/>,
			{display}
			</div>
		);
	}
}


export default App;
