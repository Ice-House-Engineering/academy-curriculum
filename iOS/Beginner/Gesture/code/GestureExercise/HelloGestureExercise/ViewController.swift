//
//  ViewController.swift
//  HelloGestureExercise
//
//  Created by arjuna sky kok on 21/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    let v1 = UIView(frame: CGRect(x: 0, y: 250, width: 100, height: 100))
    var total_translation : CGFloat = 0
    var total_rotation : CGFloat = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        self.v1.backgroundColor = .brown
        self.view.addSubview(self.v1)
        
        let t1 = UIPanGestureRecognizer(target: self, action: #selector(translate))
        self.v1.addGestureRecognizer(t1)
    }
    
    @objc func translate(_ p: UIPanGestureRecognizer) {
        guard p.view != nil else { return }
        
        let translation = p.translation(in: p.view)
        if let view = p.view {
            if p.state == .began || p.state == .changed {
                view.center = CGPoint(x: view.center.x + translation.x,
                                      y: view.center.y)
                let rotation = translation.x * (.pi / 180) * 45 / 200
                let alpha = (translation.x / 100) * 0.5
                view.transform = view.transform.rotated(by: rotation)
                view.alpha -= alpha
                total_translation += translation.x
                total_rotation += rotation
            } else if p.state == .ended {
                if total_translation > 100 {
                    self.v1.removeFromSuperview()
                } else {
                    view.center = CGPoint(x: 50, y: 300)
                    view.transform = view.transform.rotated(by: -total_rotation)
                    view.alpha = 1
                    total_rotation = 0
                    total_translation = 0
                }
            }
        }
        p.setTranslation(CGPoint.zero, in: p.view)
    }

}

