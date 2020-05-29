import UIKit
import CoreData


class TableViewController: UITableViewController {
    
    private let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    private var fetchedRC : NSFetchedResultsController<City>!

    override func viewDidLoad() {
        super.viewDidLoad()

        self.tableView.register(TableViewCell.self, forCellReuseIdentifier: "reuseIdentifier")
        
        initData()
        refresh()
    }
    
    private func initData() {
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "City")
        let deleteRequest = NSBatchDeleteRequest(fetchRequest: request)
        
        do {
            try context.execute(deleteRequest)
        } catch {
            print(error)
        }
        
        let newCity = City(context: context)
        newCity.name = "Kuala Lumpur"
        newCity.area = 6400
        
        let newCity2 = City(context: context)
        newCity2.name = "Ho Chi Minh"
        newCity2.area = 6900
        
        let newCity3 = City(context: context)
        newCity3.name = "Jakarta"
        newCity3.area = 9900
        
        let newCity4 = City(context: context)
        newCity4.name = "Tokyo"
        newCity4.area = 9500
        
        let newCity5 = City(context: context)
        newCity5.name = "Seoul"
        newCity5.area = 2500
        
        do {
            try context.save()
        } catch {
            print(error)
        }
    }
    
    private func refresh() {
        let request = City.fetchRequest() as NSFetchRequest<City>
        let nameSort = NSSortDescriptor(keyPath: \City.name, ascending: true)
        request.sortDescriptors = [nameSort]
        
        do {
            fetchedRC = NSFetchedResultsController(fetchRequest: request,
                                                   managedObjectContext: context,
                                                   sectionNameKeyPath: nil, cacheName: nil)
            try fetchedRC.performFetch()
        } catch {
            print(error)
        }
    }


    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let objects = fetchedRC.fetchedObjects else {
            return 0
        }
        return objects.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)
        
        let city = fetchedRC.object(at: indexPath)

        cell.textLabel!.text = city.name
        cell.detailTextLabel!.text = String(city.area)

        return cell
    }

}
