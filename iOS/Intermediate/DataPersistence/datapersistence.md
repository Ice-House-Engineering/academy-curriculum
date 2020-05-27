# Data Persistence

You can save data to the storage.

## File

We can create a file and a directory on the storage of our iOS application.

Create a new single-view activity project. Name it HelloDataPersistence1.

Edit HelloDataPersistence1 / ViewController.swift.

Add this block inside viewDidLoad method.
```swift
do {
...
} catch {
    print("catch \(error)")
}
```

All subsequent code are put inside do block. Let’s get a FileManager default object.
```swift
let fm = FileManager.default
```

We search, create, delete, copy files and directory using this FileManager default object.

Let’s get an url (location of a directory) with this FileManager default object using url method.
```swift
let docsurl = try fm.url(for: .documentDirectory,
                                     in: .userDomainMask, appropriateFor: nil, create: false)
```

There are four parameters for this url method.

The first is the search path directory. There are a couple of options: applicationDirectory, demoApplicationDirectory, developerApplicationDirectory, adminApplicationDirectory, libraryDirectory.

The second is the domain directory. There are four options: userDomainMask (user’s home directory), localDomainMask (available to everyone on this machine), networkDomainMask (available on the network), systemDomainMask (system files directory). There is a fifth option, allDomainsMask, but it cannot be used for url method. Because these are mask parameters, you can combine more than one parameter.

So with these two parameters, we get the url. For example, applicationDirectory in userDomainMask, or demoApplicationDirectory in userDomainMask or networkDomainMask.

The third parameter is only useful when we want to create a temporary directory.

The fourth parameter is to tell the system to create the directory if it does not exist.

The url has a path which we can use to check the existence.

To check whether the specific path of a url exists or not, we use exists method from file manager instance.
```swift
print("Document directory exists? ", fm.fileExists(atPath: docsurl.path))
```

To iterate the contents of the url, we can get the iterator from contentsOfDirectory method.
```swift
let dirs = try fm.contentsOfDirectory(at: docsurl, includingPropertiesForKeys: nil)
```

The first parameter is the url. The second parameter is the type of properties we want to get from each file inside this url. Nil value means every property. Some examples of properties: kCFURLNameKey, kCFURLLocalizedNameKey, kCFURLPathKey, kCFURLIsVolumeKey, etc.

To iterate the iterator, we can use forEach method.
```swift
try dirs.forEach {
    try fm.removeItem(atPath: $0.path)
}
```

In this example, we delete every file with removeItem method.
```swift
try fm.removeItem(atPath: $0.path)
```

To get a URL inside another URL, we can use appendingPathComponent.
```swift
let folderName = "NewFolder"
let newFolder = docsurl.appendingPathComponent(folderName)
```

docsurl is a variable of URL of documentDirectory in userDomainMask.

We can create a directory in this URL.
```swift
try fm.createDirectory(at: newFolder, withIntermediateDirectories: true)
```

Other than createDirectory method, we can create a file with createFile method.

Let’s create a binary data.
```swift
let newFileName = "NewFile"
let newFile = newFolder.appendingPathComponent(newFileName)
let bytes: [UInt8] = [ 100, 0b1111_1111, 0xFF, 89]
let data = Data(bytes)
fm.createFile(atPath: newFile.path, contents: data)
```

We convert the bytes to the data then we put data in contents parameter of createFile method. As usual, we must specify the path of the new file to the first parameter, which is atPath.

To create a text file, we just have to convert the text to the data.
```swift
let newFileTextName = "NewFileText"
let newFileText = newFolder.appendingPathComponent(newFileTextName)
let text = "Hello World\nSecond line"
let dataText = Data(text.utf8)
fm.createFile(atPath: newFileText.path, contents: dataText)
```

We can also copy the file with copyItem method.
```swift
try fm.copyItem(at: newFile, to: newFile2)
```

To read the content of the text file, you can use String method.
```swift
let textContent = try String(contentsOf: newFileText, encoding: .utf8)
```

Other than contentsOfDirectory method, you can also use enumerator method to get the iterator.
```swift
let enumerators = fm.enumerator(at: docsurl, includingPropertiesForKeys: nil)
print("Files inside directory (nested):")
if enumerators != nil {
    for fileURL in enumerators! {
        print(fileURL)
    }
}
```

To create a temporary directory, we can utilize the third and fourth parameters in url method.
```swift
let temporaryDirectory = try fm.url(for: .itemReplacementDirectory,
                                                in: .userDomainMask,
                                                appropriateFor: newFolder,
                                                create: true)
```

The first parameter must use itemReplacementDirectory value, the third parameter must accept the name of the nested directory inside temporary diretory, the fourth parameter must be true (because we want to create a directory).

Run the application. You will get this output.
```
Document directory:  file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/
Document directory exists?  true
NewFolder exists?  true
NewFile exists?  true
NewFile2 exists?  true
Hello World
Second line
Files inside Document directory:
NewFolder
Files inside directory (nested):
file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/NewFolder/
file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/NewFolder/NewFileText
file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/NewFolder/NewFile2
file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/NewFolder/NewFile
New temporary directory:  file:///Users/arjuna/Library/Developer/CoreSimulator/Devices/41BBBAF4-002C-47B3-A7FE-55A264224244/data/Containers/Data/Application/F2DA169A-E3B5-4DAE-B5CD-0CA39FDE6956/Documents/(A%20Document%20Being%20Saved%20By%20HelloDataPersistence1)/
```

## Serialization with NSKeyedArchiver and NSSecureCoding

We can write a string, a number, or a struct to a file using a couple of options.

Create a new single view project. Name it HelloDataPersistence2.

Edit ViewController.swift.

Add this block inside viewDidLoad method.
```swift
do {
...
} catch {
    print("catch \(error)")
}
```

As usual put the code inside do block.

Start by getting a document directory url.
```swift
let fm = FileManager.default
            
let docsurl = try fm.url(for: .documentDirectory,
         in: .userDomainMask, appropriateFor: nil, create: false)
```

To write NSString to a text file, you use write method of the NSString object.
```swift
let file1 = "file1.txt"
let contentFile1 : NSString = "NSString"
let file1Url = docsurl.appendingPathComponent(file1)
try contentFile1.write(to: file1Url, atomically: true, encoding: String.Encoding.ascii.rawValue)
```

To write String to a text file, you use write method of the String object.
```swift
let file2 = "file2.txt"
let contentFile2 : String = "String"
let file2Url = docsurl.appendingPathComponent(file2)
try contentFile2.write(to: file2Url, atomically: true, encoding: String.Encoding(rawValue: String.Encoding.ascii.rawValue))
```

To write a dictionary, you also use write method.
```swift
let file3 = "file3.txt"
let numberFile3 : NSNumber = 300
let contentFile3 : NSDictionary = ["number": numberFile3]
let file3Url = docsurl.appendingPathComponent(file3)
try contentFile3.write(to: file3Url)
```

To write a class instance, you use write method from the archived version of the class instance.
```swift
let customClassFile = "customClass.txt"
let customClass = CustomClass(stringVariable: "String", integerVariable: 45)
let customClassData = try NSKeyedArchiver.archivedData(withRootObject: customClass, requiringSecureCoding: true)
let customClassFileUrl = docsurl.appendingPathComponent(customClassFile)
try customClassData.write(to: customClassFileUrl, options: .atomic)
```

We serialize custom object with archivedData method from NSKeyedArchiver object. The requiringSecureCoding parameter is to resist object substitution. The “key” word in NSKeyedArchiver means we will use key to serialize our object. This is in contrast with without key approach, where it uses sequence.
```swift
NSKeyedArchiver.archivedData(withRootObject: customClass, requiringSecureCoding: true)
```

The class of CustomClass is defined in other file, CustomClass.swift. Create it.
```swift
import Foundation

class CustomClass: NSObject, NSSecureCoding {
    static var supportsSecureCoding: Bool { return true }
    
    var stringVariable : String
    var integerVariable : Int
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.stringVariable, forKey: "stringKey")
        aCoder.encode(self.integerVariable, forKey: "integerKey")
    }
    
    init(stringVariable: String, integerVariable: Int) {
        self.stringVariable = stringVariable
        self.integerVariable = integerVariable
        super.init()
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.stringVariable = aDecoder.decodeObject(of: NSString.self, forKey: "stringKey")! as String
        self.integerVariable = aDecoder.decodeInteger(forKey: "integerKey") as Int
    }  
}
```

You must extend the class to NSSecureCoding and set the property of supportsSecureCoding to true.
```swift
class CustomClass: NSObject, NSSecureCoding {
    static var supportsSecureCoding: Bool { return true }
```

You need to implement two methods. The encode method (when we serialize our object to the serialized file) and init method with “coder aDecoder: NSCoder” signature (when we deserialize our object from the serialized file).
```swift
func encode(with aCoder: NSCoder) {
    aCoder.encode(self.stringVariable, forKey: "stringKey")
    aCoder.encode(self.integerVariable, forKey: "integerKey")
}

required init?(coder aDecoder: NSCoder) {
    self.stringVariable = aDecoder.decodeObject(of: NSString.self, forKey: "stringKey")! as String
    self.integerVariable = aDecoder.decodeInteger(forKey: "integerKey") as Int
}
```

To serialize our object we can use encode method from NSCoder instance.
```swift
aCoder.encode(self.stringVariable, forKey: "stringKey")
```

self.stringVariable is our object (a string in this case), forKey parameter is our key in the serialized file. We gave “stringKey” as the value for the key.

To unserialize our object, depending on the type of the object, we use decodeObject, decodeInt.
```swift
self.stringVariable = aDecoder.decodeObject(of: NSString.self, forKey: "stringKey")! as String
self.integerVariable = aDecoder.decodeInteger(forKey: "integerKey") as Int
```

The serialized file is binary file. So you can not read it in raw version.
```swift
let dataCustomClass = try Data(contentsOf: customClassFileUrl)
dump(dataCustomClass)
```

To unserialize our object, we can use NSKeyedUnarchiver with unarchivedobject method.
```swift
let customClassDataFromFile = try Data(contentsOf: customClassFileUrl)
let customClassFromFile = try NSKeyedUnarchiver.unarchivedObject(ofClass: CustomClass.self, from: customClassDataFromFile)!
dump(customClassFromFile)
```

Run the application. You will get this output.
```
NSString
String
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
	<key>number</key>
	<integer>300</integer>
</dict>
</plist>

▿ 310 bytes
  - count: 310
  ▿ pointer: 0x00007f91b7404480
    - pointerValue: 140263821427840
▿ <HelloDataPersistence2.CustomClass: 0x600001c19180> #0
  - super: NSObject
  - stringVariable: "String"
  - integerVariable: 45
```

## Serialization with PropertyListEncoder and Codable

The more modern way to serialize objects to file is to use PropertyListEncoder and Codable.

Create a new single view project. Name it HelloDataPersistence3.

Create CustomClass.swift inside HelloDataPersistence3 directory.
```swift
import Foundation

class CustomClass: NSObject, Codable {
    static var supportsSecureCoding: Bool { return true }
    
    var stringVariable : String
    var integerVariable : Int
    
    init(stringVariable: String, integerVariable: Int) {
        self.stringVariable = stringVariable
        self.integerVariable = integerVariable
        super.init()
    }  
}
```
Compared to previous CustomClass implementation, this class extends Codable. It still has an optional supportsSecureCoding property.

Edit ViewController.swift.

Add this block inside viewDidLoad method.
```swift
do {
...
} catch {
    print("catch \(error)")
}

As usual put the code inside do block.

Start by getting a document directory url.

let fm = FileManager.default
            
let docsurl = try fm.url(for: .documentDirectory,
         in: .userDomainMask, appropriateFor: nil, create: false)
```

To serialize our CustomClass instance, use encode method of PropertyListEncoder instance. 
```swift
let customClass = CustomClass(stringVariable: "String", integerVariable: 45)
            
let propertyListEncoder = PropertyListEncoder()
propertyListEncoder.outputFormat = .xml
            
let customClassDataInXML = try propertyListEncoder.encode(customClass)
let customClassFileInXML = docsurl.appendingPathComponent("customClassInXML.txt")
try customClassDataInXML.write(to: customClassFileInXML, options: .atomic)
```

You have two output format of the serialization file, the binary and the xml. In this example, we use xml serialization format. We can read the xml file using String constructor.
```swift
let customClassDataFromFileInXML = try Data(contentsOf: customClassFileInXML)
dump(customClassDataFromFileInXML)
let stringCustomClassDataFromFileInXML = try String(contentsOf: customClassFileInXML)
dump(stringCustomClassDataFromFileInXML)
```

You can also use the binary format in serialization.
```swift
propertyListEncoder.outputFormat = .binary
            
let customClassDataInBinary = try propertyListEncoder.encode(customClass)
let customClassFileInBinary = docsurl.appendingPathComponent("customClassInBinary.txt")
try customClassDataInBinary.write(to: customClassFileInBinary, options: .atomic)
            
let customClassDataFromFileInBinary = try Data(contentsOf: customClassFileInBinary)
dump(customClassDataFromFileInBinary)
```

To decode the object, we use PropertyListDecoder instance. We can decode the object either from the xml file and the binary file.
```swift
let propertyListDecoder = PropertyListDecoder()
            
var customClassFromFile = try propertyListDecoder.decode(CustomClass.self, from: customClassDataFromFileInBinary)
dump(customClassFromFile)
            
customClassFromFile = try propertyListDecoder.decode(CustomClass.self, from: customClassDataFromFileInXML)
dump(customClassFromFile)
```

Run the application and you will get this output.
```
▿ 291 bytes
  - count: 291
  ▿ pointer: 0x00007f8ae3500f20
    - pointerValue: 140234495889184
- "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n<plist version=\"1.0\">\n<dict>\n\t<key>integerVariable</key>\n\t<integer>45</integer>\n\t<key>stringVariable</key>\n\t<string>String</string>\n</dict>\n</plist>\n"
▿ 92 bytes
  - count: 92
  ▿ pointer: 0x0000600000951aa0
    - pointerValue: 105553126038176
▿ <HelloDataPersistence3.CustomClass: 0x600002d13160> #0
  - super: NSObject
  - stringVariable: "String"
  - integerVariable: 45
▿ <HelloDataPersistence3.CustomClass: 0x600002d08760> #0
  - super: NSObject
  - stringVariable: "String"
  - integerVariable: 45

The binary file is more compact than the xml file. But we can not read it directly.
```

## UserDefaults

This is a default database. We can store values here.

Create a new single view project. Name it HelloDataPersistence4.

Create CustomClass.swift inside HelloDataPersistence4.
```swift
import Foundation

class CustomClass: NSObject, Codable {
    static var supportsSecureCoding: Bool { return true }
    
    var stringVariable : String
    var integerVariable : Int
    
    init(stringVariable: String, integerVariable: Int) {
        self.stringVariable = stringVariable
        self.integerVariable = integerVariable
        super.init()
    }  
}
```

Edit AppDelegate.swift.

Edit this method.
```swift
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
```

Add this code below inside the mentioned code.
```swift
let defaultValues = [
    "number1": 3,
    "string1": "Hello World",
    "cocoa1": try! NSKeyedArchiver.archivedData(withRootObject: UIFont.systemFont(ofSize: 16, weight: UIFont.Weight.thin), requiringSecureCoding: true),
    "property1": try! PropertyListEncoder().encode(CustomClass(stringVariable: "string1", integerVariable: 3))
] as [String : Any]
UserDefaults.standard.register(defaults: defaultValues)
        
return true
```

UserDefaults accepts dictionary with String keys and Any values.

For custom objects, we can serialize them with NSKeyedArchiver or PropertyListEncoder.

Then we save the dictionary in UserDefaults.
```swift
UserDefaults.standard.register(defaults: defaultValues)
```

Edit ViewController.swift.

To retrieve the values from UserDefaults, we can use string, integer, and object methods.

To get the string object, we use string method.
```swift
let string1 = UserDefaults.standard.string(forKey: "string1")!
print(string1)
```

To get the integer object, we use integer method.
```swift
let number1 = UserDefaults.standard.integer(forKey: "number1")
print(number1)
```

For custom object, we use object method. Then we need to deserialize it with proper method.
```swift
let fontData1 = UserDefaults.standard.object(forKey: "cocoa1")
let font1 = try! NSKeyedUnarchiver.unarchivedObject(ofClasses: [UIFont.self], from: fontData1 as! Data)
dump(font1)

let property1 = UserDefaults.standard.object(forKey: "property1")
let customClass1 = try! PropertyListDecoder().decode(CustomClass.self, from: property1 as! Data)
dump(customClass1)
```

To set the value in UserDefaults, we can use set method.
```swift
UserDefaults.standard.set("Hello Sun", forKey: "string1")
```

UserDefaults values are saved asynchronously and the updated values are reflected after some time.

Run the application and you will get this output.
```
Hello Sun
3
▿ Optional(<UICTFont: 0x7ffbc0600fd0> font-family: ".SFUIText-Light"; font-weight: normal; font-style: normal; font-size: 16.00pt)
  - some: <UICTFont: 0x7ffbc0600fd0> font-family: ".SFUIText-Light"; font-weight: normal; font-style: normal; font-size: 16.00pt #0
    - super: UIFont
      - super: NSObject
▿ <HelloDataPersistence4.CustomClass: 0x600001bec480> #0
  - super: NSObject
  - stringVariable: "string1"
  - integerVariable: 3
```

## JSON

JSON is one of the most popular file format in programming. Let’s encode and decode json!

Let’s create a single view project. 

Edit ViewController.swift. Edit viewDidLoad method.

Create a json string data.
```swift
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
```

To decode this json string data, we use decode method of JSONDecoder instance.
```swift
let jsonDecoder = JSONDecoder()
jsonDecoder.dateDecodingStrategy = .iso8601
let menujson = try! jsonDecoder.decode(MenuJson.self, from: jsonText)
dump(menujson)
```

Sometimes we encounter the date string like “2019-08-19T09:08:34Z”. We can parse it with iso8601 date decoding strategy.
```swift
jsonDecoder.dateDecodingStrategy = .iso8601
```

The decode method accepts the class of the object that will be parsed json string data and the json string data itself.
```swift
let menujson = try! jsonDecoder.decode(MenuJson.self, from: jsonText)
```

MenuJson struct is defined in other file, MenuJson.swift file. Create MenuJson swift file.
```swift
import Foundation

struct MenuJson : Decodable {
    let menu : Menu
}
```

Menu struct is defined in other file, Menu.swift file. Create Menu.swift file.
```swift
import Foundation

struct Menu : Decodable {
    let id : String
    let value : String
    let popup : PopUp
    let created : Date
}
```

PopUp struct is defined in other file, PopUp.swift. Create PopUp.swift file.
```swift
import Foundation

struct PopUp : Decodable {
    let menuitem : [MenuItem]
}
```

MenuItem struct is defined in other file, MenuItem.swift. Create MenuItem.swift file.
```swift
import Foundation

struct MenuItem : Decodable {
    let valueKey : String
    let onclickKey : String
    
    enum CodingKeys : String, CodingKey {
        case valueKey = "value"
        case onclickKey = "onclick"
    }
    
}
```

All structs extend Decodable and follow the structure in json data.

For MenuItem class, we have CodingKeys enum.
```swift
enum CodingKeys : String, CodingKey {
    case valueKey = "value"
    case onclickKey = "onclick"
}
```

The properties of the struct must have the same name as the properties in json data. 

If json data looks like this:
```json
{“menu”: “help”}
```

The struct can be like this:
```swift
struct MenuStruct : Decodable {
  let menu : String
}
```

Notice the “menu” word in json data and the struct.

But if you want to use different property in the struct, you can use CodingKeys enum.

Back to ViewController.swift.

To encode the object into json data, we use encode method from JSONEncoder instance.
```swift
let jsonEncoder = JSONEncoder()
jsonEncoder.outputFormatting = .prettyPrinted
let jsonData = try! jsonEncoder.encode(simpleStruct)
let jsonString = String(data: jsonData, encoding: .utf8)!
print(jsonString)
```

Then we can convert json data to json string before printing it.

If we want to use different date formatter than iso8601 we can use date formatter.
```swift
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
```

The keypoint is using DateFormatter instance.
```swift
let dateFormatter = DateFormatter()
dateFormatter.dateFormat = "yyyy-MM-dd"
jsonDecoder2.dateDecodingStrategy = .formatted(dateFormatter)
```

The StringJson struct is defined in other file, StringJson.swift. Create StringJson.swift file.
```swift
import Foundation

struct StringJson : Decodable {
    let created : Date
}
```

Run the application. You will get this output.
```
▿ HelloDataPersistence5.MenuJson
  ▿ menu: HelloDataPersistence5.Menu
    - id: "file"
    - value: "File"
    ▿ popup: HelloDataPersistence5.PopUp
      ▿ menuitem: 3 elements
        ▿ HelloDataPersistence5.MenuItem
          - valueKey: "New"
          - onclickKey: "CreateNewDoc()"
        ▿ HelloDataPersistence5.MenuItem
          - valueKey: "Open"
          - onclickKey: "OpenDoc()"
        ▿ HelloDataPersistence5.MenuItem
          - valueKey: "Close"
          - onclickKey: "CloseDoc()"
    ▿ created: 2019-08-19 09:08:34 +0000
      - timeIntervalSinceReferenceDate: 587898514.0
{
  "string1" : "string1",
  "number1" : 56
}
▿ HelloDataPersistence5.StringJson
  ▿ created: 2019-08-18 17:00:00 +0000
    - timeIntervalSinceReferenceDate: 587840400.0
```

## SQLite

We can save data to the database. IOS comes with SQLite by default.

In order to have a nicer API when dealing with SQLite, we need to install a plugin, called SQLite.swift. If not, we have to use a more difficult API.

Create an single view project. Name it HelloDataPersistence6.

Then create a Podfile inside the project directory.
```
use_frameworks!

target 'HelloDataPersistence6' do
  pod 'SQLite.swift', '~> 0.12.0'
end
```

Then in the command line inside the directory run this command: pod install –repo-update.

Edit ViewController.swift.

We need to import the library.
```swift
import SQLite
```

Then edit viewDidLoad method.

Let’s get a path for our sqlite3 database file.
```swift
let fm = FileManager.default
let docsurl = try! fm.url(for: .documentDirectory,
                                 in: .userDomainMask, appropriateFor: nil, create: false)
        
let sqlitedb = docsurl.appendingPathComponent("db.sqlite3")
```

We can get a connection to this database file. The connection will create a database file if the file does not exist.
```swift
let db = try! Connection(sqlitedb.path)
```

We can execute an arbitary statement on database with execute method.
```swift
let clearAll = "drop table if exists pointers; drop table if exists pointers_renamed; drop table if exists unimportants;"
try! db.execute(clearAll)
```

To get the column representation of the table in the database, we can use Expression.
```swift
let id = Expression<Int64>("id")
let email = Expression<String>("email")
let balance = Expression<Double>("balance")
let verified = Expression<Bool>("verified")
let city = Expression<String>("city")
let unimportant_id = Expression<Int64>("unimportant_id")
let name = Expression<String>("name")
let amount = Expression<Double>("amount")
```

It means id variable represents the column with name “id” anda datatype Int64.

To get the table representation, we can use Table.
```swift
let unimportants = Table("unimportants")
```

To really create table with its column, we can use run method of db object. The argument is the create method of the table object which accepts a block on which you can define all the columns.
```swift
try! db.run(unimportants.create() { t in
        t.column(id, primaryKey: .autoincrement)
        t.column(email, unique: true)
        t.column(balance)
        t.column(verified)
        t.column(city)
})
```

As you can see, id, email, balance, verified, and city are Expression objects. Column method accept the expression object and some parameters (like primaryKey and unique).

Other than column method, t object in block after create method accepts other methods, like foreignKey and check methods.
```swift
let pointers = Table("pointers")
try! db.run(pointers.create(ifNotExists: true) { t in
        t.column(id, primaryKey: .autoincrement)
        t.column(name, defaultValue: "No Name")
        t.column(unimportant_id)
        t.column(amount)
        t.foreignKey(unimportant_id, references: unimportants, id, delete: .cascade)
        t.check(amount >= 100)
})
```

foreignKey method accepts the column in the table and the table which it is linked to and the column in that table. Check method accepts the condition to be enforced.

To insert data into the table, we can use insert method of the table. The result of the insert method needs to be sent into run method of database object.
```swift
let insertion1 = unimportants.insert(email <- "user1@mailinator.com", balance <- 30, verified <- true, city <- "Jakarta")
let insertion2 = unimportants.insert(email <- "user2@mailinator.com", balance <- 70, verified <- false, city <- "Kuala Lumpur")

let newId = try! db.run(insertion1)
print(newId)
try! db.run(insertion2)
```

If you want to catch error while running database statement like insertion, you can catch the exception.
```swift
do {
    try db.run(insertion1)
} catch let error {
    print(error)
}
```

To query the table, we can use select, filter, order, limit methods. To join two tables, we can use join method.
```swift
let query = unimportants.select(email, balance, verified)
                        .filter(email == "user1@mailinator.com" || balance >= 0)
                        .order(balance.desc)
                        .limit(3)
            .join(pointers, on: unimportant_id == unimportants[id])
```

select method accepts the columns you want to retrieve. Filter method accepts the condition. Order method accepts the order (asc or desc). limit method accepts the integer. Join accepts table with parameter on accepting the condition between columns that unite both tables.

Then we execute prepare method of db object which gives us an iterator.
```swift
let rows = try! db.prepare(query)
for row in rows {
    print(row[email])
    print(row[balance])
}
```

To get the aggregate value (like count), we use scalar method.
```swift
let count = try! db.scalar(unimportants.select(balance.count))
print(count)
```

To update the row in the table, we use update method. But first, we have to retrieve the row with filter method of the table object.
```swift
let row1 = unimportants.filter(id == 2)
let update1 = row1.update(email <- "user2-updated@mailinator.com")
try! db.run(update1)
```

To delete the row in the table, we use delete method.
```swift
let delete1 = row1.delete()
try! db.run(delete1)
```

To rename the table, we use rename method of the table object.
```swift
let pointers2 = Table("pointers_renamed")
let rename1 = pointers.rename(pointers2)
try! db.run(rename1)
```

Sqlite3 does not have date data type. We can use String to save date and use date type expression when dealing with the column.
```swift
let created_at_date = Expression<Date>("created_at")
```

If the column accepts null value, we use optional Expression.
```swift
let created_at = Expression<String?>("created_at")
```

We add column using addColumn method of the table object.
```swift
let addColumn1 = pointers2.addColumn(created_at)
try! db.run(addColumn1)
```

We use String Expression not Date Expression because we use String column to save the date.

We use transaction method of the database object if we want run many transactions in atomic way.
```swift
try! db.transaction {
    let insertion4 = pointers2.insert(amount <- 102, unimportant_id <- newId, created_at_date <- Date())
    try! db.run(insertion4)
    let insertion5 = pointers2.insert(amount <- 110, unimportant_id <- newId, created_at_date <- Date(timeIntervalSince1970: 100000))
    try! db.run(insertion5)
}
```

To add index, we use createIndex method of the table object.
```swift
let addIndex1 = pointers2.createIndex(amount)
try! db.run(addIndex1)
```

We can use prepare statement to prevent SQL injection attempt.
```swift
let insertion7 = "insert into pointers_renamed (amount, unimportant_id) values (?, ?)"
let stmt = try! db.prepare(insertion7)
try! stmt.run(200, newId)
print(db.changes)
```

To drop the table, we use drop method of the table object.
```swift
let drop1 = pointers2.drop()
try! db.run(drop1)
```

If we run the application, we wil get this output.
```
1
UNIQUE constraint failed: unimportants.email (code: 19)
user1@mailinator.com
30.0
2
1
```

# Exercises

1. Create a table view that lists data from table in sqlite database. You can click it and you will be redirected to another controller. There, you can edit the data. You can save the data to the database.