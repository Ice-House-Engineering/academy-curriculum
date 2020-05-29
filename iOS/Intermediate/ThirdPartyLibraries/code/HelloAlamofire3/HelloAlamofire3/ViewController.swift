//
//  ViewController.swift
//  HelloAlamofire3
//
//  Created by arjuna sky kok on 20/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

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
        AF.upload(
            multipartFormData: { multipartFormData in
                multipartFormData.append(imageData, withName: "file", fileName: "tree.jpg", mimeType: "image/jpeg")
                    
            },
            to: "http://localhost:5000/upload_image"
        ).responseString { response in
            if let string = response.value {
                print(string)
            }
        }
    }

}

