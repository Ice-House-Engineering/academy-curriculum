import UIKit


class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let string1 = UserDefaults.standard.string(forKey: "string1")!
        print(string1)
        
        let number1 = UserDefaults.standard.integer(forKey: "number1")
        print(number1)
        
        let fontData1 = UserDefaults.standard.object(forKey: "cocoa1")
        let font1 = try! NSKeyedUnarchiver.unarchivedObject(ofClasses: [UIFont.self], from: fontData1 as! Data)
        dump(font1)
        
        let property1 = UserDefaults.standard.object(forKey: "property1")
        let customClass1 = try! PropertyListDecoder().decode(CustomClass.self, from: property1 as! Data)
        dump(customClass1)
        
        UserDefaults.standard.set("Hello Sun", forKey: "string1")
    }

}

