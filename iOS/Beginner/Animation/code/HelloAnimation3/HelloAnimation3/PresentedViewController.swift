//
//  PresentedViewController.swift
//  HelloAnimation3
//
//  Created by arjuna sky kok on 22/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class PresentedViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        self.view.backgroundColor = .green
        let label = UILabel()
        label.text = "Presented View Controller"
        label.numberOfLines = 2
        label.font = UIFont(name: "Arial", size: 46)!
        label.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(label)
        NSLayoutConstraint.activate([
            label.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 300),
            label.widthAnchor.constraint(equalTo: self.view.widthAnchor, constant: 0.8),
            label.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
        
        let button = UIButton()
        button.setTitle("Dismiss", for: .normal)
        button.titleLabel!.font = UIFont(name: "Arial", size: 23)!
        button.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(button)
        NSLayoutConstraint.activate([
            button.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 100),
            button.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
        button.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
    }
    
    @objc func buttonTapped(_ sender: UIButton) {
        self.presentingViewController?.dismiss(animated: true, completion: nil)
    }

}
