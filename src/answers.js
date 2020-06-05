// page for answers

import React from "react";
import "./styles.css";

class Answers extends React.Component {

  handleClickStd = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Warning");
    this.props.updateModeCB("Standard");
  }

  handleClickHell = (e) => {
    e.preventDefault();
    this.props.updateDisplayCB("Warning");
    this.props.updateModeCB("Hell");
  }
  
  render() {
    return (
      <div>
        <div className="main1col">
          <h1>Answers</h1>
        </div>
        <div className="main2col-weighted">
          <div className= "narrow-col">
            <h3> Here is a list of explanations to each of the game modes: </h3>
            <div className="buttons">
              <div className="rowButton" id="lightBlue" onClick={this.handleClickStd}>
                <span>Standard Monty</span>
              </div>
              <div className="rowButton" id="red" onClick={this.handleClickHell}>
                <span>Hell Monty</span>
              </div>
              <div className="rowButton" id="gold">
                <span>Angelic Monty</span>
              </div>
              <div className="rowButton" id="violet">
                <span>Mind-Reading Monty</span>
              </div>
            </div>  
          </div>
          <div className= "wide-col">

            <h3> Answers?  What do you mean by answers? </h3>
            <p> Here we have provided explanations of the optimal strategy 
              in each of the Monty modes.  Try to come up with your own strategy first
              before looking at our explanations!  We want to see how much these
              answers influence your decisions.   </p>

            <h3> Some historical background on "answers" </h3>
            <p> What if I told you that the "answer" to the original Monty Hall problem 
              was published in a newspaper, but people still didn't believe the answer? 
              Back in the 1980s, <i>Parade Magazine</i> had an excerpt about the Monty
              Hall problem in their "Ask Marilyn" column.  In this piece, Marilyn vos
              Savant, who held the title of "world's smartest woman," provided an 
              explanation to the correct strategy for this problem.  She then received
              quite a bit of public backlash as many people, including Ph.Ds, about how
              she was wrong (even though she was right!).  Although we may not be the 
              world's smartest people, let's see if you'll believe our advice.</p>
          </div>
        </div>
      </div>
    );
  }
}

export default Answers;