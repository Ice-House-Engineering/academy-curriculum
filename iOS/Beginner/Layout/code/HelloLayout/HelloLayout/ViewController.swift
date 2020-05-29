//
//  ViewController.swift
//  HelloLayout
//
//  Created by arjuna sky kok on 15/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        //let myview = MyView(frame: CGRect(x: 0, y: 0, width: 100, height: 100))
        //myview.backgroundColor = .green
        //self.view.addSubview(myview)
        
        //let view = UIView(frame: self.view.bounds.inset(by: UIEdgeInsets(top: 60, left: 60, bottom: 600, right: 60)))
        //view.backgroundColor = .green
        //self.view.addSubview(view)
        
        //view.autoresizingMask = [.flexibleWidth, .flexibleBottomMargin]
        
        let view1 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view1.backgroundColor = .blue
        view1.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view1)
        
        let constraint1 = NSLayoutConstraint(item: self.view,
                                             attribute: .trailing,
                                             relatedBy: .equal,
                                             toItem: view1,
                                             attribute: .trailing,
                                             multiplier: 1,
                                             constant: 0)
        let constraint2 = NSLayoutConstraint(item: view1,
                                             attribute: .width,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 100)
        let constraint3 = NSLayoutConstraint(item: view1,
                                             attribute: .height,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 100)
        self.view.addConstraint(constraint1)
        view1.addConstraint(constraint2)
        view1.addConstraint(constraint3)
        
        let view2 = UIView()
        view2.backgroundColor = .cyan
        view2.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view2)
        
        let view3 = UIView()
        view3.backgroundColor = .magenta
        view3.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view3)
        
        let constraint4 = NSLayoutConstraint(item: view2,
                                             attribute: .centerX,
                                             relatedBy: .equal,
                                             toItem: view3,
                                             attribute: .centerX,
                                             multiplier: 1,
                                             constant: 0)
        let constraint5 = NSLayoutConstraint(item: view1,
                                             attribute: .leading,
                                             relatedBy: .equal,
                                             toItem: view2,
                                             attribute: .trailing,
                                             multiplier: 1,
                                             constant: 20)
        let constraint6 = NSLayoutConstraint(item: view2,
                                             attribute: .width,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 100)
        let constraint7 = NSLayoutConstraint(item: view2,
                                             attribute: .height,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 100)
        let constraint8 = NSLayoutConstraint(item: view3,
                                             attribute: .width,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 140)
        let constraint9 = NSLayoutConstraint(item: view3,
                                             attribute: .height,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 100)
        let constraint10 = NSLayoutConstraint(item: view3,
                                             attribute: .top,
                                             relatedBy: .equal,
                                             toItem: view2,
                                             attribute: .bottom,
                                             multiplier: 1,
                                             constant: 100)
        
        self.view.addConstraint(constraint5)
        view2.addConstraint(constraint6)
        view2.addConstraint(constraint7)
        
        self.view.addConstraint(constraint4)
        view3.addConstraint(constraint8)
        view3.addConstraint(constraint9)
        self.view.addConstraint(constraint10)
        
        let view4 = UIView()
        view4.backgroundColor = .green
        view4.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view4)
        
        /*
        let constraint11 = NSLayoutConstraint(item: view4,
                                             attribute: .width,
                                             relatedBy: .equal,
                                             toItem: nil,
                                             attribute: .notAnAttribute,
                                             multiplier: 1,
                                             constant: 140)
        let constraint12 = NSLayoutConstraint(item: view4,
                                             attribute: .height,
                                             relatedBy: .equal,
                                             toItem: view3,
                                             attribute: .height,
                                             multiplier: 2,
                                             constant: 0)
        let constraint13 = NSLayoutConstraint(item: view4,
                                              attribute: .top,
                                              relatedBy: .equal,
                                              toItem: view3,
                                              attribute: .bottom,
                                              multiplier: 1,
                                              constant: 0)
        let constraint14 = NSLayoutConstraint(item: view4,
                                             attribute: .centerX,
                                             relatedBy: .equal,
                                             toItem: view3,
                                             attribute: .centerX,
                                             multiplier: 1,
                                             constant: 0)
        
        view4.addConstraint(constraint11)
        self.view.addConstraint(constraint12)
        self.view.addConstraint(constraint13)
        self.view.addConstraint(constraint14)
        */
        
        let anchor1 = view4.centerXAnchor.constraint(equalTo: view3.centerXAnchor)
        let anchor2 = view4.widthAnchor.constraint(equalToConstant: 140)
        let anchor3 = view4.heightAnchor.constraint(equalTo: view3.heightAnchor, multiplier: 2)
        let anchor4 = view4.topAnchor.constraint(equalTo: view3.bottomAnchor)
        NSLayoutConstraint.activate([anchor1, anchor2, anchor3, anchor4])
    }


}

