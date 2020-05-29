from flask import Flask, redirect, url_for


app = Flask(__name__)

@app.route("/")
def hello_index():
    return redirect(url_for("hello_redirect"))

@app.route("/page2")
def hello_redirect():
    return "Hello Redirect!"
