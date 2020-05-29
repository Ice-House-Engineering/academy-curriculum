//
//  ViewController.swift
//  HelloNetworking3
//
//  Created by arjuna sky kok on 05/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        uploadImage()
    }
    
    func uploadImage() {
        // Server code is in Common/Backend/code/HelloBackend3
        let image = UIImage(named: "tree")!
        let imageData = image.jpegData(compressionQuality: 1.0)!
        let url = URL(string:"http://localhost:5000/upload_image")!
        let boundaryConstant = "-------------------------abcabcabcabcabcabcabcabcabcabc"
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        var body = Data()
        body.append("\r\n--\(boundaryConstant)\r\n".data(using: .utf8)!)
        body.append("Content-Disposition: form-data; name=\"file\"; filename=\"tree.jpg\"\r\n".data(using: .utf8)!)
        body.append("Content-Type: image/jpeg\r\n\r\n".data(using: .utf8)!)
        body.append(imageData)
        body.append("\r\n".data(using: .utf8)!)
        body.append("\r\n--\(boundaryConstant)--\r\n".data(using: .utf8)!)
        request.setValue("multipart/form-data; boundary=\(boundaryConstant)", forHTTPHeaderField: "Content-Type")
        request.setValue("\(body.count)", forHTTPHeaderField: "Content-Length")
        let urlSession = URLSession.shared
        let dataTask = urlSession.uploadTask(with: request, from: body) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let string = String(data: data!, encoding: .utf8)
                print(string!)
            }
        }
        dataTask.resume()
    }

}

