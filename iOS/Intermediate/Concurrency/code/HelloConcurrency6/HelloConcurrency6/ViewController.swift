//
//  ViewController.swift
//  HelloConcurrency6
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let bap = BasicAsyncOperation("Hello World")
        bap.completionBlock = {
            print(bap.result)
            print(Date())
        }
        print(Date())
        bap.start() // Non-blocking
        print(Date())
    }


}

