//
//  ViewController.swift
//  HelloTextField
//
//  Created by arjuna sky kok on 12/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate, UITextViewDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let tf : UITextField = UITextField(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        tf.tag = 1
        tf.borderStyle = .roundedRect
        tf.font = UIFont.systemFont(ofSize: 15)
        tf.autocorrectionType = .no
        tf.keyboardType = .asciiCapable
        tf.returnKeyType = .default
        tf.clearButtonMode = .whileEditing
        let iv  = UIImageView()
        let img = UIImage(named: "pencil")!
        iv.image = img
        iv.contentMode = .left
        tf.leftView = iv
        tf.leftViewMode = .unlessEditing
        tf.translatesAutoresizingMaskIntoConstraints = false
        tf.delegate = self
        self.view.addSubview(tf)
        let anchor1 = tf.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 100)
        let anchor2 = tf.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor3 = tf.widthAnchor.constraint(equalToConstant: 200)
        NSLayoutConstraint.activate([anchor1, anchor2, anchor3])
        
        let tv : UITextView = UITextView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        tv.tag = 1
        let s = """
            Ini nomor telponnya: +628888888888.
            Besok cuaca bakal cerah. Indah sekali.
            Benar-benar indah.
            Ayo, pergi ke Liputan6 untuk baca berita.
        """
        let content = NSMutableAttributedString(string: s, attributes:[
            .font: UIFont.systemFont(ofSize: 20, weight: .medium),
            .foregroundColor: UIColor(red: 0.251, green: 0.000, blue: 0.502, alpha: 1)
            ])
        let r = (content.string as NSString).range(of: "Liputan6")
        content.addAttribute(.link, value: "https://liputan6.com", range: r)
        let r2 = (content.string as NSString).range(of: "+628888888888")
        content.addAttribute(.link, value: "tel:+628888888888", range: r2)
        tv.attributedText = content
        tv.layer.borderWidth = 1
        tv.layer.borderColor = UIColor.lightGray.cgColor
        //tv.isEditable = false
        tv.isEditable = true
        tv.isSelectable = true
        tv.delegate = self
        tv.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(tv)
        let anchor4 = tv.topAnchor.constraint(equalTo: tf.bottomAnchor, constant: 100)
        let anchor5 = tv.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor6 = tv.widthAnchor.constraint(equalToConstant: 250)
        let anchor7 = tv.heightAnchor.constraint(equalToConstant: 300)
        NSLayoutConstraint.activate([anchor4, anchor5, anchor6, anchor7])
        
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return false
    }

    func textFieldDidBeginEditing(_ textField: UITextField) {
        print("begin")
        print(textField.tag)
    }
    
    func textFieldDidEndEditing(_ textField: UITextField, reason: UITextField.DidEndEditingReason) {
        print("end")
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if string.isEmpty {
            return true
        }
        
        let decimalCharacters = CharacterSet.decimalDigits
        
        let decimalRange = string.rangeOfCharacter(from: decimalCharacters)
        
        if decimalRange != nil {
            return false
        }
        
        let lc = string
        textField.insertText(lc)
        return true
    }
    
    func textView(_ textView: UITextView, shouldInteractWith URL: URL, in characterRange: NSRange, interaction: UITextItemInteraction) -> Bool {
        print(URL)
        print(interaction)
        return true
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        print(textView.text)
    }

}

