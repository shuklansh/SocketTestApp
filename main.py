import socketio
import eventlet
import eventlet.wsgi
from flask_socketio import join_room, leave_room, send, SocketIO
from flask import Flask, render_template


app = Flask(__name__)
sio = SocketIO(app)

counter = 0

@sio.on('counterincr') 
def message():
    global counter
    counter = counter + 1
    sio.emit("value",counter)
    print("counter value is now : ", counter)

if __name__ == '__main__':
    sio.run(app,debug = True)