//
//  ViewController.swift
//  HelloSwiftPackageManager1
//
//  Created by arjuna sky kok on 15/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        Alamofire.request ("https://httpbin.org/get").responseJSON { response in
            print("Request: \(String(describing: response.request))")
        }
    }


}

