//
//  ViewController.swift
//  HelloNetworking2
//
//  Created by arjuna sky kok on 31/10/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        downloadImage()
    }
    
    func downloadImage() {
        // Server code is in Common/Backend/code/HelloBackend2
        let url = URL(string:"http://localhost:5000/image")!
        let urlSession = URLSession.shared
        let dataTask = urlSession.dataTask(with: url) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let image = UIImage(data: data!)
                DispatchQueue.main.async {
                    self.imageView.image = image
                }
            }
        }
        dataTask.resume()
    }


}

