//
//  ViewController.swift
//  HelloStackView
//
//  Created by arjuna sky kok on 28/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let view1 = MyView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view1.backgroundColor = .green
        view1.translatesAutoresizingMaskIntoConstraints = false
        view1.height = 80
        view1.width = 100
        self.view.addSubview(view1)
        
        let view2 = MyView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view2.backgroundColor = .red
        view2.translatesAutoresizingMaskIntoConstraints = false
        view2.height = 240
        view2.width = 100
        self.view.addSubview(view2)
        
        let view3 = MyView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view3.backgroundColor = .blue
        view3.translatesAutoresizingMaskIntoConstraints = false
        view3.height = 160
        view3.width = 100
        self.view.addSubview(view3)
        
        let view4 = MyView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view4.backgroundColor = .magenta
        view4.translatesAutoresizingMaskIntoConstraints = false
        view4.height = 100
        view4.width = 100
        self.view.addSubview(view4)
        
        let views : [UIView] = [view1, view2, view3, view4]
        
        let sv = UIStackView(arrangedSubviews: views)
        sv.axis = .vertical
        sv.alignment = .center
        sv.distribution = .fillEqually
        sv.spacing = 20
        sv.translatesAutoresizingMaskIntoConstraints = false
        
        self.view.addSubview(sv)
        
        NSLayoutConstraint.activate([
            sv.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
            sv.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor),
            sv.leadingAnchor.constraint(equalTo: self.view.layoutMarginsGuide.leadingAnchor),
            sv.trailingAnchor.constraint(equalTo: self.view.layoutMarginsGuide.trailingAnchor)
        ])
    }


}

