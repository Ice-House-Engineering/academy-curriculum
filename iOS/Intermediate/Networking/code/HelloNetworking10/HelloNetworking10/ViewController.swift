//
//  ViewController.swift
//  HelloNetworking10
//
//  Created by arjuna sky kok on 13/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDownloadDelegate {
    
    @IBOutlet weak var imageView: UIImageView!
    
    private lazy var urlSession: URLSession = {
        let config = URLSessionConfiguration.background(withIdentifier: "BackgroundSession1")
        config.isDiscretionary = true
        config.sessionSendsLaunchEvents = true
        return URLSession(configuration: config, delegate: self, delegateQueue: nil)
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        downloadData()
    }
    
    func downloadData() {
        // If this image is not available anymore, replace it with any image that is quite big
        let imageUrlString = "https://images.unsplash.com/photo-1573108037329-37aa135a142e"
        let url = URL(string: imageUrlString)!
        let downloadTask = urlSession.downloadTask(with: url)
        downloadTask.earliestBeginDate = Date().addingTimeInterval(1)
        downloadTask.resume()
    }
    
    func urlSession(_ session: URLSession, downloadTask: URLSessionDownloadTask, didWriteData bytesWritten: Int64, totalBytesWritten: Int64, totalBytesExpectedToWrite: Int64) {
        print("Total bytes downloaded: \(totalBytesWritten)")
        print("Total bytes downloaded (percentage): \((Float(totalBytesWritten) / Float(totalBytesExpectedToWrite)) * 100)%")
    }
    
    func urlSession(_ session: URLSession, downloadTask: URLSessionDownloadTask, didFinishDownloadingTo location: URL) {
        let data = try! Data(contentsOf: location)
        let image = UIImage(data: data)
        DispatchQueue.main.async {
            self.imageView.image = image
        }
    }
    
    func urlSessionDidFinishEvents(forBackgroundURLSession session: URLSession) {
        print("urlSessionDidFinishEvents")
        DispatchQueue.main.async {
            if let appDelegate = UIApplication.shared.delegate as? AppDelegate,
                let backgroundCompletionHandler = appDelegate.backgroundCompletionHandler {
                backgroundCompletionHandler()
            }
        }
    }
}

