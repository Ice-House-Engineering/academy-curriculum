//
//  DismissingAnimator.swift
//  HelloAnimation3
//
//  Created by arjuna sky kok on 24/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class DismissingAnimator: NSObject, UIViewControllerAnimatedTransitioning {
    
    func transitionDuration(using transitionContext: UIViewControllerContextTransitioning?) -> TimeInterval {
        return 2.0
    }
    
    func animateTransition(using transitionContext: UIViewControllerContextTransitioning) {
        let containerView = transitionContext.containerView
        let toView = transitionContext.view(forKey: .to)!
        let presentedView = transitionContext.view(forKey: .from)!
        
        let originFrame = CGRect(x: toView.frame.midX - 50,
                                 y: toView.frame.midY - 50,
                                 width: 100,
                                 height: 100)
        
        let xScaleFactor = originFrame.width / toView.frame.width
        let yScaleFactor = originFrame.height / toView.frame.height
        
        let scaleTransform = CGAffineTransform(scaleX: xScaleFactor, y: yScaleFactor)
        
        containerView.addSubview(toView)
        containerView.bringSubviewToFront(presentedView)
        
        UIView.animate(withDuration: 2.0,
                       delay: 0.0,
                       usingSpringWithDamping: 1,
                       initialSpringVelocity: 0.0,
                       animations: {
                         presentedView.transform = scaleTransform
        }, completion: { _ in
            transitionContext.completeTransition(true)
        })
    }
    
}
