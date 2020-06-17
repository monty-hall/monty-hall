import React from "react";
import "./App.css";

class HellAnswer extends React.Component {
  render () {
    return (
    	<div>	
      		<div className="main1col">
        		<h1> Answer to Hell Monty </h1>
      		</div>
      		<div className="main2col-weighted">
          <div className="wide-col">
            <h3> What the hell Monty? </h3>
            <p> Who the hell is Hell Monty?  Well, Hell Monty is from hell.  <b>Hell Monty 
            wants you to lose.</b>  So how does he do it?</p>
            <p> This version of Monty only offers the option to switch if you choose the door 
            with the car.  So if you know that the version of Monty that you're dealing with 
            is the Monty from Hell and he allows you to switch, then you better not switch.  </p>
          </div>
          <div className="narrow-col">
            <figure>
              <img className="inline-img-small" 
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRr9tj53uD0-S9bi_kFtGqujPaf39w97KeA7X7kvfpy_chVCCz-&usqp=CAU" 
              alt="monty" />
            </figure>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <h3>So what should you do?</h3>
            <p> If you can switch, don't.  Then you'll have a 100% win rate in this scenario.  
            If you can't switch, well you're stuck with your original option.  Knowing this, your
            original choice determines everything about the outcome of the game.  Thus, you have a
            1/3 chance of winning no matter which door you choose, assuming that you do not switch 
            if given the option.</p>
          </div>
        </div>
      </div>

    );
  }
}

export default HellAnswer;

