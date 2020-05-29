import UIKit

class MyView: UIView {
    
    var height = 1.0
    var width = 1.0
    
    override var intrinsicContentSize: CGSize {
        return CGSize(width: width, height: height)
    }

}
