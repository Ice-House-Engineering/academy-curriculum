//
//  ViewController.swift
//  HelloInternationalization1
//
//  Created by arjuna sky kok on 09/09/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // NSLocalizedString
        let helloWorld = NSLocalizedString("Hello World", comment: "Comment for 'Hello World' string")
        print(helloWorld)
        
        // NSLocalizedString with tableName
        let nurse = NSLocalizedString("Nurse", tableName: "MedicalLocalizable", value: "Student", comment: "Comment for 'Nurse' string")
        print(nurse)
        
        // String.localizedStringWithFormat
        let bill = "Bill Gates"
        let number = 1
        let billLocalizedString = NSLocalizedString("%@ is the successful businessman number %d.", comment: "Comment for Bill Gates")
        let billString = String.localizedStringWithFormat(billLocalizedString, bill, number)
        print(billString)
        
        // DateFormatter
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .long
        dateFormatter.timeStyle = .medium
        
        let date = Date(timeIntervalSinceNow: 0)
        let dateString = dateFormatter.string(from: date)
        print(dateString)
        
        // DateIntervalFormatter
        let dateIntervalFormatter = DateIntervalFormatter()
        dateIntervalFormatter.dateStyle = .full
        
        let endDate = Date(timeInterval: 86400, since: date)
        let dateIntervalString = dateIntervalFormatter.string(from: date, to: endDate)
        print(dateIntervalString)
        
        // NumberFormatter
        let numberFormatter = NumberFormatter()
        numberFormatter.numberStyle = .decimal
        
        let num = 200456 as NSNumber
        let numString = numberFormatter.string(from: num)!
        print(numString)
        
        numberFormatter.numberStyle = .currency
        numberFormatter.currencyCode = "Rp "
        let numCurrencyString = numberFormatter.string(from: num)!
        print(numCurrencyString)
        
        // Pluralization
        let bookLocalizedString = NSLocalizedString("Book sentence", comment: "")
        let noBookString = String.localizedStringWithFormat(bookLocalizedString, 0)
        let oneBookString = String.localizedStringWithFormat(bookLocalizedString, 1)
        let manyBookString = String.localizedStringWithFormat(bookLocalizedString, 5)
        print(noBookString)
        print(oneBookString)
        print(manyBookString)
        
        // Gender
        let genderLocalizedString = NSLocalizedString("Gender sentence", comment: "")
        let hisGenderString = String.localizedStringWithFormat(genderLocalizedString, 0)
        let herGenderString = String.localizedStringWithFormat(genderLocalizedString, 1)
        let theirGenderString = String.localizedStringWithFormat(genderLocalizedString, 2)
        print(hisGenderString)
        print(herGenderString)
        print(theirGenderString)
        
        // Getting information about localization
        let currentLocale = Locale.current
        print(currentLocale)
        print(currentLocale.identifier)
        print(currentLocale.languageCode!)
        print(currentLocale.regionCode!)
        print(currentLocale.currencyCode!)
        print(currentLocale.currencySymbol!)
        print(currentLocale.localizedString(forIdentifier: currentLocale.identifier)!)
        print(Locale.preferredLanguages)
        
    }


}

