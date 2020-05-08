//
//  ViewController.swift
//  HelloNetworking12
//
//  Created by arjuna sky kok on 29/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDataDelegate {
    
    private lazy var urlSession: URLSession = {
        let configuration = URLSessionConfiguration.default
        configuration.waitsForConnectivity = true
        return URLSession(configuration: configuration,
                          delegate: self, delegateQueue: nil)
    }()
    
    var downloadedData = Data()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        loginWebsite()
    }

    func loginWebsite() {
        // Server code is in Common/Backend/code/HelloBackend8
        let url = URL(string:"http://localhost:5000/")!
        let dataTask = urlSession.dataTask(with: url)
        dataTask.resume()
    }
    
    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive data: Data) {
        downloadedData.append(data)
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, didCompleteWithError error: Error?) {
        if error == nil {
            let httpResponse = task.response as! HTTPURLResponse
            print(httpResponse.statusCode)
            let string = String(data: downloadedData, encoding: .utf8)!
            print(string)
        }
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, didReceive challenge: URLAuthenticationChallenge, completionHandler: @escaping (URLSession.AuthChallengeDisposition, URLCredential?) -> Void) {
        let authMethod = challenge.protectionSpace.authenticationMethod
        guard authMethod == NSURLAuthenticationMethodHTTPBasic else {
            completionHandler(.performDefaultHandling, nil)
            return
        }

        let credential = URLCredential(user: "john", password: "hello", persistence: .forSession)
        completionHandler(.useCredential, credential)
    }

}

