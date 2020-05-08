//
//  ViewController.swift
//  HelloVisualFormatLayout
//
//  Created by arjuna sky kok on 24/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let view1 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view1.backgroundColor = .blue
        view1.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view1)
        
        let view2 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view2.backgroundColor = .green
        view2.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view2)
        
        let view3 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view3.backgroundColor = .cyan
        view3.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view3)
        
        let view4 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view4.backgroundColor = .purple
        view4.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view4)
        
        let view5 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view5.backgroundColor = .brown
        view5.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(view5)
        
        let views: [String:Any] = [
            "view1": view1,
            "view2": view2,
            "view3": view3,
            "view4": view4,
            "view5": view5
        ]
        
        var constraints: [NSLayoutConstraint] = []
        
        let view1VerticalConstraint = NSLayoutConstraint.constraints(withVisualFormat: "V:|-40-[view1(40)]", metrics: nil, views: views)
        constraints += view1VerticalConstraint
        
        let view2VerticalConstraint = NSLayoutConstraint.constraints(withVisualFormat: "V:|-40-[view2(40)]", metrics: nil, views: views)
        constraints += view2VerticalConstraint
        
        let view3VerticalConstraint = NSLayoutConstraint.constraints(withVisualFormat: "V:|-40-[view3(20)]", metrics: nil, views: views)
        constraints += view3VerticalConstraint
        
        //let topHorizontalConstraints = NSLayoutConstraint.constraints(withVisualFormat: "H:|-40-[view1(40)]-10-[view2(40)]-[view3(20)]-20-|", metrics: nil, views: views)
        let topHorizontalConstraints = NSLayoutConstraint.constraints(withVisualFormat: "H:|-40-[view1(40)]-10@250-[view2(40)]-[view3(20)]-20-|", metrics: nil, views: views)
        constraints += topHorizontalConstraints
        
        let view4WidthConstraints = NSLayoutConstraint.constraints(withVisualFormat: "H:|-[view4]-|", metrics: nil, views: views)
        constraints += view4WidthConstraints
        
        let view5WidthConstraints = NSLayoutConstraint.constraints(withVisualFormat: "H:[view5(width)]", metrics: ["width": 40], views: views)
        constraints += view5WidthConstraints
        
        let bottomVerticalConstraints = NSLayoutConstraint.constraints(withVisualFormat: "V:[view4(40)]-20-[view5(40)]-100-|", options: .alignAllCenterX, metrics: nil, views: views)
        constraints += bottomVerticalConstraints
        
        NSLayoutConstraint.activate(constraints)
    }


}

