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
    
    let copy = #selector(UIResponderStandardEditActions.copy)
    let cut = #selector(UIResponderStandardEditActions.cut)
    let paste = #selector(UIResponderStandardEditActions.paste)
    let customAction = #selector(TableViewCell.customAction(_:))
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(TableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
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
    
    override func tableView(_ tableView: UITableView, shouldShowMenuForRowAt indexPath: IndexPath) -> Bool {
        let customMenu = UIMenuItem(title: "Custom Action", action: customAction)
        UIMenuController.shared.menuItems = [customMenu]
        return true
    }
    
    override func tableView(_ tableView: UITableView, canPerformAction action: Selector, forRowAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return action == customAction || action == copy || action == cut || action == paste
    }
    
    override func tableView(_ tableView: UITableView, performAction action: Selector, forRowAt indexPath: IndexPath, withSender sender: Any?) {
        if action == copy {
            print("copy")
        } else if action == cut {
            print("cut")
        } else if action == paste {
            print("paste")
        }
    }

}
