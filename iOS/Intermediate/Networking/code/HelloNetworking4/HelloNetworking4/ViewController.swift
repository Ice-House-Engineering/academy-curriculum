//
//  ViewController.swift
//  HelloNetworking4
//
//  Created by arjuna sky kok on 06/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        getJson()
    }

    func getJson() {
        // Server code is in Common/Backend/code/HelloBackend4
        let url = URL(string:"http://localhost:5000")!
        let urlSession = URLSession.shared
        let dataTask = urlSession.dataTask(with: url) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let jsonDecoder = JSONDecoder()
                let cryptocurrencyjson = try! jsonDecoder.decode(CryptocurrencyContainerJson.self, from: data!)
                print(cryptocurrencyjson)
                print(cryptocurrencyjson.cryptocurrencies[0].name)
            }
        }
        dataTask.resume()
    }

}

struct CryptocurrencyContainerJson : Decodable {
    let release_date : String
    let cryptocurrencies : [CryptocurrencyJson]
}

struct CryptocurrencyJson : Decodable {
    let name : String
    let price : Int
}
