//
//  ViewController.swift
//  HelloNetworking7
//
//  Created by arjuna sky kok on 08/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDownloadDelegate {
    
    private lazy var urlSession: URLSession = {
        let configuration = URLSessionConfiguration.default
        configuration.waitsForConnectivity = true
        return URLSession(configuration: configuration,
                          delegate: self, delegateQueue: nil)
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        downloadData()
    }
    
    func downloadData() {
        // Server code is in Common/Backend/code/HelloBackend6
        let url = URL(string:"http://localhost:5000/")!
        let dataTask = urlSession.downloadTask(with: url)
        dataTask.resume()
    }
    
    func urlSession(_ session: URLSession, downloadTask: URLSessionDownloadTask, didFinishDownloadingTo location: URL) {
        if let string = try? String(contentsOf: location) {
            print(string)
        }
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, willPerformHTTPRedirection response: HTTPURLResponse, newRequest request: URLRequest, completionHandler: @escaping (URLRequest?) -> Void) {
        print(response.statusCode)
        completionHandler(request)
    }

}

