//
//  ViewController.swift
//  HelloConcurrency1
//
//  Created by arjuna sky kok on 09/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        playWithDispatchQueueGlobal()
        Thread.sleep(forTimeInterval: 1)
        playWithCustomDispatchQueue()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchQueueMain()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchWorkItem()
        Thread.sleep(forTimeInterval: 1)
        playWithAfterDeadline()
        Thread.sleep(forTimeInterval: 1)
        playWithNotify()
        Thread.sleep(forTimeInterval: 1)
        playWithConcurrentPerform()
    }
    
    func playWithDispatchQueueGlobal() {
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async {
            print("Inside DispatchQueue.global")
        }
        print("In UI thread (playWithDispatchQueueGlobal)")
    }

    func playWithCustomDispatchQueue() {
        let dispatchQueue = DispatchQueue.init(label: "co.id.icehouse.helloconcurrency1", qos: .userInitiated, attributes: .concurrent, autoreleaseFrequency: .inherit, target: .global())
        dispatchQueue.async {
            print("Inside custom DispatchQueue")
        }
        print("In UI Thread (playWithCustomDispatchQueue)")
    }
    
    func playWithDispatchQueueMain() {
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async {
            print("Inside DispatchQueue.global")
            // self.createUILabel() -> will crash the app
            
            DispatchQueue.main.async { [weak self] in
                if self == nil {
                    return
                }
                
                print("In UI Thread (playWithDispatchQueueMain)")
                // Must be executed inside UI thread (or DispatchQueue.main)
                self!.createUILabel()
            }
        }
        Thread.sleep(forTimeInterval: 1)
    }
    
    func playWithDispatchWorkItem() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem = DispatchWorkItem {
            print("Inside DispatchWorkItem")
        }
        dispatchQueue.async(execute: dispatchWorkItem)
    }
    
    func playWithAfterDeadline() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem = DispatchWorkItem {
            print("Inside DispatchWorkItem with deadline")
        }
        dispatchQueue.asyncAfter(deadline: .init(uptimeNanoseconds: 1000000000), execute: dispatchWorkItem)
        print("Inside playWithAfterDeadline")
    }
    
    func playWithNotify() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem1 = DispatchWorkItem {
            print("Inside DispatchWorkItem1")
        }
        let dispatchWorkItem2 = DispatchWorkItem {
            print("Inside DispatchWorkItem2")
        }
        let dispatchWorkItem3 = DispatchWorkItem {
            print("Inside DispatchWorkItem3")
        }
        dispatchWorkItem2.notify(queue: dispatchQueue, execute: dispatchWorkItem3)
        dispatchWorkItem1.notify(queue: dispatchQueue, execute: dispatchWorkItem2)
        dispatchQueue.async(execute: dispatchWorkItem1)
    }
    
    func playWithConcurrentPerform() {
        DispatchQueue.concurrentPerform(iterations: 5, execute: { index in
            print("Iteration: \(index)")
        })
    }
    
    func createUILabel() {
        let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
        let view = UIView(frame: viewFrame)
        view.backgroundColor = .blue
        self.view.addSubview(view)
    }
}

