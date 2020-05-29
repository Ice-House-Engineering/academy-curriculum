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
