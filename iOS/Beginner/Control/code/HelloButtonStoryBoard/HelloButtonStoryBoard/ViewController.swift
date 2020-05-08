//
//  ViewController.swift
//  HelloButtonStoryBoard
//
//  Created by arjuna sky kok on 19/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var button: UIButton!
    @IBOutlet weak var switchControl: UISwitch!
    @IBOutlet weak var pickerView: UIPickerView!
    @IBOutlet weak var searchBar: UISearchBar!
    
    @IBAction func buttonClicked(_ sender: UIButton) {
        print("button diklik")
    }
    
    @IBAction func switchValueDidChange(_ sender: UISwitch) {
        print(sender.isOn)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


}

