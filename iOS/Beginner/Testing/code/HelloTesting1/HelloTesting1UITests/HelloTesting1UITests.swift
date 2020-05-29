//
//  HelloTesting1UITests.swift
//  HelloTesting1UITests
//
//  Created by arjuna sky kok on 25/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import XCTest

class HelloTesting1UITests: XCTestCase {
    
    var app: XCUIApplication!

    override func setUp() {
        continueAfterFailure = false

        app = XCUIApplication()
        app.launch()

    }

    override func tearDown() {
    }

    func testExample() {
        app.pickerWheels.element.adjust(toPickerWheelValue: "Litecoin")
        app.sliders["slider"].adjust(toNormalizedSliderPosition: 0.8)
        
        var dayLabel = app.staticTexts["Day"]
        var nightLabel = app.staticTexts["Night"]
        XCTAssertFalse(nightLabel.exists)
        XCTAssertTrue(dayLabel.exists)
        app.buttons["primaryButton"].tap()
        dayLabel = app.staticTexts["Day"]
        nightLabel = app.staticTexts["Night"]
        XCTAssertTrue(nightLabel.exists)
        XCTAssertFalse(dayLabel.exists)
        
        app.buttons["Presenting Button"].tap()
        app.textFields["primaryTextField"].tap()
        app.textFields["primaryTextField"].typeText("hello")
        app.buttons["Button"].tap()
    }

}
