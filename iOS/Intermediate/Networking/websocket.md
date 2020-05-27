# WebSocket

iOS 13 provides a way to connect to a websocket server with webSocketTask method.

## App Transport Security Settings

We need to change the configuration of app everytime when we play with localhost instead of server with domain and HTTPS.

Edit info.plist. Add “App Transport Security Settings” key from “Information Property List” key. Then add “Allows Local Networking” key inside “App Transport Security Settings” key. The value of “Allows Local Networking” key is “YES”.
<p align="center">
<img src="../Assets/Networking-Websocket1.png">
</p>

## webSocketTask

Create a new application. Name it HelloWebSocket1.

Edit ViewController.swift.

Execute a method named “interact” in viewDidLoad method.
```swift
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        interact()
    }
```

Create a method named “interact” inside ViewController class.
```swift
    // https://appspector.com/blog/websockets-in-ios-using-urlsessionwebsockettask
    // Code for backend is in Common/WebSocket/code/HelloWebSocket1
    func interact() {
        let url = URL(string: "ws://localhost:8765")!
        let webSocketSession = URLSession.shared.webSocketTask(with: url)
        
        let message = URLSessionWebSocketTask.Message.string("Hello World")
        webSocketSession.send(message) { error in
            print("Send message with WebSocket")
        }
        
        webSocketSession.receive { result in
            switch result {
            case .failure(let error):
                print("Got error in receiving message: \(error)")
            case .success(let message):
                switch message {
                case .string(let text):
                    print("Got message: \(text)")
                case .data(let data):
                    print("Got data: \(data)")
                @unknown default:
                    print("Fatal error. Got message which is not a string, not a binary.")
                }
            }
        }
        
        webSocketSession.resume()
        sleep(1)
        webSocketSession.cancel(with: .goingAway, reason: "Enough is enough".data(using: .utf8)!)
    }
```

Create a websocket session.
```swift
    let url = URL(string: "ws://localhost:8765")!
    let webSocketSession = URLSession.shared.webSocketTask(with: url)
```

To send a message to a websocket server, we use “send” method. The message must be in Message object.
```swift
    let message = URLSessionWebSocketTask.Message.string("Hello World")
    webSocketSession.send(message) { error in
        print("Send message with WebSocket")
    }
```

To receive a message from a websocket server, we use “receive” method.
```swift
    webSocketSession.receive { result in
        switch result {
        case .failure(let error):
            print("Got error in receiving message: \(error)")
        case .success(let message):
            switch message {
            case .string(let text):
                print("Got message: \(text)")
            case .data(let data):
                print("Got data: \(data)")
            @unknown default:
                print("Fatal error. Got message which is not a string, not a binary.")
            }
        }
    }
```

We can check whether the message is a success or a failure one. If it is a success case, we can filter whether the result is a string type or a data type.

Don’t forget to start this web socket session.
```swift
    webSocketSession.resume()
```

To close the web socket session, we can execute its “cancel” method.
```swift
        webSocketSession.cancel(with: .goingAway, reason: "Enough is enough".data(using: .utf8)!)
```

We need to run the server first in Common/WebSocket/code/HelloWebSocket1.
```
(virtual-environment) $ cd Common/WebSocket/code/HelloWebSocket1
(virtual-environment) $ python hello_websocket.py
```

If we ran the application, we would get this output.
```
Send message with WebSocket
Got message: Hello WorldHello World
```

The server would get this output.
```
Hello World
```