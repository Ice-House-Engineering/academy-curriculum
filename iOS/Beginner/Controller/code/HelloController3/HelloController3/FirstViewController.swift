//
//  FirstViewController.swift
//  HelloController3
//
//  Created by arjuna sky kok on 22/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func present(_ sender: Any?) {
        let ovc = OtherViewController(nibName: nil, bundle: nil)
        ovc.modalPresentationStyle = .overCurrentContext
        self.definesPresentationContext = true
        self.present(ovc, animated: true)
    }

}

