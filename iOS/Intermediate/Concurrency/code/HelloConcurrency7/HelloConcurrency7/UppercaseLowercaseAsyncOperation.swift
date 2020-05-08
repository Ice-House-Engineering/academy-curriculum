//
//  UppercaseLowercaseAsyncOperation.swift
//  HelloConcurrency7
//
//  Created by arjuna sky kok on 27/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class UppercaseLowercaseAsyncOperation: Operation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        let words = input.components(separatedBy: " ")
        self.result = words[0].uppercased() + " " + words[1].lowercased()
    }
}
