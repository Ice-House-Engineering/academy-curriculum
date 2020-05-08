//
//  ViewController.swift
//  HelloNetworking8
//
//  Created by arjuna sky kok on 08/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDataDelegate {
    
    @IBOutlet weak var imageView: UIImageView!
    
    private lazy var urlSession: URLSession = {
        let configuration = URLSessionConfiguration.default
        configuration.waitsForConnectivity = true
        return URLSession(configuration: configuration,
                          delegate: self, delegateQueue: nil)
    }()
    
    var downloadedData = Data()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        getImage()
    }
    
    func getImage() {
        // If this image is not available anymore, replace it with any image that is quite big
        let imageUrlString = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"
        let url = URL(string: imageUrlString)!
        let dataTask = urlSession.dataTask(with: url)
        dataTask.resume()
    }
    
    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive data: Data) {
        print(data)
        downloadedData.append(data)
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, didCompleteWithError error: Error?) {
        if error == nil {
            let image = UIImage(data: downloadedData)
            DispatchQueue.main.async {
                self.imageView.image = image
            }
        }
    }

}

