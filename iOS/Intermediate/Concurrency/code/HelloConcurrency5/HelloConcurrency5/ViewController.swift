//
//  ViewController.swift
//  HelloConcurrency5
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // Part 1
        print("OperationQueue 1")
        print(Date())
        let op1 = OperationQueue()
        for i in (0...5) {
            op1.addOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
        }
        for i in (6...10) {
            let o = BlockOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
            o.queuePriority = .veryHigh
            op1.addOperation(o)
        }
        op1.waitUntilAllOperationsAreFinished()
        print(Date())
        print()
        
        // Part 2
        print("OperationQueue 2")
        print(Date())
        let op2 = OperationQueue()
        op2.maxConcurrentOperationCount = 3
        for i in (0...10) {
            op2.addOperation {
                sleep(1)
                print("Queue2: Operation \(i)")
            }
        }
        op2.waitUntilAllOperationsAreFinished()
        print(Date())
        
        // Part 3
        let op3 = OperationQueue.main
        op3.addOperation {
            let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
            let view = UIView(frame: viewFrame)
            view.backgroundColor = .blue
            self.view.addSubview(view)
        }
    }

}

