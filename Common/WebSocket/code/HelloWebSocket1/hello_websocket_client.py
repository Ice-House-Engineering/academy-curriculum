import asyncio
import websockets

# https://websockets.readthedocs.io/en/stable/
async def hello(uri):
    async with websockets.connect(uri) as websocket:
        await websocket.send("Hello World!")
        result = await websocket.recv()
        print(result)

asyncio.get_event_loop().run_until_complete(hello("ws://localhost:8765"))
