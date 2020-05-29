//
//  BasicOperation.swift
//  HelloConcurrency4
//
//  Created by arjuna sky kok on 26/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import Foundation

class BasicOperation : Operation {
    var result: Int = 0
    
    override func main() {
        sleep(1)
        result = 1
    }
    
}
