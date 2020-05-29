//
//  ViewController.swift
//  HelloImageViewExercise2
//
//  Created by arjuna sky kok on 30/1/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let parentImageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        parentImageView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(parentImageView)
        let parentConstraints : [NSLayoutConstraint] = [
            parentImageView.topAnchor.constraint(equalTo: self.view.topAnchor),
            parentImageView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            parentImageView.leftAnchor.constraint(equalTo: self.view.leftAnchor),
            parentImageView.rightAnchor.constraint(equalTo: self.view.rightAnchor)
        ]
        NSLayoutConstraint.activate(parentConstraints)
        let parentImage = UIImage(named: "fruit")!
        let imageTiled = parentImage.resizableImage(withCapInsets: .zero, resizingMode: .tile)
        parentImageView.image = imageTiled
        
        let imageView = UIImageView(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(imageView)
        let constraints : [NSLayoutConstraint] = [
            imageView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            imageView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor)
        ]
        NSLayoutConstraint.activate(constraints)
        let url = URL(string: "https://via.placeholder.com/1024.png")!
        do {
            let data = try Data(contentsOf: url)
            let image = UIImage(data: data)!
            let newSize = CGSize(width: 200, height: 200)
            let renderer = UIGraphicsImageRenderer(size: newSize)
            let resizedImage = renderer.image(actions: { ctx in
                image.draw(in: CGRect.init(origin: CGPoint.zero, size: newSize))
            })
            imageView.image = resizedImage
        } catch {
            print(error)
        }
    }


}

