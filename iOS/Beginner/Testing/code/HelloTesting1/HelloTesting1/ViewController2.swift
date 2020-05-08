//
//  ViewController2.swift
//  HelloTesting1
//
//  Created by arjuna sky kok on 25/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController2: UIViewController {
    
    var textField: UITextField!
    var button: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = .white

        // Do any additional setup after loading the view.
        self.textField = UITextField()
        self.textField.backgroundColor = .yellow
        self.textField.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.textField)
        NSLayoutConstraint.activate([
            self.textField.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.textField.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 100),
            self.textField.widthAnchor.constraint(equalToConstant: 200)
        ])
        self.textField.accessibilityIdentifier = "primaryTextField"
        
        self.button = UIButton()
        self.button.backgroundColor = .green
        self.button.setTitle("Button", for: .normal)
        self.button.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.button)
        NSLayoutConstraint.activate([
            self.button.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.button.topAnchor.constraint(equalTo: self.textField.bottomAnchor, constant: 100)
        ])
    }
    

}
