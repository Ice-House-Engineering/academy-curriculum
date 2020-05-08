//
//  ViewController.swift
//  HelloAlamofire6
//
//  Created by arjuna sky kok on 30/12/19.
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
        AF.request("http://localhost:5000")
            .authenticate(username: "john", password: "hello")
            .responseString { response in
                print(response)
        }
    }

}

