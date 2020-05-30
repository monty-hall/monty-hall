// page for playing standard version of game

import React from "react";
import "./styles.css";

export default function StandardPlay() {
  return (
    <div className="main">
      <h1>Standard Monty</h1>
      <p>Choose 1 of the 3 doors</p>
      <div>
        <button>Door 1</button>
        <button>Door 2</button>
        <button>Door 3</button>
      </div>
    </div>
  );
}