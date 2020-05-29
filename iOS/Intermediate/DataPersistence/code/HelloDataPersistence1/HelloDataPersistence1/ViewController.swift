import UIKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // filemanager/searchpathdirectory
        // filemanager/searchpathdomainmask
        // filemanager/1407693-url
        do {
            let fm = FileManager.default
            
            let docsurl = try fm.url(for: .documentDirectory,
                                     in: .userDomainMask, appropriateFor: nil, create: false)
            print("Document directory: ", docsurl)
            print("Document directory exists? ", fm.fileExists(atPath: docsurl.path))
            
            let dirs = try fm.contentsOfDirectory(at: docsurl, includingPropertiesForKeys: nil)
            try dirs.forEach {
                try fm.removeItem(atPath: $0.path)
            }
            
            let folderName = "NewFolder"
            let newFolder = docsurl.appendingPathComponent(folderName)
            if fm.fileExists(atPath: newFolder.path) {
                try fm.removeItem(atPath: newFolder.path)
            }
            try fm.createDirectory(at: newFolder, withIntermediateDirectories: true)
            print("NewFolder exists? ", fm.fileExists(atPath: newFolder.path))
            
            let newFileName = "NewFile"
            let newFile = newFolder.appendingPathComponent(newFileName)
            let bytes: [UInt8] = [ 100, 0b1111_1111, 0xFF, 89]
            let data = Data(bytes)
            fm.createFile(atPath: newFile.path, contents: data)
            print("NewFile exists? ", fm.fileExists(atPath: newFile.path))
            
            let newFileName2 = "NewFile2"
            let newFile2 = newFolder.appendingPathComponent(newFileName2)
            if fm.fileExists(atPath: newFile2.path) {
                try fm.removeItem(atPath: newFile2.path)
            }
            try fm.copyItem(at: newFile, to: newFile2)
            print("NewFile2 exists? ", fm.fileExists(atPath: newFile2.path))
            
            let newFileTextName = "NewFileText"
            let newFileText = newFolder.appendingPathComponent(newFileTextName)
            let text = "Hello World\nSecond line"
            let dataText = Data(text.utf8)
            fm.createFile(atPath: newFileText.path, contents: dataText)
            
            let textContent = try String(contentsOf: newFileText, encoding: .utf8)
            print(textContent)
            
            let arr = try fm.contentsOfDirectory(at: docsurl, includingPropertiesForKeys: nil)
            print("Files inside Document directory:")
            arr.forEach {
                print($0.lastPathComponent)
            }
            
            let enumerators = fm.enumerator(at: docsurl, includingPropertiesForKeys: nil)
            print("Files inside directory (nested):")
            if enumerators != nil {
                for fileURL in enumerators! {
                    print(fileURL)
                }
            }
            
            let temporaryDirectory = try fm.url(for: .itemReplacementDirectory,
                                                in: .userDomainMask,
                                                appropriateFor: newFolder,
                                                create: true)
            print("New temporary directory: ", temporaryDirectory)
            
            
        } catch {
            print("catch \(error)")
        }
    }
    
    
}

