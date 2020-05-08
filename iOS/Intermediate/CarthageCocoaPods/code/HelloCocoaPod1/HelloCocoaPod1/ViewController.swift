import UIKit
import Alamofire


class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        AF.request ("https://httpbin.org/get").responseJSON { response in
            print("Request: \(String(describing: response.request))")
        }
    }

}
