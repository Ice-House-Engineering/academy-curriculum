//
//  BasicAsyncOperation.swift
//  HelloConcurrency6
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class BasicAsyncOperation: TemplateAsyncOperation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        let dispatchGlobal = DispatchQueue.global()
        dispatchGlobal.async {
            sleep(2)
            self.result = self.input.uppercased()
            self.s = .f
        }
    }
    
    init(_ input: String) {
        self.input = input
    }
}
