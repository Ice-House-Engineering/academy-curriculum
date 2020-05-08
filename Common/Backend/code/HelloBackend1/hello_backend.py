from flask import Flask, render_template


app = Flask(__name__)

@app.route("/")
def hello_backend():
    return "Hello Backend!"

@app.route("/get_html_file")
def hello_html():
    return render_template("hello_html.html")
