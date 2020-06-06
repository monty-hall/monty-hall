import React from "react";
import ReactDOM from "react-dom";
import "./styles.css";

import Home from "./home";
import Play from "./play";
import StandardPlay from "./standardPlay";
import HellPlay from "./hellPlay";
import AngelicPlay from "./angelicPlay";
import MindPlay from "./mindPlay";
import MysteryPlay from "./mysteryPlay";
import Answers from "./answers";
import Warning from "./warning";
import StdAnswer from "./standardAnswer";
import HellAnswer from "./hellAnswer";

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
      text: "Home",   // display screen
      mode: "Standard"  // warning screen mode
    };
    this.updateDisplay = this.updateDisplay;
  }

  updateDisplay = (text) => {this.setState({text})}
  updateMode = (mode) => {this.setState({mode})}
  getMode = () => {return this.state.mode}

  // determines which screen takes up main display
  render() {
    let display;
    if (this.state.text === "Play") {
      display = <Play updateDisplayCB={this.updateDisplay}/>;
    } else if (this.state.text === "Standard") {
      display = <StandardPlay />;
    } else if (this.state.text === "Hell") {
      display = <HellPlay />;
    } else if (this.state.text === "Angelic") {
      display = <AngelicPlay />;
    } else if (this.state.text === "Mind") {
      display = <MindPlay />;
    } else if (this.state.text === "Mystery") {
      display = <MysteryPlay />;
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

ReactDOM.render(
  <App />,
  document.getElementById("root")
);
