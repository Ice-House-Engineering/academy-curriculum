//
//  MyButton.swift
//  HelloButton
//
//  Created by arjuna sky kok on 18/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class MyButton: UIButton {

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    override func titleRect(forContentRect contentRect: CGRect) -> CGRect {
        let result = super.titleRect(forContentRect: contentRect)
        return result.insetBy(dx: -10, dy: -10)
    }

}
