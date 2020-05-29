import Foundation


struct MenuItem : Decodable {
    let valueKey : String
    let onclickKey : String
    
    enum CodingKeys : String, CodingKey {
        case valueKey = "value"
        case onclickKey = "onclick"
    }
    
}
