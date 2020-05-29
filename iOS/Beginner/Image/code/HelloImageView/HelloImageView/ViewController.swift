//
//  ViewController.swift
//  HelloImageView
//
//  Created by arjuna sky kok on 29/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        let image = UIImage(named: "view")!
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        imageView.image = image
        self.view.addSubview(imageView)
        imageView.bounds.origin.y += 300
        
        
        /*
        let bundlePath = Bundle.main.path(forResource: "view", ofType: "jpg")!
        let image = UIImage(contentsOfFile: bundlePath)!
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        imageView.image = image
        self.view.addSubview(imageView)
        */
        
        /*
        let image = UIImage(named: "view")!
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        imageView.image = image
        self.view.addSubview(imageView)
        imageView.contentMode = .right
        imageView.clipsToBounds = true
        */
        
        /*
        let image = UIImage(named: "fruit")!
        let imageStretched = image.resizableImage(withCapInsets: .zero, resizingMode: .stretch)
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        imageView.image = imageStretched
        self.view.addSubview(imageView)
        */
        
        /*
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        let url = URL(string: "https://via.placeholder.com/1024.png")!
        do {
            let data = try Data(contentsOf: url)
            let image = UIImage(data: data)!
            imageView.image = image
        } catch {
            print(error)
        }
        self.view.addSubview(imageView)
        */
        
        /*
        let image = UIImage(named: "view")!
        let imageView = UIImageView(frame: CGRect(x: 50, y: 100, width: 300, height: 300))
        let newSize = CGSize(width: 100, height: 100)
        let renderer = UIGraphicsImageRenderer(size: newSize)
        let resizedImage = renderer.image(actions: { ctx in
            image.draw(in: CGRect.init(origin: CGPoint.zero, size: newSize))
        })
        imageView.image = resizedImage
        self.view.addSubview(imageView)
        */
        
    }


}

