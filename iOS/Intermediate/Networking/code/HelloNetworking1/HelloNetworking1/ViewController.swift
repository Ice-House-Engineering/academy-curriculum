//
//  ViewController.swift
//  HelloNetworking1
//
//  Created by arjuna sky kok on 30/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        download()
        getData()
        getHtmlData()
        getNotFound()
    }
    
    func getData() {
        let url = URL(string:"http://localhost:5000")!
        let urlSession = URLSession.shared
        let dataTask = urlSession.dataTask(with: url) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse {
                let string = String(decoding: data!, as: UTF8.self)
                print("=== dataTask ===")
                print(string)
                print(httpResponse.statusCode)
            }
        }
        dataTask.resume()
    }

    func download() {
        // Server code is in Common/Backend/code/HelloBackend1
        let url = URL(string:"http://localhost:5000")!
        let urlSession = URLSession.shared
        let downloadTask = urlSession.downloadTask(with: url) { location, response, error in
            if let url = location, let string = try? String(contentsOf: url), let httpResponse = response as? HTTPURLResponse {
                print("=== downloadTask ===")
                print(string)
                print(httpResponse.statusCode)
            }
        }
        downloadTask.resume()
    }
    
    func getHtmlData() {
        let url = URL(string:"http://localhost:5000/get_html_file")!
        let urlSession = URLSession.shared
        let dataTask = urlSession.dataTask(with: url) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, let mimeType = httpResponse.mimeType {
                let string = String(decoding: data!, as: UTF8.self)
                print("=== mimeType ===")
                print(string)
                print(mimeType)
                print(mimeType=="text/html")
                print(httpResponse.statusCode)
            }
        }
        dataTask.resume()
    }
    
    func getNotFound() {
        let url = URL(string:"http://localhost:5000/get_not_found")!
        let urlSession = URLSession.shared
        let dataTask = urlSession.dataTask(with: url) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse {
                print("=== statusCode ===")
                if httpResponse.statusCode == 404 {
                    print(httpResponse.statusCode)
                }
            }
        }
        dataTask.resume()
    }

}

