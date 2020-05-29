//
//  HelloInternationalization3UITests.swift
//  HelloInternationalization3UITests
//
//  Created by arjuna sky kok on 13/09/19.
//  Copyright © 2019 arjuna sky kok. All rights reserved.
//

import XCTest

class HelloInternationalization3UITests: XCTestCase {

    var app: XCUIApplication!
    
    override func setUp() {
        continueAfterFailure = false
        
        app = XCUIApplication()
        app.launch()
        
    }
    
    func testButtonSaveScreenshot() {
        var screenshot = XCUIScreen.main.screenshot()
        var attachment = XCTAttachment(screenshot: screenshot)
        attachment.lifetime = .keepAlways
        add(attachment)
        
        app.buttons["button"].tap()
        
        screenshot = XCUIScreen.main.screenshot()
        attachment = XCTAttachment(screenshot: screenshot)
        attachment.lifetime = .keepAlways
        add(attachment)
    }
    
    func testButtonWithAccessibilityIdentifier() {
        app.buttons["button"].tap()
        let helloLabel = app.staticTexts["hello"]
        XCTAssertTrue(helloLabel.exists)
    }
    
    func testButton() {
        app.buttons["Button"].tap()
        let helloLabel = app.staticTexts["Hello"]
        XCTAssertTrue(helloLabel.exists)
    }
    
    override func tearDown() {
    }

}
