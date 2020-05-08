//
//  ViewController.swift
//  HelloConcurrency2
//
//  Created by arjuna sky kok on 12/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        playWithDispatchSpecificKey()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchGroup()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchGroupEnterLeave()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchSemaphore()
    }
    
    func playWithDispatchSpecificKey() {
        let dispatchQueue = DispatchQueue.global()
        let specificKey = DispatchSpecificKey<String>()
        dispatchQueue.sync {
            dispatchQueue.setSpecific(key: specificKey, value: "value1")
        }
        dispatchQueue.async {
            let value = dispatchQueue.getSpecific(key: specificKey)
            print("Value of specific key is \(String(describing: value))")
        }
        Thread.sleep(forTimeInterval: 0.5)
    }
    
    func playWithDispatchGroup() {
        let dispatchGroup = DispatchGroup()
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async(group: dispatchGroup) {
            Thread.sleep(forTimeInterval: 1)
            print("First block in dispatch queue with group")
        }
        dispatchQueue.async(group: dispatchGroup) {
            print("Second block in dispatch queue with group")
        }
        dispatchGroup.wait()
    }
    
    func playWithDispatchGroupEnterLeave() {
        let dispatchGroup = DispatchGroup()
        let dispatchQueue = DispatchQueue.global()
        
        dispatchQueue.async(group: dispatchGroup) {
            dispatchGroup.enter()
            print("Outside nested dispatch queue")
            
            dispatchQueue.async(group: dispatchGroup) {
                defer { dispatchGroup.leave() }
                
                Thread.sleep(forTimeInterval: 1)
                print("Inside nested dispatch queue")
            }
        }
    }
    
    func playWithDispatchSemaphore() {
        let dispatchSemaphore = DispatchSemaphore(value: 3)
        let tenTimes = Range(1...10)
        let dispatchQueue = DispatchQueue.global()
        for n in tenTimes {
            dispatchQueue.async {
                dispatchSemaphore.wait()
                defer { dispatchSemaphore.signal() }
                print("Doing thing: \(n)")
                Thread.sleep(forTimeInterval: 1)
                print("Done thing: \(n)")
            }
        }
        Thread.sleep(forTimeInterval: 1)
    }

}

