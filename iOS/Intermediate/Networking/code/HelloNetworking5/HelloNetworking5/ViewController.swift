//
//  ViewController.swift
//  HelloNetworking5
//
//  Created by arjuna sky kok on 07/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        uploadJson()
    }
    
    func uploadJson() {
        // Server code is in Common/Backend/code/HelloBackend5
        let url = URL(string:"http://localhost:5000/")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let urlSession = URLSession.shared
        let cryptocurrencyPriceJson = CryptocurrencyPriceJson(name: "bitcoin")
        let jsonData = try! JSONEncoder().encode(cryptocurrencyPriceJson)
        let dataTask = urlSession.uploadTask(with: request, from: jsonData) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let string = String(data: data!, encoding: .utf8)
                print(string!)
            }
        }
        dataTask.resume()
    }

}

struct CryptocurrencyPriceJson: Codable {
    let name: String
}
