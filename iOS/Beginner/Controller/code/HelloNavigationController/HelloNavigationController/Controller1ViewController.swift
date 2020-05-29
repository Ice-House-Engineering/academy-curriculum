//
//  Controller1ViewController.swift
//  HelloNavigationController
//
//  Created by arjuna sky kok on 23/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class Controller1ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Number 1"
        let rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(rightBarButtonItemClicked(_:)))
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        
        self.navigationController?.setToolbarHidden(true, animated: true)
    }

    @objc func rightBarButtonItemClicked(_ sender: Any?) {
        self.navigationController?.pushViewController(Controller2ViewController(), animated: true)
    }

}
