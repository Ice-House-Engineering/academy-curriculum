//
//  ViewController.swift
//  HelloTableView8
//
//  Created by arjuna sky kok on 7/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var string = ""

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let label = UILabel(frame: CGRect(x: 100, y: 200, width: 100, height: 20))
        label.text = string
        self.view.addSubview(label)
        self.view.backgroundColor = .white
        
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("Dismiss", for: .normal)
        button.backgroundColor = .green
        self.view.addSubview(button)
        NSLayoutConstraint.activate([
            button.leftAnchor.constraint(equalTo: self.view.leftAnchor),
            button.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 50)
        ])
        button.addTarget(self, action: #selector(dismissButtonClick(_:)), for: .touchUpInside)
    }
    
    @objc func dismissButtonClick(_: UIButton) {
        self.presentingViewController?.dismiss(animated: true, completion: nil)
    }

}

