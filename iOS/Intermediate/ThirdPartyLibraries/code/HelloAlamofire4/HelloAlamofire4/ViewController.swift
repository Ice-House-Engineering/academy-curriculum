//
//  ViewController.swift
//  HelloAlamofire4
//
//  Created by arjuna sky kok on 21/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        getJson()
    }

    func getJson() {
        AF.request("http://localhost:5000").responseJSON { response in
            if let cryptocurrencyDict = response.value as? NSDictionary {
                print(cryptocurrencyDict["release_date"]!)
                let cryptocurrencies = cryptocurrencyDict["cryptocurrencies"]! as! NSArray
                let cryptocurrency = cryptocurrencies[0] as! NSDictionary
                print(cryptocurrency["name"]!)
                print(cryptocurrency["price"]!)
            }
        }
    }

}
