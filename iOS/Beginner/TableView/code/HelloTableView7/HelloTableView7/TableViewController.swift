//
//  TableViewController.swift
//  HelloTableView1
//
//  Created by arjuna sky kok on 6/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    let crypto = ["bitcoin", "ethereum", "dogecoin", "long name really long name really long name I mean really long name super long name", "litecoin"]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
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
        if indexPath.row == 3 {
            cell.textLabel!.numberOfLines = 2
        }

        return cell
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.row == 3 {
            return 200
        } else {
            return 75
        }
    }

}
