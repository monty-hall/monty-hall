/* this page has the answers to "Standard Monty" */

import React from "react";
import "./styles.css";

class StandardAnswer extends React.Component {
  render () {
    return (
      <div>
        <div className="main1col">
          <h1> Answer to Standard Monty </h1>
        </div>
        <div className="main2col-weighted">
          <div className="wide-col">
            <h2> Subtitle </h2>
            <p> abcdef ghijkl mnopqrs tu vw xyz a b cde fghij klmnop qrs tuvwx yz
              abcdefghijkl mnopqrstuvwxyz abcd efghij klmn op qrs tu vwxyz
              abcd efghijklmnop qrstuvwxyz abcdefg hijklmnopqr stuv wxyz
              abcdefghi jklmnopqrs tuvwxyz ab cdefghi jklmnopqrstuvwxyz
              abcdefghi jklmn opqr stuv w xyz abcdefgh ijklmnopqrstuvwxyz </p>
            <p> abcdefghi jklmnopqrst uvwxyz a bcdefg hij klmnop qrstuvwxyz
              abcde ghijklmnop qrstuvwxyz abcd efghijklmnopqr stuv wxyz
              abcdefghijklmnopqrstuvwxyz abc defg hijklmno pqrst uvwx yz
              abcdefghij klmnopqrstuvwxyz ab cdefg hi jklmn opqrst uvwxyz
              abcd efghijkl mnopqrstuvwxyz abcdefghijklmn opqrstuvwxyz
              ab cdef hijklmnop qr stuvwxyz abc defghijk lmnopqrst uvwxyz 
              abc defghijk lmnopqrst uvwxyz abcdefg hijklmnopq rstuvw xyz</p>
          </div>
          <div className="narrow-col">
            <img src="https://upload.wikimedia.org/wikipedia/commons/f/f7/Binary_tree.svg" alt="tree"></img>
          </div>
        </div>
        <div className="main1col">
          <div className="text">
            <p> abcdef ghijkl mnopqrs tu vw xyz a b cde fghij klmnop qrs tuvwx yz
              abcdefghijkl mnopqrstuvwxyz abcd efghij klmn op qrs tu vwxyz
              abcd efghijklmnop qrstuvwxyz abcdefg hijklmnopqr stuv wxyz
              abcdefghij klmnopqrstuvwxyz ab cdefg hi jklmn opqrst uvwxyz
              abcd efghijkl mnopqrstuvwxyz abcdefghijklmn opqrstuvwxyz </p>
          </div>
        </div>
      </div>
    );
  }
}

export default StandardAnswer;