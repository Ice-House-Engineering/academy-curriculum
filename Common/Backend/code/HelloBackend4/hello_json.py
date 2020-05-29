from flask import Flask


app = Flask(__name__)

@app.route("/")
def hello_json():
    return {
        "release_date": "2019-09-09",
        "cryptocurrencies": [
          { "name": "bitcoin", "price": 8000 },
          { "name": "ethereum", "price": 145 },
          { "name": "litecoin", "price": 80 }
        ]
    }
