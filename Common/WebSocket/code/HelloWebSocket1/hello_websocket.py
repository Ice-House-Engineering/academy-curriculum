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
