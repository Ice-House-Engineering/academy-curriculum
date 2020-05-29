//
//  ViewController.swift
//  HelloScrollView2
//
//  Created by arjuna sky kok on 1/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIScrollViewDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let sv1 = UIScrollView(frame: CGRect(x: 100, y: 100, width: 200, height: 200))
        self.view.addSubview(sv1)
        sv1.layer.borderColor = UIColor.yellow.cgColor
        sv1.layer.borderWidth = 2
        
        let view1 = UIView(frame: CGRect(x: 50, y: 50, width: 100, height: 100))
        view1.backgroundColor = .red
        view1.tag = 1
        sv1.minimumZoomScale = 1.0
        sv1.maximumZoomScale = 3.0
        sv1.delegate = self
        sv1.addSubview(view1)
        
        sv1.contentSize = CGSize(width: 200, height: 200)
    }

    func viewForZooming(in scrollView: UIScrollView) -> UIView? {
        return scrollView.viewWithTag(1)
    }

}

