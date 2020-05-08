//
//  BasicAsyncOperation.swift
//  HelloConcurrency8
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class BasicAsyncOperation : TemplateAsyncOperation {
    var result: Int = 0
    
    override func main() {
        let dispatchGlobal = DispatchQueue.global()
        dispatchGlobal.async {
            for i in (1...10) {
                if self.isCancelled { return }
                sleep(1)
                self.result = i * 10
            }
        }
    }
    
}
