//
//  TableViewController.swift
//  HelloTableView1
//
//  Created by arjuna sky kok on 6/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(TableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
        self.tableView.separatorColor = .yellow
        self.tableView.rowHeight = 100
        self.tableView.backgroundColor = .purple
        self.tableView.tableHeaderView = UISwitch()
        
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 25
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)
        
        cell.textLabel!.text = "Halo! \(indexPath.row)"
        cell.detailTextLabel!.text = "downhere"
        cell.imageView!.image = UIImage(named: "swords")
        
        cell.accessoryType = .disclosureIndicator
        cell.indentationLevel = 4
        cell.separatorInset = UIEdgeInsets(top: 0, left: 100, bottom: 0, right: 0)
        cell.backgroundColor = UIColor.cyan
        /*
        let view = UIView(frame: CGRect(x: 0, y: 0, width: 30, height: 30))
        view.backgroundColor = .yellow
        cell.accessoryView = view
        */

        return cell
    }

}
