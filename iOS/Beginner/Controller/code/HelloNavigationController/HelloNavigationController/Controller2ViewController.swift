//
//  Controller2ViewController.swift
//  HelloNavigationController
//
//  Created by arjuna sky kok on 23/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class Controller2ViewController: UIViewController {

    let titleView = UIView(frame: CGRect(x: 0, y: 0, width: 30, height: 30))
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Number 2"
        self.titleView.backgroundColor = .yellow
        self.navigationItem.titleView = self.titleView
        let rightBarButtonItem = UIBarButtonItem(image: UIImage(named: "football")!, style: .plain, target: self, action: #selector(rightBarButtonItemClicked(_:)))
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        
        self.navigationController?.setToolbarHidden(true, animated: true)
    }

    @objc func rightBarButtonItemClicked(_ sender: Any?) {
        self.navigationController?.pushViewController(Controller3ViewController(), animated: true)
    }

}
