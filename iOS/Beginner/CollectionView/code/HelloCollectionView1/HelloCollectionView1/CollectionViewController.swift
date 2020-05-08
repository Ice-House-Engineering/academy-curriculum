//
//  CollectionViewController.swift
//  HelloCollectionView1
//
//  Created by arjuna sky kok on 11/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

private let reuseIdentifier = "Cell"
private let headerIdentifier = "Header"

class CollectionViewController: UICollectionViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Register cell classes
        self.collectionView!.register(UICollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier)
        self.collectionView!.register(UICollectionReusableView.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerIdentifier)
        self.collectionView!.backgroundColor = .white
    }

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 2
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 6
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath)
    
        if cell.viewWithTag(1) == nil {
            let label = UILabel()
            label.tag = 1
            label.translatesAutoresizingMaskIntoConstraints = false
            cell.contentView.addSubview(label)
            NSLayoutConstraint.activate([label.centerXAnchor.constraint(equalTo: cell.contentView.centerXAnchor), label.centerYAnchor.constraint(equalTo: cell.contentView.centerYAnchor)])
        }
        let label = cell.viewWithTag(1) as! UILabel
        label.text = "\(indexPath.section) - \(indexPath.row)"
        cell.backgroundColor = .purple
            
        return cell
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        let header = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerIdentifier, for: indexPath)
        if header.viewWithTag(1) == nil {
            let label = UILabel()
            label.tag = 1
            label.translatesAutoresizingMaskIntoConstraints = false
            header.addSubview(label)
            NSLayoutConstraint.activate([label.centerXAnchor.constraint(equalTo: header.centerXAnchor), label.centerYAnchor.constraint(equalTo: header.centerYAnchor)])
        }
        let label = header.viewWithTag(1) as! UILabel
        label.text = "Section \(indexPath.section)"
        return header
    }
    
    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print(indexPath)
    }

}
