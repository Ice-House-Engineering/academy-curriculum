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
