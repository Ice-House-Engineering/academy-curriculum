# REST API

We can create REST API manually with Flask. But there is a library to make REST API easier. The library is called flask-restful.

Create a virtual environment. Then install flask library.
```
$ python3.8 -m venv virtual-environment
$ source virtual-environment/bin/activate
(virtual-environment) $ pip install flask
```
Install flask-restful library.
```
(virtual-environment) $ pip install flask-restful
```

## Resource

REST is based on the concept of resource. We can get, update, create and delete the resource.

Create a file named hello_api.py
```python
# Code is from https://flask-restful.readthedocs.io/en/latest/quickstart.html
from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class HelloWorld(Resource):
    def get(self):
        return {"hello": "world"}

api.add_resource(HelloWorld, "/")

if __name__ == "__main__":
    app.run()
```

We create an API from Flask application with “Api” class.
```python
app = Flask(__name__)
api = Api(app)
```

We create a class that inherits “Resource” class. Inside the class, we can add methods as representatives of GET, PUT, DELETE, etc.
```python
class HelloWorld(Resource):
    def get(self):
        return {"hello": "world"}
```

Then we can pinpoint this class to our routing end point.
```python
api.add_resource(HelloWorld, "/")
```

We can run the application.
```
(virtual-environment) $ python hello_api.py
```
Then in another terminal we can interact with the REST API server with curl.
```
$ curl http://localhost:5000
{"hello": "world"}
```
Then going back to the server terminal, we can see the output from the flask application.
```
127.0.0.1 - - [24/Dec/2019 21:42:10] "GET / HTTP/1.1" 200 -
```
This is a GET response of REST API.

## List, Create, Delete, Update, Read

Let’s create a more complete REST API with list, create, delete, update, read response.

Create a new file named hello_routing.py.

Add import lines.
```python
# Code is from https://flask-restful.readthedocs.io/en/latest/quickstart.html
from flask import Flask
from flask_restful import reqparse, abort, Api, Resource
```

Create an API from the Flask application.
```python
app = Flask(__name__)
api = Api(app)
```
Create a dictionary to hold the data. This is like a dummy database.
```python
TODOS = {
    "todo1": {"task": "build an API"},
    "todo2": {"task": "????"},
    "todo3": {"task": "profit"},
}
```
Create a helper function to give 404 response if we can not find the resource.
```python
def abort_if_todo_doesnt_exist(todo_id):
    if todo_id not in TODOS:
        abort(404, message="Todo {} doesn't exist".format(todo_id))
```

Create a parser to parse the data that we would get.
```python
parser = reqparse.RequestParser()
parser.add_argument("task")
```
Create a resource class.
```python
class Todo(Resource):

    def get(self, todo_id):
        abort_if_todo_doesnt_exist(todo_id)
        return TODOS[todo_id]

    def delete(self, todo_id):
        abort_if_todo_doesnt_exist(todo_id)
        del TODOS[todo_id]
        return "", 204

    def put(self, todo_id):
        args = parser.parse_args()
        task = {"task": args["task"]}
        TODOS[todo_id] = task
        return task, 201
```

We can read (get method), delete (delete method), and update (put method) this resource.

To create a resource, we need to do it in higher level, in another resource.
```python
class TodoList(Resource):

    def get(self):
        return TODOS

    def post(self):
        args = parser.parse_args()
        todo_id = int(max(TODOS.keys()).lstrip("todo")) + 1
        todo_id = f"todo{todo_id}"
        TODOS[todo_id] = {"task": args["task"]}
        return TODOS[todo_id], 201
```

We can read (get method) the list of resources and create (post method) the resource.

We have two resources and we pinpoint them to two endpoints.
```python
api.add_resource(TodoList, "/todos")
api.add_resource(Todo, "/todos/<todo_id>")
```

Then add a way to run this script.
```python
if __name__ == "__main__":
    app.run()
```

Run the script.
```
(virtual-environment) $ python hello_routing.py
```
Then in the other terminal, we can interact with these REST API resources.

We can read the list of todos.
```
$ curl http://localhost:5000/todos
{"todo1": {"task": "build an API"}, "todo2": {"task": "????"}, "todo3": {"task": "profit"}}
```
The server would print this list response output.
```
127.0.0.1 - - [24/Dec/2019 22:33:47] "GET /todos HTTP/1.1" 200 -
```
We can read a todo resource.
```
$ curl http://localhost:5000/todos/todo1
{"task": "build an API"}
```
The server would print this success read response output.
```
127.0.0.1 - - [24/Dec/2019 22:46:19] "GET /todos/todo1 HTTP/1.1" 200 -
```
We can create a todo.
```
$ curl http://localhost:5000/todos -d "task=something new" -X POST
{"task": "something new"}
```
The server would print the success created response.
```
127.0.0.1 - - [24/Dec/2019 22:36:13] "POST /todos HTTP/1.1" 201 -
```
We can update a todo resource.
```
$ curl http://localhost:5000/todos/todo2 -d "task=something updated" -X PUT
{"task": "something updated"}
```
The server would print the success updated response.
```
127.0.0.1 - - [24/Dec/2019 22:40:29] "PUT /todos/todo2 HTTP/1.1" 201 -
```
We can delete a todo resource.
```
$ curl http://localhost:5000/todos/todo2 -X DELETE
```
The server would print this success deleted response.
```
127.0.0.1 - - [24/Dec/2019 22:50:10] "DELETE /todos/todo2 HTTP/1.1" 204 -
```