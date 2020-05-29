import UIKit
import SQLite


class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let fm = FileManager.default
        let docsurl = try! fm.url(for: .documentDirectory,
                                 in: .userDomainMask, appropriateFor: nil, create: false)
        
        let sqlitedb = docsurl.appendingPathComponent("db.sqlite3")
        let db = try! Connection(sqlitedb.path)
        
        let clearAll = "drop table if exists pointers; drop table if exists pointers_renamed; drop table if exists unimportants;"
        try! db.execute(clearAll)
        
        let id = Expression<Int64>("id")
        let email = Expression<String>("email")
        let balance = Expression<Double>("balance")
        let verified = Expression<Bool>("verified")
        let city = Expression<String>("city")
        let unimportant_id = Expression<Int64>("unimportant_id")
        let name = Expression<String>("name")
        let amount = Expression<Double>("amount")
        
        let unimportants = Table("unimportants")
        try! db.run(unimportants.create() { t in
            t.column(id, primaryKey: .autoincrement)
            t.column(email, unique: true)
            t.column(balance)
            t.column(verified)
            t.column(city)
        })
        
        let pointers = Table("pointers")
        try! db.run(pointers.create(ifNotExists: true) { t in
            t.column(id, primaryKey: .autoincrement)
            t.column(name, defaultValue: "No Name")
            t.column(unimportant_id)
            t.column(amount)
            t.foreignKey(unimportant_id, references: unimportants, id, delete: .cascade)
            t.check(amount >= 100)
        })
        
        let insertion1 = unimportants.insert(email <- "user1@mailinator.com", balance <- 30, verified <- true, city <- "Jakarta")
        let insertion2 = unimportants.insert(email <- "user2@mailinator.com", balance <- 70, verified <- false, city <- "Kuala Lumpur")
        
        let newId = try! db.run(insertion1)
        print(newId)
        try! db.run(insertion2)
        
        do {
            try db.run(insertion1)
        } catch let error {
            print(error)
        }
        
        let insertion3 = pointers.insert(amount <- 101, unimportant_id <- newId)
        try! db.run(insertion3)
        
        let query = unimportants.select(email, balance, verified)
                                .filter(email == "user1@mailinator.com" || balance >= 0)
                                .order(balance.desc)
                                .limit(3)
            .join(pointers, on: unimportant_id == unimportants[id])
        let rows = try! db.prepare(query)
        for row in rows {
            print(row[email])
            print(row[balance])
        }
        
        let count = try! db.scalar(unimportants.select(balance.count))
        print(count)
        
        let row1 = unimportants.filter(id == 2)
        let update1 = row1.update(email <- "user2-updated@mailinator.com")
        try! db.run(update1)
        
        let delete1 = row1.delete()
        try! db.run(delete1)
        
        let pointers2 = Table("pointers_renamed")
        let rename1 = pointers.rename(pointers2)
        try! db.run(rename1)
        
        let created_at_date = Expression<Date>("created_at")
        let created_at = Expression<String?>("created_at")
        let addColumn1 = pointers2.addColumn(created_at)
        try! db.run(addColumn1)
        
        try! db.transaction {
            let insertion4 = pointers2.insert(amount <- 102, unimportant_id <- newId, created_at_date <- Date())
            try! db.run(insertion4)
            let insertion5 = pointers2.insert(amount <- 110, unimportant_id <- newId, created_at_date <- Date(timeIntervalSince1970: 100000))
            try! db.run(insertion5)
        }
        
        let addIndex1 = pointers2.createIndex(amount)
        try! db.run(addIndex1)
        
        let insertion7 = "insert into pointers_renamed (amount, unimportant_id) values (?, ?)"
        let stmt = try! db.prepare(insertion7)
        try! stmt.run(200, newId)
        print(db.changes)
        
        let drop1 = pointers2.drop()
        try! db.run(drop1)
        
    }

}

