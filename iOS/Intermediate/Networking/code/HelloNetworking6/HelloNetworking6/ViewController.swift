//
//  ViewController.swift
//  HelloNetworking6
//
//  Created by arjuna sky kok on 07/11/19.
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
        
        getData()
    }
    
    func getData() {
        // Server code is in Common/Backend/code/HelloBackend1
        let url = URL(string:"http://localhost:5000/")!
        let dataTask = urlSession.dataTask(with: url)
        dataTask.resume()
    }

    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive data: Data) {
        downloadedData.append(data)
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, didCompleteWithError error: Error?) {
        if error == nil {
            let string = String(data: downloadedData, encoding: .utf8)!
            print(string)
        }
    }
    
    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive response: URLResponse, completionHandler: @escaping (URLSession.ResponseDisposition) -> Void) {
        let httpResponse = response as! HTTPURLResponse
        print(httpResponse.statusCode)
        completionHandler(.allow)
    }

}

