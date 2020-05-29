//
//  ViewController.swift
//  HelloWebSocket1
//
//  Created by arjuna sky kok on 13/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        interact()
    }

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

}

