//
//  Controller3ViewController.swift
//  HelloNavigationController
//
//  Created by arjuna sky kok on 23/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class Controller3ViewController: UIViewController {

    let view1 = UIView(frame: CGRect(x: 0, y: 0, width: 30, height: 30))
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Number 3"
        self.view1.backgroundColor = .green
        let tap = UITapGestureRecognizer(target: self, action: #selector(tapAction))
        self.view1.addGestureRecognizer(tap)
        let rightBarButtonItem = UIBarButtonItem(customView: view1)
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        
        let barButtonItem1 = UIBarButtonItem(barButtonSystemItem: .action, target: self, action: #selector(barButtonItemClicked(_:)))
        let barButtonItemSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: self, action: nil)
        let barButtonItem2 = UIBarButtonItem(barButtonSystemItem: .camera, target: self, action: #selector(barButtonItemClicked(_:)))
        let items = [barButtonItemSpace, barButtonItem1, barButtonItemSpace, barButtonItem2, barButtonItemSpace]
        self.setToolbarItems(items, animated: true)
        self.navigationController?.setToolbarHidden(false, animated: true)
    }
    
    @objc func barButtonItemClicked(_ sender: Any?) {
        print("clicked")
    }
    
    override func willMove(toParent parent: UIViewController?) {
        super.willMove(toParent: parent)
        if parent == nil {
            self.navigationController?.setToolbarHidden(true, animated: true)
        }
    }
    
    @objc func tapAction(_ sender: Any?) {
        self.navigationController?.pushViewController(Controller4ViewController(), animated: true)
    }
}
