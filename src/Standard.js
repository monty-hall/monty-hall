/* interface for playing the game */

import "./App.css";
import React from 'react';

class StandardPlay extends React.Component {
  handleClick = (e) => {
    var clicked = parseInt(e.target.id);
    this.props.updateDoor(clicked);
  }

  render () {
    const door_items = []
    var i;
    for (i=0; i<this.props.doors; i++){
      /* use door_1 and monty_door if not null to style those doors*/
      if (this.props.door_1 != null && this.props.door_1 === i){
        // mark selected door
        door_items.push(<img src="../img/selected-door.png" className="door" id={i} onClick={this.handleClick} />)
      } else if (this.props.monty_door != null && this.props.monty_door === i){
        // reveal goat(s) - after user makes first selection
        door_items.push(<img src="../img/open_door_goat.png" className="open-door" id={i} onClick={this.handleClick} />)
      } else {
        // default closed door
        door_items.push(<img src="../img/closed-door.png" className="door" id={i} onClick={this.handleClick} />)
      }
    }

    var greeting;
    if (this.props.mystery === false) {
      greeting = <h1> {this.props.monty} Monty </h1>
    } else {
      greeting = <h1> Monty Hall Game </h1>
    }
      
    return (
      <div className="main1col">
        {greeting}
        <p> Choose a door! </p>
        <div>
        {door_items}
        </div>
      </div>
    )
  }

}

export default StandardPlay;
