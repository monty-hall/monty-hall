import React from "react";
import ReactDOM from "react-dom";
import "./styles.css";

import Home from "./home";
import Play from "./play";
import StandardPlay from "./standardPlay";

function updateState(text) {
  this.setState({text});
}

// sidebar navigation
class Menu extends React.Component {
  handleClickHome = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Home")
  }

  handleClickPlay = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Play");
  }

  render() {
    return (
      <div className="menu">
        <h2 id="menuTitle">Menu</h2>
        <a href ='#' onClick={this.handleClickHome}>Home</a>
        <a href='#' onClick={this.handleClickPlay}>Play</a>
        <a href='#'>About</a>
      </div>
    );
  }
}

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      text: "Home"
    };
    this.updateDisplay = this.updateDisplay;
  }

  updateDisplay = (text) => {this.setState({text})}

  render() {
    let display;
    if (this.state.text == "Play") {
      display = <Play updateDisplayCB={this.updateDisplay}/>;
    } else if (this.state.text == "Standard") {
      display = <StandardPlay />;
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
