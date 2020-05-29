import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let jsonText =
"""
{"menu": {
  "id": "file",
  "value": "File",
  "created": "2019-08-19T09:08:34Z",
  "popup": {
    "menuitem": [
      {"value": "New", "onclick": "CreateNewDoc()"},
      {"value": "Open", "onclick": "OpenDoc()"},
      {"value": "Close", "onclick": "CloseDoc()"}
    ]
  }
}}
""".data(using: .utf8)!
        
        
        let jsonDecoder = JSONDecoder()
        jsonDecoder.dateDecodingStrategy = .iso8601
        let menujson = try! jsonDecoder.decode(MenuJson.self, from: jsonText)
        dump(menujson)
        
        struct SimpleStruct: Codable {
            var string1: String
            var number1: Int
        }
        let simpleStruct = SimpleStruct(string1: "string1", number1: 56)
        
        let jsonEncoder = JSONEncoder()
        jsonEncoder.outputFormatting = .prettyPrinted
        let jsonData = try! jsonEncoder.encode(simpleStruct)
        let jsonString = String(data: jsonData, encoding: .utf8)!
        print(jsonString)
        
        let jsonText2 =
"""
{"created": "2019-08-19"}
""".data(using: .utf8)!
        
        let jsonDecoder2 = JSONDecoder()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        jsonDecoder2.dateDecodingStrategy = .formatted(dateFormatter)
        if let stringjson = try? jsonDecoder2.decode(StringJson.self, from: jsonText2) {
            dump(stringjson)
        }
    }

}

