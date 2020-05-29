# Keychain

We should not store sensitive and private information to a normal storage. It is better if we store them to Keychain.

Create a new empty view application. Name it HelloKeychain1.

Edit ViewController.swift.

Create a variable inside ViewController class.
```swift
    private let server = "server1"
```

We create methods to insert data into keychain, read data from keychain, updata data in keychain, and delete data from keychain.
```swift
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        saveIntoKeychain()
        readFromKeychain()
        updateToKeychain()
        readFromKeychain()
        deleteFromKeychain()
        readFromKeychain()
    }
```

Create a method to insert data into keychain.
```swift
    func saveIntoKeychain() {
        let account = "email1@domain1.com"
        let token = "token1".data(using: .utf8)!
        
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrAccount as String: account,
                                    kSecAttrServer as String: self.server,
                                    kSecValueData as String: token]
        let status = SecItemAdd(query as CFDictionary, nil)
        
        guard status == errSecSuccess else { print("Error in saveIntoKeychain"); return; }
    }
```

In this case, we assume we want to save the token from JSON Web Token authentication that we receive from the server. The token must be converted to bytes.
```swift
    let account = "email1@domain1.com"
    let token = "token1".data(using: .utf8)!
```

Then we create a dictionary of our data.
```swift
    let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                kSecAttrAccount as String: account,
                                kSecAttrServer as String: self.server,
                                kSecValueData as String: token]
```

We use internet password class of data because we want to save the server of the token. But if we don’t need that, we can use generic password class of data and omit the server like this:
```swift
    let query: [String: Any] = [kSecClass as String: kSecClassGenericPassword,
                                kSecAttrAccount as String: account,
                                kSecValueData as String: token]
```

Then we add this dictionary of the data to the keychain using “SecItemAdd” method.
```swift
    let status = SecItemAdd(query as CFDictionary, nil)
```

The second parameter of this method is the result of the operation. For adding item,we don’t need it.

We can check whether the method is successful or not.
```swift
guard status == errSecSuccess else { print("Error in saveIntoKeychain"); return; }
```

Create a method to read data from the keychain.
```swift
    func readFromKeychain() {
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: server,
                                    kSecMatchLimit as String: kSecMatchLimitOne,
                                    kSecReturnAttributes as String: true,
                                    kSecReturnData as String: true]
        
        var item: CFTypeRef?
        let status = SecItemCopyMatching(query as CFDictionary, &item)
        
        guard status == errSecSuccess else { print("Error in readFromKeychain"); return; }
        guard let existingItem = item as? [String: Any],
        let passwordData = existingItem[kSecValueData as String] as? Data,
            let password = String(data: passwordData, encoding: .utf8),
            let account = existingItem[kSecAttrAccount as String] as? String
            else {
                print("Error in readFromKeychain"); return;
        }
        print("readFromKeychain: \(password)")
        print("readFromKeychain: \(account)")
    }
```

Create a dictionary of the query data.
```swift
    let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                kSecAttrServer as String: server,
                                kSecMatchLimit as String: kSecMatchLimitOne,
                                kSecReturnAttributes as String: true,
                                kSecReturnData as String: true]
```

We search whether there is data in the keychain for specific server. We limit it just for one result. We want to get the attributes (kSecReturnAttributes) which contains the name of the account and the data (kSecReturnData) which contains the token.

To retrieve the data, we use “SecItemCopyMatching” method.
```swift
    var item: CFTypeRef?
    let status = SecItemCopyMatching(query as CFDictionary, &item)
```

In this case, we send “CFTypeRef” object’s reference to hold the data we are looking for.

Then we can get the data from “item” variable.
```swift
    guard let existingItem = item as? [String: Any],
    let passwordData = existingItem[kSecValueData as String] as? Data,
        let password = String(data: passwordData, encoding: .utf8),
        let account = existingItem[kSecAttrAccount as String] as? String
        else {
            print("Error in readFromKeychain"); return;
    }
```

Create a method to update data in the keychain.
```swift
    func updateToKeychain() {
        let account = "email1@domain1.com"
        let token = "token2".data(using: .utf8)!
        
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: self.server]
        let attributes: [String: Any] = [kSecAttrAccount as String: account,
                                         kSecValueData as String: token]
        let status = SecItemUpdate(query as CFDictionary, attributes as CFDictionary)
        
        guard status == errSecSuccess else { print("Error in updateToKeychain"); return; }
    }
```

We get the data which we want to update based on the server attribute.
```swift
    let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                kSecAttrServer as String: self.server]
```

We create a dictionary to hold the new data for the account and the token.
```swift
    let attributes: [String: Any] = [kSecAttrAccount as String: account,
                                        kSecValueData as String: token]
```

We update the data in the keychain with “SecItemUpdate” method.
```swift
    let status = SecItemUpdate(query as CFDictionary, attributes as CFDictionary)
```

Create a method to delete the data from the keychain.
```swift
    func deleteFromKeychain() {
        let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                    kSecAttrServer as String: server]
        let status = SecItemDelete(query as CFDictionary)
        
        guard status == errSecSuccess else { print("Error in deleteFromKeychain"); return; }
    }
```

We get the data which we want to update based on the server attribute.
```swift
    let query: [String: Any] = [kSecClass as String: kSecClassInternetPassword,
                                kSecAttrServer as String: server]
```

We delete the data from the keychain with “SecItemDelete” method.
```swift
    let status = SecItemDelete(query as CFDictionary)
```

If we ran the application, we would get this output.
```
readFromKeychain: token1
readFromKeychain: email1@domain1.com
readFromKeychain: token2
readFromKeychain: email1@domain1.com
Error in readFromKeychain
```