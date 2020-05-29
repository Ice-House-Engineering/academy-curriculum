//
//  TableViewCell.swift
//  HelloTableView10
//
//  Created by arjuna sky kok on 8/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    @objc func customAction(_ sender: Any?) {
        print("custom menu action")
    }

}
