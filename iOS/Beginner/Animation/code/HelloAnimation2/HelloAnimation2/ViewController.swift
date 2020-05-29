//
//  ViewController.swift
//  HelloAnimation2
//
//  Created by arjuna sky kok on 21/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var view1Constraint1 : NSLayoutConstraint!
    var view1Constraint2 : NSLayoutConstraint!
    var view1Constraint3 : NSLayoutConstraint!
    var view1Constraint4 : NSLayoutConstraint!
    var view2Constraint1 : NSLayoutConstraint!
    var view2Constraint2 : NSLayoutConstraint!
    var view2Constraint3 : NSLayoutConstraint!
    var view2Constraint4 : NSLayoutConstraint!
    
    var view2 : UIView!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let view1 = UIView()
        view1.translatesAutoresizingMaskIntoConstraints = false
        view1.backgroundColor = .red
        self.view.addSubview(view1)
        view1Constraint1 = view1.widthAnchor.constraint(equalToConstant: 100)
        view1Constraint2 = view1.heightAnchor.constraint(equalToConstant: 100)
        view1Constraint3 = view1.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 100)
        view1Constraint4 = view1.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([view1Constraint1, view1Constraint2, view1Constraint3, view1Constraint4])
        
        view2 = UIView()
        view2.translatesAutoresizingMaskIntoConstraints = false
        view2.backgroundColor = .blue
        self.view.addSubview(view2)
        view2Constraint1 = view2.widthAnchor.constraint(equalTo: view1.widthAnchor)
        view2Constraint2 = view2.heightAnchor.constraint(equalTo: view1.heightAnchor)
        view2Constraint3 = view2.topAnchor.constraint(equalTo: view1.bottomAnchor, constant: 100)
        view2Constraint4 = view2.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([view2Constraint1, view2Constraint2, view2Constraint3, view2Constraint4])
        
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("Button", for: .normal)
        button.setTitleColor(.blue, for: .normal)
        self.view.addSubview(button)
        let buttonConstraint1 = button.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 50)
        let buttonConstraint2 = button.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([buttonConstraint1, buttonConstraint2])
        button.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
        self.view2.layoutIfNeeded()
    }
    
    @objc func buttonTapped(_ sender: UIButton) {
        self.view2Constraint3.constant = 400
        UIView.animate(withDuration: 5.8, delay: 0.0, options: [.curveEaseIn],
                       animations: {
                        self.view.layoutIfNeeded()
                       },
                       completion: nil
        )
        
        /*
        self.view2Constraint3.constant = 300
        self.view.setNeedsLayout()
        
        UIView.animateKeyframes(withDuration: 4, delay: 1.0, animations: {
            
            UIView.addKeyframe(withRelativeStartTime: 0.0, relativeDuration: 0.5, animations: {
                self.view.layoutIfNeeded()
                self.view2Constraint2.constant = 200
                self.view2Constraint1.constant = 200
            })
            
            UIView.addKeyframe(withRelativeStartTime: 0.5, relativeDuration: 0.5, animations: {
                self.view.layoutIfNeeded()
            })
            
        }, completion: nil)
        */
    }
    

}

