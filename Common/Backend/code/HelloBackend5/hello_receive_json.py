from flask import Flask, request


app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def hello_json():
    prices = {"bitcoin": 10000, "ethereum": 148, "litecoin": 80}
    if request.method == "POST":
        content = request.json
        return str(prices[content["name"]])
    return "Hello JSON!"
