//
//  ViewController.swift
//  HelloNetworking11
//
//  Created by arjuna sky kok on 24/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        uploadForm()
    }

    func uploadForm() {
        // Server code is in Common/Backend/code/HelloBackend7
        let url = URL(string:"http://localhost:5000/")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        let urlSession = URLSession.shared
        var body = Data()
        body.append("name=john woo&age=23".data(using: .utf8)!)
        let dataTask = urlSession.uploadTask(with: request, from: body) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let string = String(data: data!, encoding: .utf8)
                print(string!)
            }
        }
        dataTask.resume()
    }

}

