//
//  TableViewController.swift
//  HelloTableView1
//
//  Created by arjuna sky kok on 6/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    let currencies = ["usd", "yen", "euro", "idr"]
    let crypto = ["bitcoin", "ethereum", "dogecoin", "litecoin"]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
        
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
            return currencies.count
        } else {
            return crypto.count
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        if indexPath.section == 0 {
            cell.textLabel!.text = currencies[indexPath.row]
        } else {
            cell.textLabel!.text = crypto[indexPath.row]
        }

        return cell
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0 {
            return "Normal Currencies"
        } else {
            return "Crypto Currencies"
        }
    }
    
    override func sectionIndexTitles(for tableView: UITableView) -> [String]? {
        return ["N", "C"]
    }

}
