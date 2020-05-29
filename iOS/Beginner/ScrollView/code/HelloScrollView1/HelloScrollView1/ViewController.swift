//
//  ViewController.swift
//  HelloScrollView1
//
//  Created by arjuna sky kok on 1/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIScrollViewDelegate {
    
    let pageControl = UIPageControl()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let sv1 = UIScrollView(frame: CGRect(x: 100, y: 100, width: 200, height: 200))
        self.view.addSubview(sv1)
        sv1.layer.borderColor = UIColor.yellow.cgColor
        sv1.layer.borderWidth = 2
        
        let view1 = UIView(frame: CGRect(x: 0, y: 0, width: 200, height: 150))
        view1.backgroundColor = .red
        sv1.addSubview(view1)
        let view2 = UIView(frame: CGRect(x: 0, y: 170, width: 200, height: 150))
        view2.backgroundColor = .purple
        sv1.addSubview(view2)
        
        sv1.contentSize = CGSize(width: 200, height: 320)
        sv1.setContentOffset(CGPoint(x: 0, y: 50), animated: true)
        sv1.delegate = self
        
        
        let sv2 = UIScrollView()
        sv2.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(sv2)
        sv2.layer.borderColor = UIColor.yellow.cgColor
        sv2.layer.borderWidth = 2
        NSLayoutConstraint.activate([
            sv2.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 320),
            sv2.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 100),
            sv2.widthAnchor.constraint(equalToConstant: 200),
            sv2.heightAnchor.constraint(equalToConstant: 200)
        ])
        
        let view3 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view3.backgroundColor = .red
        view3.translatesAutoresizingMaskIntoConstraints = false
        sv2.addSubview(view3)
        NSLayoutConstraint.activate([
            view3.topAnchor.constraint(equalTo: sv2.topAnchor),
            view3.leadingAnchor.constraint(equalTo: sv2.leadingAnchor),
            view3.widthAnchor.constraint(equalToConstant: 200),
            view3.heightAnchor.constraint(equalToConstant: 150)
        ])
        
        let view4 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view4.backgroundColor = .purple
        view4.translatesAutoresizingMaskIntoConstraints = false
        sv2.addSubview(view4)
        NSLayoutConstraint.activate([
            view4.topAnchor.constraint(equalTo: view3.bottomAnchor, constant: 20),
            view4.leadingAnchor.constraint(equalTo: sv2.leadingAnchor),
            view4.widthAnchor.constraint(equalToConstant: 200),
            view4.heightAnchor.constraint(equalToConstant: 150),
            view4.bottomAnchor.constraint(equalTo: sv2.bottomAnchor)
        ])
        
        sv2.contentInset = UIEdgeInsets(top: 20, left: 0, bottom: 0, right: 0)
        
        let sv3 = UIScrollView()
        sv3.translatesAutoresizingMaskIntoConstraints = false
        sv3.isPagingEnabled = true
        self.view.addSubview(sv3)
        sv3.layer.borderColor = UIColor.yellow.cgColor
        sv3.layer.borderWidth = 2
        NSLayoutConstraint.activate([
            sv3.topAnchor.constraint(equalTo: sv2.bottomAnchor, constant: 20),
            sv3.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 100),
            sv3.widthAnchor.constraint(equalToConstant: 200),
            sv3.heightAnchor.constraint(equalToConstant: 200)
        ])
        
        let view5 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view5.backgroundColor = .red
        view5.translatesAutoresizingMaskIntoConstraints = false
        sv3.addSubview(view5)
        NSLayoutConstraint.activate([
            view5.topAnchor.constraint(equalTo: sv3.topAnchor),
            view5.leadingAnchor.constraint(equalTo: sv3.leadingAnchor),
            view5.widthAnchor.constraint(equalToConstant: 200),
            view5.heightAnchor.constraint(equalToConstant: 150)
        ])
        
        let view6 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view6.backgroundColor = .purple
        view6.translatesAutoresizingMaskIntoConstraints = false
        sv3.addSubview(view6)
        NSLayoutConstraint.activate([
            view6.topAnchor.constraint(equalTo: sv3.topAnchor),
            view6.leadingAnchor.constraint(equalTo: view5.trailingAnchor),
            view6.widthAnchor.constraint(equalToConstant: 200),
            view6.heightAnchor.constraint(equalToConstant: 150)
        ])
        
        let view7 = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        view7.backgroundColor = .green
        view7.translatesAutoresizingMaskIntoConstraints = false
        sv3.addSubview(view7)
        NSLayoutConstraint.activate([
            view7.topAnchor.constraint(equalTo: sv3.topAnchor),
            view7.leadingAnchor.constraint(equalTo: view6.trailingAnchor),
            view7.widthAnchor.constraint(equalToConstant: 200),
            view7.heightAnchor.constraint(equalToConstant: 150),
            view7.trailingAnchor.constraint(equalTo: sv3.trailingAnchor)
        ])
        
        pageControl.translatesAutoresizingMaskIntoConstraints = false
        pageControl.numberOfPages = 3
        pageControl.currentPage = 0
        pageControl.pageIndicatorTintColor = .yellow
        pageControl.currentPageIndicatorTintColor = .blue
        sv3.addSubview(pageControl)
        sv3.delegate = self
        NSLayoutConstraint.activate([
            pageControl.topAnchor.constraint(equalTo: sv3.topAnchor, constant: 140),
            pageControl.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
    }
    
    func scrollViewDidEndDragging(_ scrollView: UIScrollView, willDecelerate decelerate: Bool) {
        let position = (scrollView.contentOffset.x + (0.5 * 200)) / 200
        pageControl.currentPage = Int(position)
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        print(scrollView.contentOffset)
    }

}

