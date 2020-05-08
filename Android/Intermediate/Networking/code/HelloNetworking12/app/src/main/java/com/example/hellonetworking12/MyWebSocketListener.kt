package com.example.hellonetworking12

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class MyWebSocketListener : WebSocketListener() {

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        Log.d(LOG, text)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

        webSocket.send("Hello Jakarta")
    }
}