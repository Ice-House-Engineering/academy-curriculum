//
//  ViewController.swift
//  HelloText
//
//  Created by arjuna sky kok on 10/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        /*
        UIFont.familyNames.forEach { _ in
            //print($0)
        }
        
        UIFont.fontNames(forFamilyName: "Helvetica Neue").forEach { print($0) }
        */
        
        let label1 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label1.text = "Headline"
        label1.translatesAutoresizingMaskIntoConstraints = false
        // label1.font = UIFont(name: "Arial", size: 15)!
        label1.font = UIFont.systemFont(ofSize: 20, weight: .medium)
        self.view.addSubview(label1)
        let anchor1 = label1.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 180)
        let anchor2 = label1.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor1, anchor2])
        
        let label2 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label2.text = "Large Body1\nLarge Body2"
        label2.numberOfLines = 2
        label2.translatesAutoresizingMaskIntoConstraints = false
        label2.font = UIFont.preferredFont(forTextStyle: .headline)
        label2.adjustsFontForContentSizeCategory = true
        self.view.addSubview(label2)
        let anchor3 = label2.topAnchor.constraint(equalTo: label1.bottomAnchor, constant: 0)
        let anchor4 = label2.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor3, anchor4])
        
        let label3 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label3.text = "Another Normal Label"
        label3.translatesAutoresizingMaskIntoConstraints = false
        let font = UIFont(name: "Arial-ItalicMT", size: 15)!
        label3.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: font)
        label3.adjustsFontForContentSizeCategory = true
        self.view.addSubview(label3)
        let anchor5 = label3.topAnchor.constraint(equalTo: label2.bottomAnchor, constant: 50)
        let anchor6 = label3.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor5, anchor6])
        
        let label4 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label4.text = "Another Normal Label"
        label4.translatesAutoresizingMaskIntoConstraints = false
        let _f = UIFont(name: "Arial", size: 15)!
        let _desc = _f.fontDescriptor
        let desc = _desc.withSymbolicTraits(.traitItalic)
        let f = UIFont(descriptor: desc!, size: 0)
        label4.font = UIFontMetrics(forTextStyle: .caption1).scaledFont(for: f)
        label4.adjustsFontForContentSizeCategory = true
        self.view.addSubview(label4)
        let anchor7 = label4.topAnchor.constraint(equalTo: label3.bottomAnchor, constant: 0)
        let anchor8 = label4.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor7, anchor8])
        
        let label5 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label5.text = "Small Text1\nSmall Text2"
        label5.numberOfLines = 2
        label5.translatesAutoresizingMaskIntoConstraints = false
        label5.font = UIFont.preferredFont(forTextStyle: .footnote)
        label5.adjustsFontForContentSizeCategory = true
        self.view.addSubview(label5)
        let anchor9 = label5.leadingAnchor.constraint(equalTo: label2.trailingAnchor, constant: 0)
        let anchor10 = label5.firstBaselineAnchor.constraint(equalTo: label2.lastBaselineAnchor)
        NSLayoutConstraint.activate([anchor9, anchor10])
        
        let label6 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label6.text = "Small Text1\nSmall Text2"
        label6.numberOfLines = 2
        label6.translatesAutoresizingMaskIntoConstraints = false
        label6.font = UIFont.preferredFont(forTextStyle: .footnote)
        label6.adjustsFontForContentSizeCategory = true
        self.view.addSubview(label6)
        let anchor11 = label6.trailingAnchor.constraint(equalTo: label2.leadingAnchor, constant: 0)
        let anchor12 = label6.lastBaselineAnchor.constraint(equalTo: label2.firstBaselineAnchor)
        NSLayoutConstraint.activate([anchor11, anchor12])
        
        let priority1 = UILayoutPriority(1000)
        let priority2 = UILayoutPriority(999)
        let priority3 = UILayoutPriority(998)
        
        let label7 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label7.text = "Long Label Long Label"
        label7.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(label7)
        let anchor13 = label7.topAnchor.constraint(equalTo: label4.bottomAnchor, constant: 40)
        let anchor14 = label7.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor15 = label7.widthAnchor.constraint(equalToConstant: 100)
        anchor15.priority = priority2
        //label7.setContentCompressionResistancePriority(priority1, for: .horizontal)
        label7.setContentCompressionResistancePriority(priority3, for: .horizontal)
        NSLayoutConstraint.activate([anchor13, anchor14, anchor15])
        
        let label8 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label8.text = "Normal Label"
        label8.backgroundColor = .red
        label8.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(label8)
        let anchor16 = label8.topAnchor.constraint(equalTo: label7.bottomAnchor, constant: 20)
        let anchor17 = label8.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor18 = label8.widthAnchor.constraint(equalToConstant: 400)
        anchor18.priority = priority2
        //label8.setContentHuggingPriority(priority3, for: .horizontal)
        label8.setContentHuggingPriority(priority1, for: .horizontal)
        NSLayoutConstraint.activate([anchor16, anchor17, anchor18])
        
        let s1 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            """
        let content = NSAttributedString(string: s1, attributes:[
            .font: UIFont.systemFont(ofSize: 20, weight: .medium),
            .foregroundColor: UIColor(red: 0.251, green: 0.000, blue: 0.502, alpha: 1)
            ])
        let label9 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label9.translatesAutoresizingMaskIntoConstraints = false
        label9.attributedText = content
        label9.numberOfLines = 0
        self.view.addSubview(label9)
        let anchor19 = label9.topAnchor.constraint(equalTo: label8.bottomAnchor, constant: 20)
        let anchor20 = label9.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor21 = label9.widthAnchor.constraint(equalToConstant: 200)
        NSLayoutConstraint.activate([anchor19, anchor20, anchor21])
        
        let content2 = NSMutableAttributedString(string: s1, attributes:[
            .font: UIFont.systemFont(ofSize: 20, weight: .medium),
            .foregroundColor: UIColor(red: 0.251, green: 0.000, blue: 0.502, alpha: 1)
            ])
        let label10 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label10.translatesAutoresizingMaskIntoConstraints = false
        
        let r = (content.string as NSString).range(of: "consectetur adipiscing elit")
        content2.addAttributes([
            .strokeColor: UIColor.red,
            .strokeWidth: -2.0,
            .backgroundColor: UIColor.yellow,
            .strikethroughStyle: NSUnderlineStyle.single.rawValue
            ], range: r)
        
        label10.attributedText = content2
        label10.numberOfLines = 0
        self.view.addSubview(label10)
        let anchor22 = label10.topAnchor.constraint(equalTo: label9.bottomAnchor, constant: 20)
        let anchor23 = label10.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor24 = label10.widthAnchor.constraint(equalToConstant: 400)
        NSLayoutConstraint.activate([anchor22, anchor23, anchor24])
        
        let para = NSMutableParagraphStyle()
        para.headIndent = 20
        para.firstLineHeadIndent = 20
        para.lineBreakMode = .byWordWrapping
        para.alignment = .justified
        para.paragraphSpacing = 10
        content2.addAttribute(.paragraphStyle, value: para, range:NSMakeRange(0, 1))
        let label11 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label11.translatesAutoresizingMaskIntoConstraints = false
        label11.attributedText = content2
        label11.numberOfLines = 0
        self.view.addSubview(label11)
        let anchor25 = label11.topAnchor.constraint(equalTo: label10.bottomAnchor, constant: 20)
        let anchor26 = label11.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor27 = label11.widthAnchor.constraint(equalToConstant: 400)
        NSLayoutConstraint.activate([anchor25, anchor26, anchor27])
        
        let s2 = "Pencil"
        let content3 = NSMutableAttributedString(string: s2)
        let pencil = UIImage(named: "pencil")!
        let pencilatt = NSTextAttachment()
        pencilatt.image = pencil
        pencilatt.bounds = CGRect(x: 0, y: 0, width: pencil.size.width, height: pencil.size.height)
        let pencilattchar = NSAttributedString(attachment: pencilatt)
        let r2 = (content3.string as NSString).range(of: "Pencil")
        content3.insert(pencilattchar, at:(r2.location + r2.length))
        
        let label12 : UILabel = UILabel(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        label12.translatesAutoresizingMaskIntoConstraints = false
        label12.attributedText = content3
        self.view.addSubview(label12)
        let anchor28 = label12.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 60)
        let anchor29 = label12.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor30 = label12.widthAnchor.constraint(equalToConstant: 200)
        NSLayoutConstraint.activate([anchor28, anchor29, anchor30])
    }
    
    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        print(traitCollection.preferredContentSizeCategory)
        print(previousTraitCollection?.preferredContentSizeCategory as Any)
    }
}

