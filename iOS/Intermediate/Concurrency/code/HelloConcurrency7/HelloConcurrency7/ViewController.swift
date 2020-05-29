//
//  ViewController.swift
//  HelloConcurrency7
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let op = OperationQueue()
        let doubleOp = DoubleWordAsyncOperation("jAkArta bAnDUNG")
        let uppercaseLowercaseOp = UppercaseLowercaseAsyncOperation()
        let transferOp = BlockOperation {
            uppercaseLowercaseOp.input = doubleOp.result
        }
        uppercaseLowercaseOp.addDependency(transferOp)
        transferOp.addDependency(doubleOp)
        
        op.addOperations([doubleOp, transferOp, uppercaseLowercaseOp], waitUntilFinished: true)
        
        print(uppercaseLowercaseOp.result)
    }


}

