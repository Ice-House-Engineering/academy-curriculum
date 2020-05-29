from flask import Flask, send_from_directory


app = Flask(__name__)

@app.route("/image", methods=["GET"])
def serve_image():
    return send_from_directory(".", "tree.jpg")
