//
//  OtherViewController.swift
//  HelloController
//
//  Created by arjuna sky kok on 22/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

protocol OtherViewControllerDelegate : class {
    func getStringFromOtherViewController(string: String!)
}

class OtherViewController: UIViewController {
    
    var string : String?
    weak var delegate : OtherViewControllerDelegate?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        print(string!)
    }

    @IBAction func dismiss(_ sender: Any?) {
        self.delegate?.getStringFromOtherViewController(string: "Hello from OtherViewController")
        //self.presentingViewController?.dismiss(animated: true)
    }


}
