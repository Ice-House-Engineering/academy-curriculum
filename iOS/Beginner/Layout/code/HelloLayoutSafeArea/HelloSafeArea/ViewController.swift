//
//  ViewController.swift
//  HelloSafeArea
//
//  Created by arjuna sky kok on 25/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let view1 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view1.backgroundColor = .red
        view1.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view1)
        
        let anchor1 = view1.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor)
        let anchor2 = view1.widthAnchor.constraint(equalToConstant: 300)
        let anchor3 = view1.heightAnchor.constraint(equalToConstant: 100)
        let anchor4 = view1.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor1, anchor2, anchor3, anchor4])
    }


}

