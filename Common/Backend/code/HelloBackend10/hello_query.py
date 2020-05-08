from flask import Flask, request


app = Flask(__name__)

@app.route("/")
def hello_query():
    name_parameter = request.args.get("name")
    return name_parameter.upper() + "\n"
