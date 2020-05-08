//
//  ViewController.swift
//  HelloAlamofire8
//
//  Created by arjuna sky kok on 31/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        login()
    }

    func login() {
        let parameters = ["username": "john", "password": "password"]
        // The server code is in Common/Backend/code/HelloBackend9
        AF.request("http://localhost:5000/auth", method: .post, parameters: parameters, encoder: JSONParameterEncoder.default).responseJSON { response in
            if let tokenDict = response.value as? NSDictionary {
                self.getSecretData(tokenDict["access_token"]! as! String)
            }
        }
    }
    
    func getSecretData(_ access_token: String) {
        let headers: HTTPHeaders = [
            "Authorization": "JWT \(access_token)"
        ]
        AF.request("http://localhost:5000/secret", headers: headers).responseString { response in
            print(response.value!)
        }
    }

}

