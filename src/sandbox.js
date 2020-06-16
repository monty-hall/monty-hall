import React from "react";
import "./App.css";

class Sandbox extends React.Component {
	changeDisplayAndMonty = (m) => {
		this.props.updateMonty(m);
		this.props.updateWinningDoor();
		this.props.updateDisplayCB("Standard");
	}

	handleClickStd = (e) => {
		e.preventDefault();
		this.changeDisplayAndMonty("Standard");
	}

    handleClickHell = (e) => {
    	e.preventDefault();
		this.changeDisplayAndMonty("Hell");
    }

    handleClickAngelic = (e) => {
    	e.preventDefault();
		this.changeDisplayAndMonty("Angelic");
	}

    handleClickMind = (e) => {
    	e.preventDefault();
        this.props.updateDisplayCB("Standard");
		this.changeDisplayAndMonty("Mind");
	}

    handleClickRandom = (e) => {
    	var modes = ["Standard","Hell","Angelic","Mind"];
        e.preventDefault();
        var monty = modes[Math.floor(Math.random()*modes.length)];
		this.changeDisplayAndMonty(monty);
	}

    handleClickMystery = (e) => {
    	e.preventDefault();
		this.props.updateMystery(true);
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

	render() {
		return(
			<div className="main1col">
                <h1>Sandbox Mode</h1>
                <div className="inlineDiv">
					<input id="numDoors" value={this.props.doors} onChange={this.changeDoors} />
					<label for="numDoors">Number of Doors</label>
				</div>
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

export default Sandbox;