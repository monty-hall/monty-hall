// homepage for website

import React from "react";
import "./styles.css";

export default function Home() {
  return (
    <div className="main">
      <h1>The Monty Hall Problem</h1>
      <h2>From the game show "Let's Make a Deal"</h2>
      <div className="image-section">
        <img src='../img/cardoorgoat.JPG' alt="cardoorgoat" id="cardoorgoat"/>
      </div>
      <p> <b>What is the Monty Hall Problem?</b> abcdefghijklmnopqrstuvwxyz
         abcdefghijkl mnopqrs tuvwxyz abcdefghi jkl mnopqr st uvwxy z 
         abc def ghijklmn opqrs tuvwxy z abcd efghij klmnop qrstuvwxyz </p>
    </div>
  );
}
