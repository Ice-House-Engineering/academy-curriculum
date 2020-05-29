//
//  ViewController.swift
//  HelloGesture
//
//  Created by arjuna sky kok on 20/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIGestureRecognizerDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let v1 = UIView(frame: CGRect(x: 0, y: 100, width: 100, height: 100))
        v1.backgroundColor = .green
        self.view.addSubview(v1)
        
        let t1 = UITapGestureRecognizer(target: self, action: #selector(singleTap))
        t1.numberOfTapsRequired = 3
        v1.addGestureRecognizer(t1)
        
        let v2 = UIView(frame: CGRect(x: 200, y: 100, width: 100, height: 100))
        v2.backgroundColor = .yellow
        self.view.addSubview(v2)
        
        let t2 = UIPinchGestureRecognizer(target: self, action: #selector(pinch))
        v2.addGestureRecognizer(t2)
        
        let v3 = UIView(frame: CGRect(x: 100, y: 200, width: 100, height: 100))
        v3.backgroundColor = .red
        self.view.addSubview(v3)
        
        let t3 = UIRotationGestureRecognizer(target: self, action: #selector(rotate))
        v3.addGestureRecognizer(t3)
        
        let v4 = UIView(frame: CGRect(x: 0, y: 300, width: 100, height: 100))
        v4.backgroundColor = .purple
        self.view.addSubview(v4)
        
        let t4_right = UISwipeGestureRecognizer(target: self, action: #selector(swipe))
        t4_right.direction = .right
        v4.addGestureRecognizer(t4_right)
        
        let t4_left = UISwipeGestureRecognizer(target: self, action: #selector(swipe))
        t4_left.direction = .left
        v4.addGestureRecognizer(t4_left)
        
        let v5 = UIView(frame: CGRect(x: 200, y: 300, width: 100, height: 100))
        v5.backgroundColor = .brown
        self.view.addSubview(v5)
        
        let t5 = UIPanGestureRecognizer(target: self, action: #selector(translate))
        v5.addGestureRecognizer(t5)
        
        let v6 = UIView(frame: CGRect(x: 100, y: 400, width: 100, height: 100))
        v6.backgroundColor = .blue
        self.view.addSubview(v6)
        
        let t6 = UILongPressGestureRecognizer(target: self, action: #selector(longpress))
        t6.minimumPressDuration = 2
        v6.addGestureRecognizer(t6)
        
        let t7 = UIScreenEdgePanGestureRecognizer(target: self, action: #selector(screen))
        t7.edges = .right
        t7.delegate = self
        self.view.addGestureRecognizer(t7)
    }

    @objc func singleTap(_ t: UITapGestureRecognizer) {
        guard t.view != nil else { return }
        
        print("tap")
    }
    
    @objc func pinch(_ p: UIPinchGestureRecognizer) {
        guard p.view != nil else { return }
        
        if p.state == .began || p.state == .changed {
            p.view?.transform = (p.view?.transform.scaledBy(x: p.scale, y: p.scale))!
            p.scale = 1.0
        }
        print(p.velocity)
    }
    
    @objc func rotate(_ r: UIRotationGestureRecognizer) {
        guard r.view != nil else { return }
        
        if r.state == .began || r.state == .changed {
            r.view?.transform = (r.view?.transform.rotated(by: r.rotation))!
            r.rotation = 0
        }
        print(r.velocity)
    }
    
    @objc func swipe(_ s: UISwipeGestureRecognizer) {
        guard s.view != nil else { return }
        
        var move = 0
        if s.state == .ended {
            switch s.direction {
            case .right:
                move = 100
            case .left:
                move = -100
            default:
                break
            }
            if move != 0 {
                s.view?.transform = (s.view?.transform.translatedBy(x: CGFloat(move), y: 0))!
            }
        }
        print(s.direction)
    }
    
    @objc func translate(_ p: UIPanGestureRecognizer) {
        guard p.view != nil else { return }
        
        let translation = p.translation(in: p.view)
        if let view = p.view {
            view.center = CGPoint(x: view.center.x + translation.x,
                                  y: view.center.y + translation.y)
        }
        p.setTranslation(CGPoint.zero, in: p.view)
    }
    
    @objc func longpress(_ l: UILongPressGestureRecognizer) {
        guard l.view != nil else { return }
        
        print("long press")
    }
    
    @objc func screen(_ s: UIScreenEdgePanGestureRecognizer) {
        guard s.view != nil else { return }
        
        if s.state == .began || s.state == .changed {
            let translation = s.translation(in: s.view)
            print(translation.x)
            print(translation.y)
        }
    }
}

