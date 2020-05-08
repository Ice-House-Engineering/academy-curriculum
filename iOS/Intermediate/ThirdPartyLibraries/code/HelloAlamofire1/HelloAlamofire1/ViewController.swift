//
//  ViewController.swift
//  HelloAlamofire1
//
//  Created by arjuna sky kok on 17/12/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    // The server code is in Common/Backend/code/HelloBackend1
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        download()
        getData()
        getHtmlData()
        getNotFound()
    }
    
    func getData() {
        AF.request("http://localhost:5000")
            .validate(statusCode: 200..<300)
            .responseString { response in
                switch response.result {
                case .success:
                    if let string = response.value {
                        print("=== request ===")
                        print(response.response!.statusCode)
                        print(string)
                    }
                case let .failure(error):
                    print(error)
                }
        }
    }

    func download() {
        AF.download("http://localhost:5000").responseData { response in
            if let data = response.value, let string = String(data: data, encoding: .utf8) {
                print("=== download ===")
                print(string)
            }
        }
    }
    
    func getHtmlData() {
        AF.request("http://localhost:5000/get_html_file")
            .responseString { response in
                if let string = response.value {
                    print("=== mimeType ===")
                    print(response.response!.mimeType!)
                    print(string)
                }
        }
    }
    
    func getNotFound() {
        AF.request("http://localhost:5000/get_not_found").response { response in
            print("=== not found ===")
            switch response.result {
            case .success:
                print(response.response!.statusCode)
            case let .failure(error):
                print(error)
            }
        }
    }

}

