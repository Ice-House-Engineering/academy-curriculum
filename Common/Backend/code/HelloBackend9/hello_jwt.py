# https://github.com/mattupstate/flask-jwt/blob/master/example/app.py
from flask import Flask
from flask_jwt import JWT, jwt_required, current_identity
from werkzeug.security import safe_str_cmp
import uuid


class User:
    def __init__(self, id, username, password):
        self.id = id
        self.username = username
        self.password = password

    def __str__(self):
        return f"User(id='{self.id}')"

users = [
    User(1, "john", "password"),
    User(2, "susan", "password"),
]

username_table = {u.username: u for u in users}
userid_table = {u.id: u for u in users}

def authenticate(username, password):
    user = username_table.get(username, None)
    if user and safe_str_cmp(user.password.encode("utf-8"), password.encode("utf-8")):
        return user

def identity(payload):
    user_id = payload["identity"]
    return userid_table.get(user_id, None)

app = Flask(__name__)
app.config["SECRET_KEY"] = str(uuid.uuid4())

jwt = JWT(app, authenticate, identity)

@app.route("/secret")
@jwt_required()
def protected():
    return f"{current_identity}"
