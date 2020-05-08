import UIKit
import CoreData

class ViewController: UIViewController {
    
    private let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    private let request = NSFetchRequest<NSFetchRequestResult>(entityName: "City")

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        initData()
        retrieveData()
    }

    private func initData() {
        let deleteRequest = NSBatchDeleteRequest(fetchRequest: request)
        
        do {
            try context.execute(deleteRequest)
        } catch {
            print(error)
        }
        
        let newCity = City(context: context)
        newCity.name = "Kuala Lumpur"
        newCity.area = 6400
        
        let newPerson = Person(context: context)
        newPerson.name = "John Woo"
        newPerson.age = 46
        
        let newPerson2 = Person(context: context)
        newPerson2.name = "Jack Smith"
        newPerson2.age = 27
        
        newCity.people = [newPerson, newPerson2]
        
        let newCity2 = City(context: context)
        newCity2.name = "Ho Chi Minh"
        newCity2.area = 6900
        
        let newPerson3 = Person(context: context)
        newPerson3.name = " Lisa Cure"
        newPerson3.age = 54
        
        newCity2.people = [newPerson3]
        
        let newCity3 = City(context: context)
        newCity3.name = "Jakarta"
        newCity3.area = 9900
        
        
        do {
            try context.save()
        } catch {
            print(error)
        }
    }
    
    func retrieveData() {
        do {
            let cities = try context.fetch(request) as! [City]
            for c in cities {
                print("\nCity: \(c.name!) \(c.area)")
                for p in c.people! {
                    let person = p as! Person
                    print("Person: \(person.name!) \(person.age)")
                }
            }
        } catch {
            print(error)
        }
    }

}

