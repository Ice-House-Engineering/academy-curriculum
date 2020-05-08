//
//  ViewController.swift
//  HelloAlamofire5
//
//  Created by arjuna sky kok on 23/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        uploadJson()
    }

    func uploadJson() {
        // Server code is in Common/Backend/code/HelloBackend5
        let parameters = ["name": "bitcoin"]
        AF.request("http://localhost:5000", method: .post, parameters: parameters, encoder: JSONParameterEncoder.default).responseString { response in
            if let string = response.value {
                print(string)
            }
        }
    }

}

