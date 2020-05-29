import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        do {
            let fm = FileManager.default
            
            let docsurl = try fm.url(for: .documentDirectory,
                                     in: .userDomainMask, appropriateFor: nil, create: false)
            
            let file1 = "file1.txt"
            let contentFile1 : NSString = "NSString"
            let file1Url = docsurl.appendingPathComponent(file1)
            try contentFile1.write(to: file1Url, atomically: true, encoding: String.Encoding.ascii.rawValue)
            
            var textContent = try String(contentsOf: file1Url, encoding: .ascii)
            print(textContent)
            
            let file2 = "file2.txt"
            let contentFile2 : String = "String"
            let file2Url = docsurl.appendingPathComponent(file2)
            try contentFile2.write(to: file2Url, atomically: true, encoding: String.Encoding(rawValue: String.Encoding.ascii.rawValue))
            
            textContent = try String(contentsOf: file2Url, encoding: .ascii)
            print(textContent)
            
            let file3 = "file3.txt"
            let numberFile3 : NSNumber = 300
            let contentFile3 : NSDictionary = ["number": numberFile3]
            let file3Url = docsurl.appendingPathComponent(file3)
            try contentFile3.write(to: file3Url)
            
            textContent = try String(contentsOf: file3Url, encoding: .ascii)
            print(textContent)
            
            let customClassFile = "customClass.txt"
            let customClass = CustomClass(stringVariable: "String", integerVariable: 45)
            let customClassData = try NSKeyedArchiver.archivedData(withRootObject: customClass, requiringSecureCoding: true)
            let customClassFileUrl = docsurl.appendingPathComponent(customClassFile)
            try customClassData.write(to: customClassFileUrl, options: .atomic)
            
            let dataCustomClass = try Data(contentsOf: customClassFileUrl)
            dump(dataCustomClass)
            
            let customClassDataFromFile = try Data(contentsOf: customClassFileUrl)
            let customClassFromFile = try NSKeyedUnarchiver.unarchivedObject(ofClass: CustomClass.self, from: customClassDataFromFile)!
            dump(customClassFromFile)
        } catch {
            print("catch \(error)")
        }
    }

}

