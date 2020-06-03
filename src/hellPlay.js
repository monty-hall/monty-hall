// page for playing "Hell Monty" version of game

import React from "react";
import "./styles.css";

export default function HellPlay() {
  return (
    <div className="main">
      <h1>Hell Monty</h1>
      <p>Choose 1 of the 3 doors</p>
      <div>
        <button>Door 1</button>
        <button>Door 2</button>
        <button>Door 3</button>
      </div>
    </div>
  );
}