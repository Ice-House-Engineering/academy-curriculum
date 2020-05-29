import Foundation


struct Menu : Decodable {
    let id : String
    let value : String
    let popup : PopUp
    let created : Date
}
