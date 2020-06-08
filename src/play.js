import React from "react";
import "./App.css";

class Play extends React.Component {
        handleClickStd = (e) => {
                e.preventDefault();
                this.props.updateDisplayCB("Standard");
		this.props.updateMonty("Standard");
        }

        handleClickHell = (e) => {
                e.preventDefault();
                this.props.updateDisplayCB("Standard");
		this.props.updateMonty("Hell");
        }

        handleClickAngelic = (e) => {
                e.preventDefault();
                this.props.updateDisplayCB("Standard");
		this.props.updateMonty("Angelic");
        }

        handleClickMind = (e) => {
                e.preventDefault();
                this.props.updateDisplayCB("Standard");
		this.props.updateMonty("Mind");
        }

        handleClickRandom = (e) => {
                var modes = ["Standard","Hell","Angelic","Mind"];
                e.preventDefault();
		this.props.updateDisplayCB("Standard");
                this.props.updateMonty(modes[Math.floor(Math.random()*modes.length)]);


        }

        handleClickMystery = (e) => {
                e.preventDefault();
                this.props.updateDisplayCB("Standard");
		this.props.updateMystery(true);
                var modes = ["Standard","Hell","Angelic","Mind"];
                this.props.updateMonty(modes[Math.floor(Math.random()*modes.length)]);
        }


	changeDoors = (e) => {
		console.log(e.target.value)
		const re = /^[0-9\b]+$/;
		if (re.test(e.target.value)) {
			this.props.updateDoors(e.target.value)
		}
		this.props.updateWinningDoor()
	}

        render () {
                return (
                        <div className="main1col">
                        <h1>Let's Play</h1>
			<p> Change number of doors </p>
			<input value={this.props.doors} onChange={this.changeDoors} />
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

