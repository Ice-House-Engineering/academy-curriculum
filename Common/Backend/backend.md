# Backend

Install Python.

https://www.python.org/

Create virtual environment.
```
$ python -m venv backend-venv
$ source backend-venv/bin/activate
```

Install Flask.
```
(backend-venv) $ pip install Flask
```

## Basic API

Create a file named hello_backend.py.
```python
from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_backend():
    return "Hello Backend!"
```

To run the application, we can use these commands.
```
(backend-venv) $ export FLASK_APP=hello_backend.py
(backend-venv) $ flask run
 * Serving Flask app "hello_backend.py"
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
```

If we opened the browser with the url pointed to http://localhost:5000, we would get this output.
<p align="center">
<img src="../Assets/Backend-BasicAPI.png">
</p>

Add a new method for displaying html file. Edit hello_backend.py and make it look like this.
```python
from flask import Flask, render_template

app = Flask(__name__)

@app.route("/")
def hello_backend():
    return "Hello Backend!"

@app.route("/get_html_file")
def hello_html():
    return render_template("hello_html.html")
```

Create “templates” directory inside the parent directory of “hello_backend.py”. Create “hello_html.html” inside that “templates” directory.
```html
<!doctype html>
<html>
  <head>
    <title>Hello HTML</title>
  </head>
  <body>
    <p>This is a paragraph</p>
  </body>
</html>
```

Restart the application.

If we opened the browser with the url pointed to http://localhost:5000, we would get this output.
<p align="center">
<img src="../Assets/Backend-BasicAPI2.png">
</p>

## Downloading Image

Create a file named hello_image.py. You can do this in a new directory.
```python
from flask import Flask, send_from_directory

app = Flask(__name__)

@app.route("/image", methods=["GET"])
def serve_image():
    return send_from_directory(".", "tree.jpg")
```

To serve an image we use send_from_directory method. It accepts two parameters. The first one is the directory. “.” means the current directory on which we run the flask application. The second one is the name of the file which we can find in the first parameter directory.

Then we must put “tree.jpg” image in the same directory where we put our hello_image.py file.

If we run the application and we accessed this url: http://localhost:5000/image, we would get our image.
<p align="center">
<img src="../Assets/Backend-DownloadingImage.png">
</p>

## Uploading Image

Create a file named hello_upload.py. You can do this in a new directory.
```python
from flask import Flask, request, redirect, url_for
from pathlib import Path
from werkzeug.utils import secure_filename

app = Flask(__name__)

@app.route("/")
def hello_index():
    return "Hello Index!"

# https://flask.palletsprojects.com/en/1.1.x/patterns/fileuploads/
@app.route("/upload_image", methods=["GET", "POST"])
def upload_image():
    if request.method == "POST":
        if "file" not in request.files:
            return redirect(request.url)
        file = request.files["file"]
        if file.filename == "":
            return redirect(request.url)
        if file:
            filename = secure_filename(file.filename)
            uploaded_dir = Path("/tmp") / Path("uploaded_file")
            if not uploaded_dir.exists():
                uploaded_dir.mkdir()
            file.save(str(uploaded_dir / Path(filename)))
            return redirect(url_for("hello_index"))

    return '''
    <!doctype html>
    <title>Upload new File</title>
    <h1>Upload new File</h1>
    <form method=post enctype=multipart/form-data>
      <input type=file name=file>
      <input type=submit value=Upload>
    </form>
    '''
```

If we opened the url http://localhost:5000/upload_image, we would see the upload form.
<p align="center">
<img src="../Assets/Backend-UploadingImage.png">
</p>

Click “Choose File”. Choose any image. Then click “Upload”. We will get an image saved in “/tmp/uploaded_file” directory.

In the method on handling uploaded file, we can access the file from “request.files” which is a dictionary. The key is the name of the html field representing the uploading file field. In this case, its name is “file”. Then we check the filename of the file. For example: tree.jpg, cat.png. Then we sanitize it with “secure_filename”, in case someone uses “../../../tree.jpg” as a filename. Then we save the file to a directory with “save” method from the file object.

## Serving JSON (API)

Common use of server is as API (Application Programming Interface) server. Instead of serving HTML files, the server serves JSON files or XML files. But the JSON file format is much more popular compared to XML file format. So we only discuss JSON file format.

Create a file named hello_json.py. You can do this in a new directory.
```python
from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_json():
    return {
        "release_date": "2019-09-09",
        "cryptocurrencies": [
          { "name": "bitcoin", "price": 8000 },
          { "name": "ethereum", "price": 145 },
          { "name": "litecoin", "price": 80 }
        ]
    }
```

By default, the Flask application returns dictionary as a JSON string.

If we opened the url http://localhost:5000/, we would see the result page in JSON format.
<p align="center">
<img src="../Assets/Backend-ServeJSON.png">
</p>

## Receiving JSON (API)

We also sometimes need to receive JSON object from the client.

Create a file named hello_receive_json.py. You can do this in a new directory.
```python
from flask import Flask, request

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def hello_json():
    prices = {"bitcoin": 10000, "ethereum": 148, "litecoin": 80}
    if request.method == "POST":
        content = request.json
        return str(prices[content["name"]])
    return "Hello JSON!"
```

If we opened the url http://localhost:5000/, we would see “Hello JSON!” string.

To post the JSON request, we need to use Curl.
```
$ curl --header "Content-Type: application/json" --request POST --data '{"name": "bitcoin"}' http://localhost:5000
```

We will get this result.
```
10000
```

## Redirect

We can redirect from one method to another method with Flask framework.

Create a file named hello_redirect.py. You can do this in a new directory.
```python
from flask import Flask, redirect, url_for

app = Flask(__name__)

@app.route("/")
def hello_index():
    return redirect(url_for("hello_redirect"))

@app.route("/page2")
def hello_redirect():
    return "Hello Redirect!"
```

To redirect from one place to another place, we use “redirect” method. The parameter is the result of “url_for” method which accepts the name of the method we want to redirect to in string format.

If we opened the url http://localhost:5000/, we would be redirected to http://localhost:5000/page2 and get “Hello Redirect!” string.
<p align="center">
<img src="../Assets/Backend-Redirect.png">
</p>

## Uploading Form Data

We can create a form to send data to the server.

Create a file named hello_form.py. You can do this in a new directory.
```python
from flask import Flask, request, redirect, url_for

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def upload_form():
    if request.method == "POST":
        name = request.form["name"].upper()
        age = int(request.form["age"]) * 2
        if name and age:
            return f"{name} is {age} years old"

    return '''
    <!doctype html>
    <title>A Form to Uppercase Name and Double Age</title>
    <h1>A Form to Uppercase Name and Double Age</h1>
    <form method=post>
      <p><span>Name: </span><input type=text name=name></p>
      <p><span>Age: </span><input type=text name=age></p>
      <p><input type=submit value=Send></p>
    </form>
    '''
```

To get the value from the form, we use “form” attribute from the request object. Basically it is a dictionary. The key is the name of the field. The value is the value of that field.

If we opened the url http://localhost:5000, we would get this page.
<p align="center">
<img src="../Assets/Backend-UploadingData.png">
</p>

Fill the “Name: “ field with “john woo” string. Fill the “Age: “ field with “23” string.

Then click “Send” button.

You would get this page.
<p align="center">
<img src="../Assets/Backend-UploadingData2.png">
</p>

## HTTP Basic Authentication

The simplest authentication is HTTP Basic Authentication.

We must install another library to get HTTP Basic Authentication support.

(backend-venv) $ pip install flask-httpauth

Create a file named hello_http_authentication.py. You can do this in a new directory.
```python
# https://flask-httpauth.readthedocs.io/en/latest/
from flask import Flask
from flask_httpauth import HTTPBasicAuth
from werkzeug.security import generate_password_hash, check_password_hash

app = Flask(__name__)
auth = HTTPBasicAuth()

users = {
    "john": generate_password_hash("hello"),
    "susan": generate_password_hash("bye")
}

@auth.verify_password
def verify_password(username, password):
    if username in users:
        return check_password_hash(users.get(username), password)
    return False

@app.route("/")
@auth.login_required
def index():
    return f"Hello, {auth.username()}!"
```

We initialize HTTPBasicAuth object.
```python
auth = HTTPBasicAuth()
```

Then we decorate the function which verifies the username and the password with @auth.verify_password.
```python
@auth.verify_password
def verify_password(username, password):
    if username in users:
        return check_password_hash(users.get(username), password)
    return False
```

Then we can protect the page with @auth.login_required decorator.
```python
@app.route("/")
@auth.login_required
def index():
    return f"Hello, {auth.username()}!"
```

If we ran the application, we would get this screen.
<p align="center">
<img src="../Assets/Backend-HTTPBasic.png">
</p>

Fill the fields with “john” in “Username” field and “hello” in “Password” field. Then press “Sign in” button.

Then we will get this screen, showing our username.
<p align="center">
<img src="../Assets/Backend-HTTPBasic2.png">
</p>

## JWT Authentication

JWT is the short form of JSON Web Token. It is a much more common form of authentication in mobile apps, compared to cookies.
We must install another library to get JWT support.
```
(backend-venv) $ pip install flask-jwt
```

Create a file named hello_jwt.py. You can do this in a new directory.
```python
# https://github.com/mattupstate/flask-jwt/blob/master/example/app.py
from flask import Flask
from flask_jwt import JWT, jwt_required, current_identity
from werkzeug.security import safe_str_cmp
import uuid

class User:
    def __init__(self, id, username, password):
        self.id = id
        self.username = username
        self.password = password

    def __str__(self):
        return f"User(id='{self.id}')"

users = [
    User(1, "john", "password"),
    User(2, "susan", "password"),
]

username_table = {u.username: u for u in users}
userid_table = {u.id: u for u in users}

def authenticate(username, password):
    user = username_table.get(username, None)
    if user and safe_str_cmp(user.password.encode("utf-8"), password.encode("utf-8")):
        return user

def identity(payload):
    user_id = payload["identity"]
    return userid_table.get(user_id, None)

app = Flask(__name__)
app.config["SECRET_KEY"] = str(uuid.uuid4())

jwt = JWT(app, authenticate, identity)

@app.route("/secret")
@jwt_required()
def protected():
    return f"{current_identity}"
```

We create a method to authenticate the user.
```python
def authenticate(username, password):
    user = username_table.get(username, None)
    if user and safe_str_cmp(user.password.encode("utf-8"), password.encode("utf-8")):
        return user
```

We create a method to give the “description” of the token.
```python
def identity(payload):
    user_id = payload["identity"]
    return userid_table.get(user_id, None)
```

To sign the token, we use a secret word.
```python
app = Flask(__name__)
app.config["SECRET_KEY"] = str(uuid.uuid4())
```

Then we initialize JWT object.
```python
jwt = JWT(app, authenticate, identity)
```

To protect a page with JWT, we can use a function decorator from jwt library, which is @jwt_required.
```python
@app.route("/secret")
@jwt_required()
def protected():
    return f"{current_identity}"
```

To authenticate against the server with this JWT authentication, we can access http://localhost:5000/auth.
```
$ curl -H "Content-Type: application/json" --request POST --data '{"username":"john", "password":"password"}' http://localhost:5000/auth

{"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzQ5NTUyODIsImlhdCI6MTU3NDk1NDk4MiwibmJmIjoxNTc0OTU0OTgyLCJpZGVudGl0eSI6MX0.qOg4kYU9g3ssjWi0pl69Z-KZ2By4slpHwkzA737SK2Q"}
```

Then to access the protected page of the server, we can use this token in our header.
```
$ curl -H "Authorization: JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzQ5NTUyODIsImlhdCI6MTU3NDk1NDk4MiwibmJmIjoxNTc0OTU0OTgyLCJpZGVudGl0eSI6MX0.qOg4kYU9g3ssjWi0pl69Z-KZ2By4slpHwkzA737SK2Q" http://localhost:5000/secret

User(id='1')
```

## Query Parameters

We can parse the query parameters coming from clients. For example: http://localhost:5000/download?file=song.mp3

The “file” is a query parameter and its value is “song.mp3”.

Create hello_query.py file.
```python
from flask import Flask, request

app = Flask(__name__)

@app.route("/")
def hello_query():
    name_parameter = request.args.get("name")
    return name_parameter.upper() + "\n"
```

To get the query parameter, we can use “request.args”.

Then run the Flask application.
```
(backend-venv) $ export FLASK_APP=hello_query
(backend-venv) $ flask run
```

Then in other terminal, we can interact with the server with Curl.
```
$ curl http://localhost:5000/?name=james
JAMES
```