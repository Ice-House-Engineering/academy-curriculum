//
//  TemplateAsyncOperation.swift
//  HelloConcurrency8
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class TemplateAsyncOperation: Operation {
    enum State: String {
        case r = "Ready"
        case e = "Executing"
        case f = "Finished"
        fileprivate var key: String {
            return "is" + rawValue
        }
    }
    
    var s = State.r {
        willSet {
            willChangeValue(forKey: newValue.key)
            willChangeValue(forKey: s.key)
        }
        didSet {
            didChangeValue(forKey: oldValue.key)
            didChangeValue(forKey: s.key)
        }
    }
    
    override var isExecuting: Bool { return s == .e }
    
    override var isFinished: Bool { return s == .f }
    
    override var isAsynchronous: Bool { return true }
    
    override func start() {
        if isCancelled {
            s = .f
            return
        }
        main()
        s = .e
    }
    
    override func cancel() {
        s = .f
    }
    
    override var isReady: Bool {
        return super.isReady && s == .r
    }
}
