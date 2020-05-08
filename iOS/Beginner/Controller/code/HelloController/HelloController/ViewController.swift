//
//  ViewController.swift
//  HelloController
//
//  Created by arjuna sky kok on 22/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit


class ViewController: UIViewController, OtherViewControllerDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func getStringFromOtherViewController(string: String!) {
        print(string)
        self.dismiss(animated: true)
    }

    @IBAction func present(_ sender: Any?) {
        let ovc = OtherViewController()
        ovc.string = "Hello from ViewController"
        ovc.delegate = self
        //ovc.modalTransitionStyle = .coverVertical
        //ovc.modalTransitionStyle = .crossDissolve
        //ovc.modalTransitionStyle = .partialCurl
        ovc.modalTransitionStyle = .flipHorizontal
        self.present(ovc, animated: true)
    }
    

}

