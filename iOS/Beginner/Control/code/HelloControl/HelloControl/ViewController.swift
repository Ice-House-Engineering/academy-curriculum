//
//  ViewController.swift
//  HelloControl
//
//  Created by arjuna sky kok on 15/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    let cryptocurrencyStrings = ["bitcoin", "ethereum", "monero", "filecoin"]
    let deeplearningStrings = ["tensorflow", "keras", "pytorch"]
    
    let imagePickerDelegate = ImagePickerDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        // Activity Indicator
        let aiv = UIActivityIndicatorView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        aiv.style = .gray
        aiv.startAnimating()
        aiv.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(aiv)
        let anchor_aiv_1 = aiv.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 20)
        let anchor_aiv_2 = aiv.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_aiv_1, anchor_aiv_2])
        
        // Progress Bar
        let pv = UIProgressView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        let progress = Progress()
        progress.totalUnitCount = 100
        progress.completedUnitCount = 35
        pv.observedProgress = progress
        //pv.setProgress(0.35, animated: true)
        pv.trackTintColor = .yellow
        pv.progressTintColor = .red
        pv.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(pv)
        let anchor_pv_1 = pv.topAnchor.constraint(equalTo: aiv.bottomAnchor, constant: 20)
        let anchor_pv_2 = pv.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor_pv_3 = pv.widthAnchor.constraint(equalToConstant: 400)
        let anchor_pv_4 = pv.heightAnchor.constraint(equalToConstant: 20)
        NSLayoutConstraint.activate([anchor_pv_1, anchor_pv_2, anchor_pv_3, anchor_pv_4])
        
        // Picker Views
        let picker1 = UIPickerView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        picker1.delegate = self
        picker1.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(picker1)
        let anchor_picker_1 = picker1.topAnchor.constraint(equalTo: pv.bottomAnchor, constant: 20)
        let anchor_picker_2 = picker1.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor_picker_3 = picker1.widthAnchor.constraint(equalToConstant: 400)
        NSLayoutConstraint.activate([anchor_picker_1, anchor_picker_2, anchor_picker_3])
        
        let picker2 = UIPickerView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        picker2.delegate = imagePickerDelegate
        picker2.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(picker2)
        let anchor_picker_4 = picker2.topAnchor.constraint(equalTo: picker1.bottomAnchor, constant: 20)
        let anchor_picker_5 = picker2.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor_picker_6 = picker2.widthAnchor.constraint(equalToConstant: 200)
        NSLayoutConstraint.activate([anchor_picker_4, anchor_picker_5, anchor_picker_6])
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return cryptocurrencyStrings.count
        } else {
            return deeplearningStrings.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return cryptocurrencyStrings[row]
        } else {
            return deeplearningStrings[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        print("Component: ", component)
        print("Row: ", row)
    }

}

class ImagePickerDelegate : NSObject, UIPickerViewDelegate, UIPickerViewDataSource {
    
    let imagePickerStrings = ["pencil", "coffee", "ruler"]
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return imagePickerStrings.count
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        print("Component: ", component)
        print("Row: ", row)
    }
    
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return 100
    }
    
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        let imageName = imagePickerStrings[row]
        let image = UIImage(named: imageName)!
        let imageView = UIImageView()
        imageView.image = image
        imageView.sizeToFit()
        return imageView
    }
}
