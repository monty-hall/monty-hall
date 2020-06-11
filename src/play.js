import React from "react";
import "./App.css";

class Play extends React.Component {
        changeDisplayAndMonty = (m) => {
	       this.props.updateMonty(m);
		this.props.newSession();
		this.props.updateWinningDoor();
		this.props.updateDisplayCB("Standard");
	}

        handleClickStd = (e) => {
                e.preventDefault();
		this.changeDisplayAndMonty("Standard");
        }

        handleClickVar = (e) => {
                e.preventDefault();
                var modes = ["Standard","Hell","Angelic","Mind"];
		var monty = modes[Math.floor(Math.random()*modes.length)];
                this.changeDisplayAndMonty(monty);
        }

	changeDoors = (e) => {
		console.log(e.target.value)
		const re = /^[0-9\b]+$/;
		if (re.test(e.target.value)) {
			this.props.updateDoors(parseInt(e.target.value));
		}
		this.props.updateWinningDoor();
	}

        render () {
                return (
                        <div className="main1col">
                        <h1>Let's Play</h1>
			<div className="inlineDiv">
                                <input id="numDoors" value={this.props.doors} onChange={this.changeDoors} />
                                <label for="numDoors">Number of Doors</label>
                        </div>
                        <h2>Select your game mode</h2>
                        <div className="buttons">
                        <div className="rowButton" id="skyBlue" onClick={this.handleClickStd}>
                        <span>Standard Monty</span>
                        </div>
                        <div className="rowButton" id="navy" onClick={this.handleClickVar}>
                        <span>Variant Monty</span>
                        </div>
                        </div>
                        </div>
                );
        }
}

export default Play;

