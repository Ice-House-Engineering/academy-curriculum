//
//  ViewController.swift
//  ViewExercise1
//
//  Created by arjuna sky kok on 15/1/19.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // INITIAL CODE
        let view1 = UIView(frame: CGRect(x: 100, y: 100, width: 200, height: 200))
        view1.backgroundColor = .blue
        
        let view2 = UIView(frame: CGRect(x: 0, y: 0, width: 40, height: 40))
        view2.backgroundColor = .red
        
        view1.addSubview(view2)
        self.view.addSubview(view1)
        // END OF INITIAL CODE
        
        // Answer of Exercise 1
        // view2.frame.origin.x = view1.bounds.maxX - view2.frame.width
        
        // Answer of Exercise 2
        // view2.frame.origin.x = view1.bounds.maxX - view2.frame.width
        // view2.frame.origin.y = view1.bounds.maxY - view2.frame.height
        
        // Answer of Exercise 3
        // view2.center = CGPoint(x: view1.bounds.midX, y: view1.bounds.maxY)
        // let r = CGAffineTransform(rotationAngle: .pi / 4)
        // view2.transform = r
        
        // Answer of Exercise 4
        // view2.removeFromSuperview()
    }


}

