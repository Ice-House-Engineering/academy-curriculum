//
//  ViewController.swift
//  HelloButton
//
//  Created by arjuna sky kok on 18/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let button1 = UIButton(type: .system)
        button1.tag = 1
        button1.setTitle("Button1", for: .normal)
        button1.setTitle("Button1 Highlighted", for: .highlighted)
        button1.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(button1)
        let anchor_button1_1 = button1.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 20)
        let anchor_button1_2 = button1.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_button1_1, anchor_button1_2])
        button1.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
        let button2 = UIButton(type: .infoDark)
        button2.tag = 2
        let button2Container = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        button2Container.backgroundColor = .green
        button2Container.translatesAutoresizingMaskIntoConstraints = false
        button2.showsTouchWhenHighlighted = true
        button2.translatesAutoresizingMaskIntoConstraints = false
        button2Container.addSubview(button2)
        self.view.addSubview(button2Container)
        let anchor_button2Container_1 = button2Container.topAnchor.constraint(equalTo: button1.bottomAnchor, constant: 20)
        let anchor_button2Container_2 = button2Container.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor_button2Container_3 = button2Container.widthAnchor.constraint(equalToConstant: 30)
        let anchor_button2Container_4 = button2Container.heightAnchor.constraint(equalToConstant: 30)
        let anchor_button2_1 = button2.centerYAnchor.constraint(equalTo: button2Container.centerYAnchor)
        let anchor_button2_2 = button2.centerXAnchor.constraint(equalTo: button2Container.centerXAnchor)
        NSLayoutConstraint.activate([anchor_button2_1,
                                     anchor_button2_2,
                                     anchor_button2Container_1,
                                     anchor_button2Container_2,
                                     anchor_button2Container_3,
                                     anchor_button2Container_4])
        button2.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
        let button3 = UIButton(type: .custom)
        button3.tag = 3
        button3.setTitle("Button3", for: .normal)
        button3.setTitle("Button3 Highlighted", for: .highlighted)
        button3.setImage(UIImage(named: "earth")!, for: .normal)
        button3.setTitleColor(.blue, for: .normal)
        button3.setTitleColor(.cyan, for: .highlighted)
        button3.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(button3)
        let anchor_button3_1 = button3.topAnchor.constraint(equalTo: button2Container.bottomAnchor, constant: 20)
        let anchor_button3_2 = button3.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_button3_1, anchor_button3_2])
        button3.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
        let button4 = MyButton(type: .custom)
        button4.tag = 4
        button4.setTitle("Button4", for: .normal)
        button4.setBackgroundImage(UIImage(named: "ruler")!, for: .normal)
        button4.setTitleColor(.blue, for: .normal)
        button4.setTitleColor(.red, for: .highlighted)
        button4.translatesAutoresizingMaskIntoConstraints = false
        button4.contentEdgeInsets = UIEdgeInsets(top: 40, left: 40, bottom: 40, right: 40)
        self.view.addSubview(button4)
        let anchor_button4_1 = button4.topAnchor.constraint(equalTo: button3.bottomAnchor, constant: 20)
        let anchor_button4_2 = button4.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_button4_1, anchor_button4_2])
        button4.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
    }
    
    @objc func buttonTapped(_ sender: UIButton) {
        print(sender.tag)
    }


}

