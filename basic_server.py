from flask import Flask, request, Response, jsonify, g
from flask_cors import CORS, cross_origin
import mysql.connector
import os
import random

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": "*"}})
app.config['CORS_HEADERS'] = 'Content-Type'

mydb = None

def getdb():
    db = getattr(g, "mydb", None)
    if db is None:
        g.mydb = mysql.connector.connect(
          host="localhost",
          user="bram",
          password="montyhall"
        )

@app.teardown_request
def close_db(exception):
    db = getattr(g, "mydb", None)
    if db is not  None:
        db.close()




@app.route("/new_session", methods=["GET"])
@cross_origin(supports_credentials=True)
def new_session():
    getdb()
    session = 0
    cursor = g.mydb.cursor()
    cursor.execute("SELECT MAX(session) FROM mydb.table1")
    res = cursor.fetchone()
    print(res)
    cursor.close()
    if res != None and res != (None,):
        session = res[0] + 1
    return jsonify({"session_id":session})


@app.route("/game", methods=["POST"])
@cross_origin(supports_credentials=True)
def game():
    req_data = request.get_json()
    monty_type = req_data['monty']
    number_doors = req_data['doors']
    winning_door = req_data['winning_door']
    chosen_door = req_data['door_1']
    monty_door = None
    print(monty_type)
    print(number_doors)
    print(winning_door)
    print(chosen_door)
    #LOGIC FOR CHOOSING MONTY DOOR GOES HERE
    if number_doors != None:
        monty_door = random.choice([x for x in range(number_doors) if x != chosen_door])

    return jsonify({"monty_door": monty_door})

@app.route("/end", methods=["POST"])
@cross_origin(supports_credentials=True)
def game_over():
    getdb()
    req_data = request.get_json()
    session_id = req_data['sess']
    monty_type = req_data['monty']
    number_doors = req_data['doors']
    winning_door = req_data['winning_door']
    chosen_door_1 = req_data['door_1']
    monty_door = req_data['m_door']
    chosen_door_2 = req_data['door_2']
    cur = g.mydb.cursor()
    sql = """
    INSERT INTO mydb.montyhall
    VALUES (%s, CURDATE(), %s, %s, %s, %s, %s, %s);
    """
    params = {
        "session": session_id,
        "type": monty_type,
        "doors": number_doors,
        "winning": winning_door,
        "door1": chosen_door_1,
        "revealed": monty_door,
        "door2": chosen_door_2
    }
    cur.execute(sql, params=[v for k,v in params.items()])
    cur.close()
    return jsonify(True)

    

    
if __name__ == "__main__":
    app.run(debug=True)
