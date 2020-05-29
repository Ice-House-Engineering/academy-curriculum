//
//  HelloTesting1Tests.swift
//  HelloTesting1Tests
//
//  Created by arjuna sky kok on 25/3/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import XCTest
@testable import HelloTesting1

class HelloTesting1Tests: XCTestCase {
    
    var viewController : ViewController!

    override func setUp() {
        viewController = ViewController()
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        let result = viewController.calculate(2, 3)
        XCTAssertEqual(result, 5, "should be 5")
    }
    
    func test_Button() {
        let viewController = ViewController()
        viewController.loadViewIfNeeded()
        XCTAssertNotNil(viewController.button)
        
        XCTAssertEqual(viewController.label.text, "Day", "should be Day")
        viewController.button.sendActions(for: .touchUpInside)
        XCTAssertEqual(viewController.label.text, "Night", "should be Night")
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
