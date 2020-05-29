//
//  ViewController.swift
//  HelloNetworking9
//
//  Created by arjuna sky kok on 08/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDownloadDelegate {
    
    @IBOutlet weak var imageView: UIImageView!
    
    private lazy var urlSession: URLSession = {
        let configuration = URLSessionConfiguration.default
        configuration.waitsForConnectivity = true
        return URLSession(configuration: configuration,
                          delegate: self, delegateQueue: nil)
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        downloadData()
    }
    
    func downloadData() {
        // If this image is not available anymore, replace it with any image that is quite big
        let imageUrlString = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"
        let url = URL(string: imageUrlString)!
        let dataTask = urlSession.downloadTask(with: url)
        dataTask.resume()
    }
    
    func urlSession(_ session: URLSession, downloadTask: URLSessionDownloadTask, didFinishDownloadingTo location: URL) {
        let data = try! Data(contentsOf: location)
        let image = UIImage(data: data)
        DispatchQueue.main.async {
            self.imageView.image = image
        }
    }
    
    func urlSession(_ session: URLSession, downloadTask: URLSessionDownloadTask, didWriteData bytesWritten: Int64, totalBytesWritten: Int64, totalBytesExpectedToWrite: Int64) {
        print("Total bytes downloaded: \(totalBytesWritten)")
        print("Total bytes downloaded (percentage): \((Float(totalBytesWritten) / Float(totalBytesExpectedToWrite)) * 100)%")
    }

}

