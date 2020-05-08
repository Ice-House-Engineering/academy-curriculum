//
//  ViewController.swift
//  HelloScrollView3
//
//  Created by arjuna sky kok on 1/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    let sv = UIScrollView()
    let textField = UITextField()
    var topConstraint = NSLayoutConstraint()
    var topConstraintKeyboard = NSLayoutConstraint()
    let topDistance : CGFloat = 800
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        sv.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(sv)
        NSLayoutConstraint.activate([
            sv.topAnchor.constraint(equalTo: self.view.topAnchor),
            sv.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            sv.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            sv.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
        
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.backgroundColor = .blue
        textField.delegate = self
        sv.addSubview(textField)
        topConstraint = textField.topAnchor.constraint(equalTo: sv.topAnchor, constant: topDistance)
        topConstraintKeyboard = textField.topAnchor.constraint(equalTo: sv.topAnchor, constant: 0)
        NSLayoutConstraint.activate([
            textField.centerXAnchor.constraint(equalTo: sv.centerXAnchor),
            textField.widthAnchor.constraint(equalToConstant: 200),
            topConstraint
        ])
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(adjustKeyboard(_:)),
                                               name: UIResponder.keyboardWillShowNotification,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(adjustKeyboard(_:)),
                                               name: UIResponder.keyboardWillHideNotification,
                                               object: nil)
    }
    
    @objc func adjustKeyboard(_ notification: Notification) {
        guard let userInfo = notification.userInfo else { return }
        let keyboardFrame = (userInfo[UIResponder.keyboardFrameEndUserInfoKey] as! NSValue).cgRectValue
        
        topConstraintKeyboard.constant = topDistance - keyboardFrame.size.height
        
        let show = notification.name == UIResponder.keyboardWillShowNotification
        
        if show {
            topConstraint.isActive = false
            topConstraintKeyboard.isActive = true
        } else {
            topConstraint.isActive = true
            topConstraintKeyboard.isActive = false
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return false
    }

}

