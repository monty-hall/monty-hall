import React from "react";
import "./App.css";

class MindAnswer extends React.Component {
  render () {
    return (
    	<div>	
      		<div className="main1col">
        		<h1> Answer to Mind-Reading Monty </h1>
      		</div>
      		<div className="main2col-weighted">
          <div className="wide-col">
            <h3> Do you mind, Monty? </h3>
            <p> That's right, Monty Hall has the ability to read minds.  He also wants to save the
            show some money because too many people have figured out the optimal strategy for his
            game.  <b>Mind-Reading Monty wants you to lose.</b>  So how does he do it?</p>
            <p> This version of Monty doesn't actually read your mind.  He instead just uses your past 
            decisions to predict your behavior.  He takes a guess at the strategy that you will use and 
            tries to trick you into choosing a door with a goat.</p>
          </div>
          <div className="narrow-col">
            <figure>
              <img className="inline-img-small" 
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRhEfdQLxV1ajseSRyktMJfYv4svINFj9qhiZBaZHbqxqApKY4O&usqp=CAU" 
              alt="monty" />
            </figure>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <h3>So what should you do?</h3>
            <p> Try something different.  If you know that you switch often, try sticking with your
            original door.  Try to read Mind-Reading Monty's mind.  That's really the best advice we 
            can give you.</p>
          </div>
        </div>
      </div>

    );
  }
}

export default MindAnswer;