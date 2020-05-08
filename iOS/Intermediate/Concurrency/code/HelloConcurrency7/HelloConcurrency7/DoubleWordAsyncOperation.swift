//
//  DoubleWordAsyncOperation.swift
//  HelloConcurrency7
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class DoubleWordAsyncOperation: Operation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        self.result = input + " " + input
    }
    
    init(_ input: String) {
        self.input = input
    }
}
