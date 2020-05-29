//
//  ViewController.swift
//  HelloAnimation3
//
//  Created by arjuna sky kok on 22/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIViewControllerTransitioningDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let buttonView = UIView()
        buttonView.translatesAutoresizingMaskIntoConstraints = false
        buttonView.backgroundColor = .green
        self.view.addSubview(buttonView)
        
        NSLayoutConstraint.activate([
            buttonView.widthAnchor.constraint(equalToConstant: 100),
            buttonView.heightAnchor.constraint(equalToConstant: 100),
            buttonView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            buttonView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor)
        ])
        
        let t1 = UITapGestureRecognizer(target: self, action: #selector(singleTap))
        buttonView.addGestureRecognizer(t1)
    }

    @objc func singleTap(_ t: UITapGestureRecognizer) {
        guard t.view != nil else { return }
        
        let presentedViewController = PresentedViewController()
        presentedViewController.transitioningDelegate = self
        present(presentedViewController, animated: true, completion: nil)
    }
    
    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return PresentingAnimator()
    }
    
    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        return DismissingAnimator()
    }

}

