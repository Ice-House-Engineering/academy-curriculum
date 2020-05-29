//
//  ViewController.swift
//  HelloConcurrency8
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // Canceling synchronous task from OperationQueue
        let op = OperationQueue()
        let bo = BasicOperation()
        print(bo.result)
        op.addOperation(bo)
        sleep(3)
        op.cancelAllOperations()
        print(bo.result)
        
        // Canceling asynchronous task from Operation
        let bao = BasicAsyncOperation()
        print(bao.result)
        bao.start()
        sleep(4)
        bao.cancel()
        print(bao.result)
    }


}

