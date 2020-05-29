//
//  ViewController.swift
//  HelloTextExercise
//
//  Created by arjuna sky kok on 15/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var deskripsiTextView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        deskripsiTextView.layer.borderColor = UIColor.lightGray.cgColor
        deskripsiTextView.layer.borderWidth = 0.3
    }


}

