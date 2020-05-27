# Web Socket

Web socket is different compared to normal HTTP. Web socket is a 2 ways communication between a server and a client. The client does not have to contact the server everytime to check whether the client has received the response from the server or not. The server can push the response to the client as if the client is the server.

Install websockets library.
```
(virtual-environment) $ pip install websockets
```

## Websocket Server

Create a file, named hello_websocket.py.
```python
import asyncio
import websockets

# https://websockets.readthedocs.io/en/stable/
async def echo(websocket, path):
    async for message in websocket:
        print(message)
        await websocket.send(message * 2)

asyncio.get_event_loop().run_until_complete(
       websockets.serve(echo, "localhost", 8765))
asyncio.get_event_loop().run_forever()
```

We create a callback named “echo” to receive the message, print the message, then send the message which has been modified (we double the message) to the client.
```python
async def echo(websocket, path):
    async for message in websocket:
        print(message)
        await websocket.send(message * 2)
```

Then we run the websocket server.
```python
asyncio.get_event_loop().run_until_complete(
       websockets.serve(echo, "localhost", 8765))
asyncio.get_event_loop().run_forever()
```

To run the server, we can execute it as a normal Python script.
```
(virtual-environment) $ python hello_websocket.py
```

## Client

We need to create a client script to interact with the websocket server. Name it hello_websocket_client.py.
```python
import asyncio
import websockets

# https://websockets.readthedocs.io/en/stable/
async def hello(uri):
    async with websockets.connect(uri) as websocket:
        await websocket.send("Hello World!")
        result = await websocket.recv()
        print(result)

asyncio.get_event_loop().run_until_complete(hello("ws://localhost:8765"))
```

Open a new terminal then run this script in the virtual environment which has websockets library installed.
```
(virtual-environment) $ python hello_websocket_client.py
Hello World!Hello World!
```
If we went back to the terminal on which we run the websocket server, we would get this output.
```
(virtual-environment) $ python hello_websocket.py
Hello World!
```