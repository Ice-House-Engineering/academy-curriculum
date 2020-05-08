//
//  SinglePageViewController.swift
//  HelloPageController
//
//  Created by arjuna sky kok on 24/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class SinglePageViewController: UIViewController {

    @IBOutlet weak var label: UILabel!
    var string : String!
    
    init(_ string: String) {
        self.string = string
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        label.text = self.string
    }
}
