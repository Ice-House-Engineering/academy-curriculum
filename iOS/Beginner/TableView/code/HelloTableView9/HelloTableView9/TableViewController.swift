//
//  TableViewController.swift
//  HelloTableView1
//
//  Created by arjuna sky kok on 6/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    var crypto = ["bitcoin", "ethereum", "dogecoin", "litecoin"]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
        self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return crypto.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        cell.textLabel!.text = crypto[indexPath.row]

        return cell
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            print("delete")
            self.crypto.remove(at: indexPath.row)
            self.tableView.performBatchUpdates({self.tableView.deleteRows(at: [indexPath], with: .top)}, completion: nil)
        } else {
            print("insert")
            let newIndexPath = IndexPath(row: indexPath.row + 1, section: indexPath.section)
            self.crypto.insert("new item", at: newIndexPath.row)
            self.tableView.performBatchUpdates({self.tableView.insertRows(at: [newIndexPath], with: .top)}, completion: nil)
        }
        print(indexPath.row)
    }
    
    override func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let deleteAction = UIContextualAction(style: .destructive, title: nil) {
            action, view, completion in
            self.crypto.remove(at: indexPath.row)
            self.tableView.performBatchUpdates({self.tableView.deleteRows(at: [indexPath], with: .top)}, completion: nil)
            print("delete action")
            completion(true)
        }
        deleteAction.image = UIGraphicsImageRenderer(size: CGSize(width: 30, height: 30)).image { _ in
            UIImage(named: "swords")?.draw(in: CGRect(x: 0, y: 0, width: 30, height: 30))
        }
        let customAction = UIContextualAction(style: .normal, title: "Custom") {
            action, view, completion in
            print("custom action")
            completion(true)
        }
        customAction.backgroundColor = .blue
        return UISwipeActionsConfiguration(actions: [deleteAction, customAction])
    }
    
    override func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        if indexPath.row % 2 == 0 {
            return .delete
        } else {
            return .insert
        }
    }
    
    override func tableView(_ tableView: UITableView, moveRowAt sourceIndexPath: IndexPath, to destinationIndexPath: IndexPath) {
        let row = self.crypto.remove(at: sourceIndexPath.row)
        self.crypto.insert(row, at: destinationIndexPath.row)
        self.tableView.reloadData()
    }
    
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return true
    }

}
