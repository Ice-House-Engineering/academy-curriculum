# Code is from https://flask-restful.readthedocs.io/en/latest/quickstart.html
from flask import Flask
from flask_restful import reqparse, abort, Api, Resource

app = Flask(__name__)
api = Api(app)

CRYPTOCURRENCIES = {
    "BITCOIN": {"id": "0", "name": "BITCOIN", "title": "bitcoin.org"},
    "ETHEREUM": {"id": "1", "name": "ETHEREUM", "title": "ethereum.org"},
    "BITCOIN CASH": {"id": "2", "name": "BITCOIN CASH", "title": "bitcoin.com"},
    "LITECOIN": {"id": "3", "name": "LITECOIN", "title": "litecoin.org"}
}

def abort_if_cryptocurrency_doesnt_exist(cryptocurrency_id):
    if cryptocurrency_id not in CRYPTOCURRENCIES:
        abort(404, message="Cryptocurrency {} doesn't exist".format(cryptocurrency_id))

parser = reqparse.RequestParser()
parser.add_argument("name")
parser.add_argument("title")

class Cryptocurrency(Resource):

    def get(self, cryptocurrency_id):
        abort_if_cryptocurrency_doesnt_exist(cryptocurrency_id)
        return CRYPTOCURRENCIES[cryptocurrency_id]

class CryptocurrenciesList(Resource):

    def get(self):
        return CRYPTOCURRENCIES

    def post(self):
        args = parser.parse_args()
        new_id = len(CRYPTOCURRENCIES)
        CRYPTOCURRENCIES[args["name"]] = {"name": args["name"], "title": args["title"]}
        return CRYPTOCURRENCIES[args["name"]], 201

api.add_resource(CryptocurrenciesList, "/cryptocurrencies")
api.add_resource(Cryptocurrency, "/cryptocurrencies/<cryptocurrency_id>")

if __name__ == "__main__":
    app.run()
