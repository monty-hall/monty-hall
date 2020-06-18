/* Answer to Standard Monty variant */

import React from "react";
import "./App.css";

class StandardAnswer extends React.Component {
  render () {
    return (
      <div>
        <div className="main1col">
          <h1> Answer to Standard Monty </h1>
        </div>
        <div className="main2col-weighted">
          <div className="wide-col">
            <h3> It's a 50/50 chance either way, right?  Wrong. </h3>
            <p> Intuitively one would think that given the choice of 2 doors left that
            there's a 50% chance of the car being behind the original door chosen.  Combine 
            that with the "stick with your gut" philosophy, and you will see that many people 
            choose to stay with their original door.  It turns out that <b>switching is the 
            optimal strategy!</b></p>
            <p> So you may be asking why this is.  Basically, the idea is that Monty knows 
            exactly where the car is, so he has to purposely avoid the car when revealing 
            the goat.  When you first pick a door, you have a 1/3 chance of picking the door 
            with the car.  This leaves a 2/3 chance of the car being behind one of the other
            two doors.  Thus when Monty knowingly reveals one of the goats, there is a 2/3
            chance of the car being behind the other door left.  Still not convinced?  We have 
            some math prepared for you.</p>
          </div>
          <div className="narrow-col">
            <figure>
              <img className="inline-img-small" 
              src="https://si.wsj.net/public/resources/images/BN-VL303_hall10_8S_20171005133236.jpg" 
              alt="monty" />
              <figcaption>Monty Hall from <i>Let's Make a Deal</i></figcaption>
            </figure>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <h3>It's all about probability!</h3>
            <p> Below is a probability tree that outlines every possible outcome of the game, assuming that
            you choose Door 1.  In the standard game, the car is randomly placed 
            behind one of the three doors, meaning that there is an equal chance that the car is behind 
            each of the doors.  Now, Monty must choose a door that does not have the car behind it.  If the
            car is not behind Door 1, then Monty only has one option for which door he can open (which means the other
              door must have a car behind it!)  Otherwise, he then has a 1/2 chance of opening either door.  So there's 
            a 2/3 chance you are in the scenario where the other remaining door has the car behind it.  From the table
            below, you can simply sum the total probabilities of the scenarios and see that switching gives you a car 
            2/3 of the time.</p>
            <img className="inline-img-large" 
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Monty_tree_door1.svg/525px-Monty_tree_door1.svg.png" 
            alt="tree"/>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <h3>Still not convinced?</h3>
            <p> If you're still not convinced, you can try out Sandbox Mode and keep track of your 
            results.  A <a href="https://en.wikipedia.org/wiki/Monte_Carlo_method">Monte Carlo simulation</a> in 
            Sandbox Mode may finally convince you <i>(*wink*)</i>.</p>
          </div>
        </div>
      </div>
    );
  }
}

export default StandardAnswer;

