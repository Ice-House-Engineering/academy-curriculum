//
//  ViewController.swift
//  HelloKeychain1
//
//  Created by arjuna sky kok on 30/11/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    private let server = "server1"

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        saveIntoKeychain()
        readFromKeychain()
        updateToKeychain()
        readFromKeychain()
        deleteFromKeychain()
        readFromKeychain()
    }

    func saveIntoKeychain() {
        let account = "email1@domain1.com"
        let token = "token1".data(using: .utf8)!
        
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrAccount as String: account,
                                    kSecAttrServer as String: self.server,
                                    kSecValueData as String: token]
        let status = SecItemAdd(query as CFDictionary, nil)
        
        guard status == errSecSuccess else { print("Error in saveIntoKeychain"); return; }
    }
    
    func readFromKeychain() {
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: server,
                                    kSecMatchLimit as String: kSecMatchLimitOne,
                                    kSecReturnAttributes as String: true,
                                    kSecReturnData as String: true]
        
        var item: CFTypeRef?
        let status = SecItemCopyMatching(query as CFDictionary, &item)
        
        guard status == errSecSuccess else { print("Error in readFromKeychain"); return; }
        guard let existingItem = item as? [String: Any],
        let passwordData = existingItem[kSecValueData as String] as? Data,
            let password = String(data: passwordData, encoding: .utf8),
            let account = existingItem[kSecAttrAccount as String] as? String
            else {
                print("Error in readFromKeychain"); return;
        }
        print("readFromKeychain: \(password)")
        print("readFromKeychain: \(account)")
    }
    
    func updateToKeychain() {
        let account = "email1@domain1.com"
        let token = "token2".data(using: .utf8)!
        
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: self.server]
        let attributes: [String: Any] = [kSecAttrAccount as String: account,
                                         kSecValueData as String: token]
        let status = SecItemUpdate(query as CFDictionary, attributes as CFDictionary)
        
        guard status == errSecSuccess else { print("Error in updateToKeychain"); return; }
    }
    
    func deleteFromKeychain() {
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: server]
        let status = SecItemDelete(query as CFDictionary)
        
        guard status == errSecSuccess else { print("Error in deleteFromKeychain"); return; }
    }

}

