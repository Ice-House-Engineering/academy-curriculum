from flask import Flask, request, redirect, url_for


app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def upload_form():
    if request.method == "POST":
        name = request.form["name"].upper()
        age = int(request.form["age"]) * 2
        if name and age:
            return f"{name} is {age} years old"

    return '''
    <!doctype html>
    <title>A Form to Uppercase Name and Double Age</title>
    <h1>A Form to Uppercase Name and Double Age</h1>
    <form method=post>
      <p><span>Name: </span><input type=text name=name></p>
      <p><span>Age: </span><input type=text name=age></p>
      <p><input type=submit value=Send></p>
    </form>
    '''
