//
//  Controller4ViewController.swift
//  HelloNavigationController
//
//  Created by arjuna sky kok on 23/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class Controller4ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Number 4"
        let leftBarButtonItem = UIBarButtonItem(image: UIImage(named: "football")!, style: .done, target: self, action: #selector(leftBarButtonItemClicked(_:)))
        self.navigationItem.leftBarButtonItem = leftBarButtonItem
        
        self.navigationController?.navigationBar.barStyle = .black
        self.navigationController?.navigationBar.prefersLargeTitles = true
        
        self.navigationController?.setToolbarHidden(true, animated: true)
    }
    
    @objc func leftBarButtonItemClicked(_ sender: Any?) {
        self.navigationController?.popViewController(animated: true)
        self.navigationController?.navigationBar.barStyle = .default
        self.navigationController?.navigationBar.prefersLargeTitles = false
        self.navigationController?.setToolbarHidden(false, animated: true)
    }
}
