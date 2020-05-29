//
//  ViewController.swift
//  HelloAlamofire7
//
//  Created by arjuna sky kok on 31/12/19.
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
        // Change this url if it does not work anymore. We need a big size image url.
        AF.request("https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format")
            .downloadProgress { progress in
                print(progress.fractionCompleted)
            }
            .responseData { response in
                if let data = response.value, let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self.imageView.image = image
                    }
                }
            }
    }

}

