import React from "react";
import "./App.css";

class AngelicAnswer extends React.Component {
  render () {
    return (
    	<div>	
      		<div className="main1col">
        		<h1> Answer to Angelic Monty </h1>
      		</div>
      		<div className="main2col-weighted">
          <div className="wide-col">
            <h3> A good-hearted Monty </h3>
            <p> Game show heaven has decided to let Monty the Angel come down to earth
            to help <i>Let's Make a Deal</i> get better ratings 
            than <i>The Price is Right</i>.  <b>Angelic Monty wants you to win.</b>  We don't know why
            he wants you to win, but we do know how he tries to help.</p>
            <p> Angelic Monty gives you the option to switch only if you choose a door with a goat 
            behind it.  If you pick the door with the car, then he does not give you the option to switch,
            and you win the car.  </p>
          </div>
          <div className="narrow-col">
            <figure>
              <img className="inline-img-small" 
              src="https://image.freepik.com/free-vector/angel-emoji-with-blue-halo-overhead_1319-827.jpg" 
              alt="monty" />
            </figure>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <h3>So what should you do?</h3>
            <p> If you can switch, do it.  If you can't switch, you win.  So you actually have a
            100% chance of winning if you switch when given the opportunity.  Of course, this is 
            only helpful if you know that you're dealing with Monty the Angel (but unfortunately 
            he can't reveal that he's secretly an angel.)</p>
          </div>
        </div>
      </div>

    );
  }
}

export default AngelicAnswer;