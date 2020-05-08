//
//  ViewController.swift
//  HelloDataPersistence3
//
//  Created by arjuna sky kok on 16/08/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        do {
            let fm = FileManager.default
            
            let docsurl = try fm.url(for: .documentDirectory,
                                     in: .userDomainMask, appropriateFor: nil, create: false)
            
            let customClass = CustomClass(stringVariable: "String", integerVariable: 45)
            
            let propertyListEncoder = PropertyListEncoder()
            propertyListEncoder.outputFormat = .xml
            
            let customClassDataInXML = try propertyListEncoder.encode(customClass)
            let customClassFileInXML = docsurl.appendingPathComponent("customClassInXML.txt")
            try customClassDataInXML.write(to: customClassFileInXML, options: .atomic)
            
            let customClassDataFromFileInXML = try Data(contentsOf: customClassFileInXML)
            dump(customClassDataFromFileInXML)
            let stringCustomClassDataFromFileInXML = try String(contentsOf: customClassFileInXML)
            dump(stringCustomClassDataFromFileInXML)
            
            propertyListEncoder.outputFormat = .binary
            
            let customClassDataInBinary = try propertyListEncoder.encode(customClass)
            let customClassFileInBinary = docsurl.appendingPathComponent("customClassInBinary.txt")
            try customClassDataInBinary.write(to: customClassFileInBinary, options: .atomic)
            
            let customClassDataFromFileInBinary = try Data(contentsOf: customClassFileInBinary)
            dump(customClassDataFromFileInBinary)
            
            let propertyListDecoder = PropertyListDecoder()
            
            var customClassFromFile = try propertyListDecoder.decode(CustomClass.self, from: customClassDataFromFileInBinary)
            dump(customClassFromFile)
            
            customClassFromFile = try propertyListDecoder.decode(CustomClass.self, from: customClassDataFromFileInXML)
            dump(customClassFromFile)
        } catch {
            print("catch \(error)")
        }
    }


}

