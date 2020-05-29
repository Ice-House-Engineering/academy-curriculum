//
//  ViewController.swift
//  HelloConcurrency3
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        print(Date())
        
        var result = 0
        let bo = BlockOperation {
            result = 1
            sleep(1)
        }
        bo.completionBlock = {
            print("Finish!!!")
        }
        for i in (0...10) {
            bo.addExecutionBlock {
                sleep(1)
                print("execution block \(i)")
            }
        }
        bo.start()
        print(result)
        
        print(Date())
    }


}

