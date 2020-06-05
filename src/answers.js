// page for answers

import React from "react";
import "./styles.css";

export default function Answers() {
  return (
    <div>
      <div className="main1col">
        <h1>Answers</h1>
        <h2>From the game show "Let's Make a Deal"</h2>
      </div>
      <div className="main2col-weighted">
        <div class= "narrow-col">
          <p> <b>What is the Monty Hall Problem?</b> abcdefghijklmnopqrstuvwxyz
            abcdefghijkl mnopqrs tuvwxyz abcdefghi jkl mnopqr st uvwxy z 
            abc def ghijklmn opqrs tuvwxy z abcd efghij klmnop qrstuvwxyz </p>
        </div>
        <div class= "wide-col">
          <p> <b>What is the Monty Hall Problem?</b> abcdefghijklmnopqrstuvwxyz
            abcdefghijkl mnopqrs tuvwxyz abcdefghi jkl mnopqr st uvwxy z 
            abc def ghijklmn opqrs tuvwxy z abcd efghij klmnop qrstuvwxyz </p>
        </div>
      </div>
    </div>
  );
}