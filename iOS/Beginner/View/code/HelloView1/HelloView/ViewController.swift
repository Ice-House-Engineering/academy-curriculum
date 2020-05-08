//
//  ViewController.swift
//  HelloView
//
//  Created by arjuna sky kok on 12/1/19.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
        let view = UIView(frame: viewFrame)
        view.backgroundColor = .blue
        view.alpha = 0.5
        let t = CGAffineTransform(translationX: 200, y: 200)
        let r = CGAffineTransform(rotationAngle: .pi / 4)
        let s = CGAffineTransform(scaleX: 2, y: 3)
        view.transform = s.concatenating(r).concatenating(t)
        self.view.addSubview(view)
        
        let button = UIButton(frame: viewFrame)
        button.setTitle("Button", for: UIControl.State.normal)
        button.setTitleColor(.black, for: UIControl.State.normal)
        self.view.addSubview(button)
        
        let view2 = UIView(frame: CGRect(x: 200, y: 200, width: 100, height: 100))
        view2.backgroundColor = .green
        let view3 = UIView(frame: CGRect(x: 10, y: 10, width: 50, height: 50))
        view3.backgroundColor = .purple
        let view4 = UIView(frame: CGRect(x: 300, y: 300, width: 50, height: 50))
        view4.backgroundColor = .brown
        
        view2.addSubview(view3)
        self.view.addSubview(view2)
        self.view.addSubview(view4)
        
        let view5 = UIView(frame: CGRect(x: 100, y: 400, width: 100, height: 100))
        view5.backgroundColor = .yellow
        
        let view6 = UIView(frame: view5.bounds.insetBy(dx: 10, dy: 10))
        view6.backgroundColor = .black
        
        view5.addSubview(view6)
        self.view.addSubview(view5)
        
        print(view5.bounds.insetBy(dx: 10, dy: 10))
        print(view5.frame.insetBy(dx: 10, dy: 10))
        print(view5.bounds.minX)
        print(view5.frame.minX)
        print(view5.bounds.maxX)
        print(view5.frame.maxX)
        
        // Resizing view with frame point of view
        //view6.frame.size.height += 20
        //view6.frame.size.width += 20
        
        // Resizing view with bounds point of view
        //view6.bounds.size.height += 20
        //view6.bounds.size.width += 20
        
        let view7 = UIView(frame: CGRect(x: 100, y: 600, width: 100, height: 100))
        view7.backgroundColor = .yellow
        
        let view8 = UIView(frame: CGRect(x: 0, y: 0, width: 200, height: 200))
        view8.backgroundColor = .red
        view8.alpha = 0.3
        
        view7.addSubview(view8)
        self.view.addSubview(view7)
        
        //view7.bounds.origin.y += 100
        view7.bounds.origin.x += 100
        
        view3.center = CGPoint(x: view2.bounds.midX, y: view2.bounds.midY)
        
    }


}

