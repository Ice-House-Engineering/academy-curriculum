import UIKit
import CoreData

class ViewController: UIViewController {
    
    private let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    private var cities = [City]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "City")
        let deleteRequest = NSBatchDeleteRequest(fetchRequest: request)
        
        do {
            print("Deleting Data on Core Data")
            try context.execute(deleteRequest)
        } catch {
            print(error)
        }
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        
        print("Creating New Data on Core Data")
        let newRandomCity = City(context: context)
        newRandomCity.name = "Kuala Lumpur"
        newRandomCity.area = 6400
        newRandomCity.created_at = dateFormatter.date(from: "2002-02-02 14:02:00")!
        
        let newCity = City(context: context)
        newCity.name = "Lagos"
        newCity.area = 7800
        newCity.created_at = dateFormatter.date(from: "2009-02-02 14:02:00")!
        
        do {
            print("Saving Data")
            try context.save()
        } catch {
            print(error)
        }
        
        do {
            print("Using Fetch Request")
            let cities = try context.fetch(request) as! [City]
            for c in cities {
                print("City: \(c.name!) \(c.area) \(dateFormatter.string(from: c.created_at!))")
            }
        } catch {
            print(error)
        }
        
        do {
            print("Using Sort Descriptor")
            let nameSort = NSSortDescriptor(keyPath: \City.name, ascending: true)
            request.sortDescriptors = [nameSort]
            let cities = try context.fetch(request) as! [City]
            for c in cities {
                print("City: \(c.name!) \(c.area) \(dateFormatter.string(from: c.created_at!))")
            }
        } catch {
            print(error)
        }
        
        do {
            print("Using Predicate")
            let dateIn2006 = dateFormatter.date(from: "2006-02-02 14:02:00")!
            let cityPredicate = NSPredicate(format: "name == %@ and area > %@ and created_at > %@",
                                            argumentArray: ["Lagos", 100, dateIn2006])
            request.predicate = cityPredicate
            let cities = try context.fetch(request) as! [City]
            for c in cities {
                print("City: \(c.name!) \(c.area) \(dateFormatter.string(from: c.created_at!))")
            }
        } catch {
            print(error)
        }
        
    }

}

