//
//  ViewController.swift
//  HelloAlamofire2
//
//  Created by arjuna sky kok on 20/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {
    
    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        downloadImage()
    }

    func downloadImage() {
        // Server code is in Common/Backend/code/HelloBackend2
        AF.download("http://localhost:5000/image").responseData { response in
            if let data = response.value, let image = UIImage(data: data) {
                DispatchQueue.main.async {
                    self.imageView.image = image
                }
            }
        }
    }

}

