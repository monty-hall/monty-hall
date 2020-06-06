// page for playing "Mystery Monty"
// random version but user doesn't know which version

import React from "react";
import "./styles.css";

export default function MysteryPlay() {
  return (
    <div className="main">
      <h1>Mystery Monty</h1>
      <p>Choose 1 of the 3 doors</p>
      <div>
        <button>Door 1</button>
        <button>Door 2</button>
        <button>Door 3</button>
      </div>
    </div>
  );
}