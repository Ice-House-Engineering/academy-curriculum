//
//  ViewController.swift
//  HelloTesting1
//
//  Created by arjuna sky kok on 25/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    var button: UIButton!
    var label: UILabel!
    var presentingButton: UIButton!
    var pickerView: UIPickerView!
    var slider: UISlider!
    var cryptocurrencies = ["Bitcoin", "Ethereum", "Monero", "Litecoin"]

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        self.button = UIButton()
        self.button.backgroundColor = .green
        self.button.setTitle("Button", for: .normal)
        self.button.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.button)
        NSLayoutConstraint.activate([
            self.button.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.button.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 100)
        ])
        self.button.accessibilityIdentifier = "primaryButton"
        self.button.addTarget(self, action: #selector(buttonTapped(_:)), for: .touchUpInside)
        
        self.label = UILabel()
        self.label.text = "Day"
        self.label.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.label)
        NSLayoutConstraint.activate([
            self.label.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.label.topAnchor.constraint(equalTo: self.button.bottomAnchor, constant: 100)
        ])
        
        self.presentingButton = UIButton()
        self.presentingButton.backgroundColor = .red
        self.presentingButton.setTitle("Presenting Button", for: .normal)
        self.presentingButton.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.presentingButton)
        NSLayoutConstraint.activate([
            self.presentingButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.presentingButton.topAnchor.constraint(equalTo: self.label.bottomAnchor, constant: 100)
        ])
        self.presentingButton.addTarget(self, action: #selector(presentingButtonTapped(_:)), for: .touchUpInside)
        
        self.pickerView = UIPickerView()
        self.pickerView.delegate = self
        self.pickerView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.pickerView)
        NSLayoutConstraint.activate([
            self.pickerView.topAnchor.constraint(equalTo: self.presentingButton.bottomAnchor, constant: 100),
            self.pickerView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.pickerView.widthAnchor.constraint(equalToConstant: 400)
        ])
        
        self.slider = UISlider()
        self.slider.minimumValue = 0
        self.slider.maximumValue = 100
        self.slider.minimumTrackTintColor = .yellow
        self.slider.maximumTrackTintColor = .blue
        self.slider.translatesAutoresizingMaskIntoConstraints = false
        self.slider.accessibilityIdentifier = "slider"
        self.view.addSubview(self.slider)
        NSLayoutConstraint.activate([
            self.slider.topAnchor.constraint(equalTo: self.pickerView.bottomAnchor, constant: 100),
            self.slider.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.slider.widthAnchor.constraint(equalToConstant: 400)
        ])
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return cryptocurrencies.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return cryptocurrencies[row]
    }
    
    @objc func buttonTapped(_ sender: UIButton) {
        self.label.text = "Night"
    }
    
    @objc func presentingButtonTapped(_ sender: UIButton) {
        let viewController2 = ViewController2()
        self.present(viewController2, animated: true, completion: nil)
    }

    func calculate(_ a: Int, _ b: Int) -> Int {
        return a + b
    }

}

