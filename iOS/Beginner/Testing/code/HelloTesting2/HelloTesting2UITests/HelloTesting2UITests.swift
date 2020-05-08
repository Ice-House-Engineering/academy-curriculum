//
//  HelloTesting2UITests.swift
//  HelloTesting2UITests
//
//  Created by arjuna sky kok on 25/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import XCTest

class HelloTesting2UITests: XCTestCase {
    
    var app: XCUIApplication!

    override func setUp() {
        continueAfterFailure = false

        app = XCUIApplication()
        app.launch()
    }

    override func tearDown() {
    }

    func testExample() {
        app.navigationBars.buttons.element(boundBy: 1).tap()
        app.navigationBars.buttons.element(boundBy: 0).tap()
        let firstCell = app.tables.cells.element(boundBy: 0)
        firstCell.swipeLeft()
        firstCell.buttons.element(boundBy: 0).tap()
        firstCell.buttons.element(boundBy: 1).tap()
        app.navigationBars.buttons.element(boundBy: 0).tap()
        let cell = app.tables.cells.element(boundBy: 2)
        cell.tap()
        let ethereumLabel = app.staticTexts["Ethereum"]
        let predicate = NSPredicate(format: "exists == false")
        let exp = expectation(for: predicate, evaluatedWith: ethereumLabel, handler: nil)
        XCTWaiter().wait(for: [exp], timeout: 5)
        let label = app.staticTexts["Bitcoin Cash"]
        XCTAssertTrue(label.exists)
    }

}
