//
//  ViewController.swift
//  HelloControllerStoryBoard
//
//  Created by arjuna sky kok on 24/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func presentLightGreenButtonClicked(_ sender: UIButton) {
        //performSegue(withIdentifier: "yellowToGreen", sender: nil)
        let lightGreenController : UIViewController = storyboard!.instantiateViewController(withIdentifier: "lightGreenStoryboard")
        present(lightGreenController, animated: true, completion: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "yellowToGreen" {
            let greenViewController = segue.destination as! GreenViewController
        }
    }
    
    @IBAction func unwind(_ seg: UIStoryboardSegue) {
        
    }

}

