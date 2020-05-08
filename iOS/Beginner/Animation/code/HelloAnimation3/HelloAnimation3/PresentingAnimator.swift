//
//  PresentingAnimator.swift
//  HelloAnimation3
//
//  Created by arjuna sky kok on 24/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class PresentingAnimator: NSObject, UIViewControllerAnimatedTransitioning {
    
    func transitionDuration(using transitionContext: UIViewControllerContextTransitioning?) -> TimeInterval {
        return 2.0
    }
    
    func animateTransition(using transitionContext: UIViewControllerContextTransitioning) {
        let containerView = transitionContext.containerView
        let presentedView = transitionContext.view(forKey: .to)!
        let presentingView = transitionContext.view(forKey: .from)!
        
        let originFrame = CGRect(x: presentingView.frame.midX - 50,
                                 y: presentingView.frame.midY - 50,
                                 width: 100,
                                 height: 100)
        
        let xScaleFactor = originFrame.width / presentedView.frame.width
        let yScaleFactor = originFrame.height / presentedView.frame.height
        
        presentedView.transform = CGAffineTransform(scaleX: xScaleFactor, y: yScaleFactor)
        
        containerView.addSubview(presentedView)
        
        UIView.animate(withDuration: 2.0,
                       delay: 0.0,
                       usingSpringWithDamping: 1,
                       initialSpringVelocity: 0.0,
                       animations: {
                         presentedView.transform = CGAffineTransform.identity
        }, completion: { _ in
            transitionContext.completeTransition(true)
        })
    }
    
}
