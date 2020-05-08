//
//  ViewController.swift
//  HelloConcurrency4
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let bo = BasicOperation()
        bo.completionBlock = {
            print(bo.result)
        }
        print(bo.result)
        bo.start()
        print("start method from sync operation is blocking")
    }

}

