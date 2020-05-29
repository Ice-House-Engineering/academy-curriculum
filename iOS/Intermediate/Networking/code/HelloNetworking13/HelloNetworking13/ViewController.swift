//
//  ViewController.swift
//  HelloNetworking13
//
//  Created by arjuna sky kok on 30/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // Code for backend is in Common/Backend/code/HelloBackend9
        login()
    }
    
    func login() {
        let url = URL(string:"http://localhost:5000/auth")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        let userAuth = UserAuth(username: "john", password: "password")
        let uploadData = try! JSONEncoder().encode(userAuth)
        
        let urlSession = URLSession.shared
        let loginTask = urlSession.uploadTask(with: request, from: uploadData) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let accessToken = try! JSONDecoder().decode(AccessToken.self, from: data!)
                // It's better to encrypt the token rather than put it in memory like this
                // We put it in memory just for the sake of the tutorial
                self.getData(accessToken.access_token)
            }
        }
        loginTask.resume()
    }
    
    func getData(_ accessToken: String) {
        let url = URL(string:"http://localhost:5000/secret")!
        var request = URLRequest(url: url)
        request.setValue("JWT \(accessToken)", forHTTPHeaderField: "Authorization")
        
        let urlSession = URLSession.shared
        let secretTask = urlSession.dataTask(with: request) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 {
                let string = String(decoding: data!, as: UTF8.self)
                print(string)
            }
        }
        secretTask.resume()
    }

}

struct UserAuth: Codable {
    let username: String
    let password: String
}

struct AccessToken: Codable {
    let access_token: String
}
