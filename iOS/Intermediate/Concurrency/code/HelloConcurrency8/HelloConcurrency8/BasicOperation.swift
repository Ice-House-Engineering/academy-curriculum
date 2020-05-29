//
//  BasicOperation.swift
//  HelloConcurrency8
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class BasicOperation : Operation {
    var result: Int = 0
    
    override func main() {
        for i in (1...10) {
            if isCancelled { return }
            sleep(1)
            result = i * 10
        }
    }
}
