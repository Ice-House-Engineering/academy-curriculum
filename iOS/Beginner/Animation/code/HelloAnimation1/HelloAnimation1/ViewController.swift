//
//  ViewController.swift
//  HelloAnimation1
//
//  Created by arjuna sky kok on 20/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var swordView: UIImageView!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let textField = UITextField(frame: CGRect(x: -100, y: 100, width: 200, height: 50))
        textField.layer.backgroundColor = UIColor.gray.cgColor
        self.view.addSubview(textField)
        
        UIView.animate(withDuration: 0.8, delay: 0.3, options: [.curveEaseIn],
                       animations: {
                         textField.center.x = self.view.bounds.midX
                       },
                       completion: nil
        )
        
        let button = UIButton(frame: CGRect(x: 0, y: 200, width: 200, height: 50))
        button.setTitle("Button", for: .normal)
        button.backgroundColor = .green
        button.center.x = self.view.bounds.midX
        button.alpha = 0.0
        self.view.addSubview(button)
        
        UIView.animate(withDuration: 0.8, delay: 0.3, options: [],
                       animations: {
                         button.alpha = 1.0
                       },
                       completion: nil
        )
        
        let imageView = UIImageView(frame: CGRect(x: 0, y: 350, width: 100, height: 100))
        imageView.image = UIImage(named: "football")
        imageView.center.x = self.view.bounds.midX
        imageView.alpha = 0.0
        self.view.addSubview(imageView)
        
        UIView.animate(withDuration: 0.8,
                       delay: 0.3,
                       usingSpringWithDamping: 0.3,
                       initialSpringVelocity: 0.9,
                       options: [],
                       animations: {
                         imageView.alpha = 1.0
                         imageView.center.y -= 50
                       },
                       completion: nil
        )
        
        swordView = UIImageView(frame: CGRect(x: 0, y: 450, width: 100, height: 100))
        swordView.image = UIImage(named: "swords")
        swordView.center.x = self.view.bounds.midX
        swordView.isHidden = true
        self.view.addSubview(swordView)
        
        let view1 = UIView(frame: CGRect(x: 0, y: 650, width: 100, height: 100))
        view1.backgroundColor = .red
        self.view.addSubview(view1)
        
        UIView.animateKeyframes(withDuration: 2, delay: 0.0, animations: {
        
            UIView.addKeyframe(withRelativeStartTime: 0.0, relativeDuration: 0.2, animations: {
                view1.center.x += 100
                view1.center.y += 10
            })
            
            UIView.addKeyframe(withRelativeStartTime: 0.2, relativeDuration: 0.5, animations: {
                view1.transform = CGAffineTransform(rotationAngle: -.pi / 8)
            })
            
            UIView.addKeyframe(withRelativeStartTime: 0.7, relativeDuration: 0.3, animations: {
                view1.center.x += 350
                view1.center.y -= 50
            })
            
        }, completion: nil)
        
        /*
        let label = UILabel(frame: CGRect(x: -100, y: 250, width: 200, height: 50))
        label.text = "label"
        self.view.addSubview(label)
         
        UIView.animate(withDuration: 0.8, delay: 0.3, options: [.repeat, .autoreverse],
                       animations: {
                         label.center.x += self.view.bounds.width
                       },
                       completion: nil
        )
        */
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        UIView.transition(with: self.swordView,
                          duration: 2.5,
                          options: [.transitionCurlDown],
                          animations: {
                            self.swordView.isHidden = false
                          },
                          completion: nil
        )
    }

}

