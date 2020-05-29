//
//  ViewController.swift
//  HelloNetworking14
//
//  Created by arjuna sky kok on 07/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController, URLSessionDataDelegate {

    @IBOutlet weak var imageView: UIImageView!
    
    // If this image is not available anymore, replace it with any image that is quite big
    let imageUrlString = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"
    
    var downloadedData = Data()
    
    private lazy var urlSession: URLSession = {
        let configuration = URLSessionConfiguration.default
        configuration.waitsForConnectivity = true
        return URLSession(configuration: configuration,
                          delegate: self, delegateQueue: nil)
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func buttonOnClicked(_ sender: UIButton) {
        getImage()
    }
    
    @IBAction func buttonOnClickedCaching(_ sender: Any) {
        getImageWithCache()
    }
    
    @IBAction func buttonOnClickedClearCache(_ sender: UIButton) {
        URLCache.shared.removeAllCachedResponses()
    }
    
    @IBAction func buttonOnClickedClearImage(_ sender: UIButton) {
        self.imageView.image = nil
    }
    
    func getImageWithCache() {
        self.imageView.image = nil
        let url = URL(string: imageUrlString)!
        var request = URLRequest(url: url)
        request.cachePolicy = .returnCacheDataDontLoad
        let dataTask = urlSession.dataTask(with: request)
        dataTask.resume()
    }
    
    func getImage() {
        self.imageView.image = nil
        let url = URL(string: imageUrlString)!
        var request = URLRequest(url: url)
        request.cachePolicy = .reloadIgnoringLocalCacheData
        let dataTask = urlSession.dataTask(with: request)
        dataTask.resume()
    }
    
    func urlSession(_ session: URLSession, dataTask: URLSessionDataTask, didReceive data: Data) {
        print(data)
        downloadedData.append(data)
    }
    
    func urlSession(_ session: URLSession, task: URLSessionTask, didCompleteWithError error: Error?) {
        if error == nil {
            let cacheControl = (task.response as! HTTPURLResponse).allHeaderFields["Cache-Control"]!
            print(cacheControl)
            
            let image = UIImage(data: downloadedData)
            DispatchQueue.main.async {
                self.imageView.image = image
            }
            
            downloadedData.removeAll()
        }
    }
    
}

