//
//  TableViewController.swift
//  HelloTableView1
//
//  Created by arjuna sky kok on 6/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    let crypto = ["bitcoin", "ethereum", "dogecoin", "litecoin"]
    let currencies = ["usd", "yen", "yuan", "idr", "euro", "sgd"]
    var useCrypto = false
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
        let uiswitch = UISwitch()
        uiswitch.isOn = useCrypto
        uiswitch.addTarget(self, action: #selector(switchValueDidChange(_:)), for: .valueChanged)
        self.tableView.tableHeaderView = uiswitch
        self.tableView.refreshControl = UIRefreshControl()
        self.tableView.refreshControl!.addTarget(self, action: #selector(doRefresh), for: .valueChanged)
    }
    
    @objc func switchValueDidChange(_: UISwitch) {
        useCrypto = !useCrypto
        self.tableView.reloadData()
    }
    
    @objc func doRefresh(_ sender: Any) {
        (self.tableView.tableHeaderView! as! UISwitch).setOn(!useCrypto, animated: true)
        useCrypto = !useCrypto
        self.tableView.reloadData()
        (sender as! UIRefreshControl).endRefreshing()
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if useCrypto {
            return crypto.count
        } else {
            return currencies.count
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        if useCrypto {
            cell.textLabel!.text = crypto[indexPath.row]
        } else {
            cell.textLabel!.text = currencies[indexPath.row]
        }

        return cell
    }

}
