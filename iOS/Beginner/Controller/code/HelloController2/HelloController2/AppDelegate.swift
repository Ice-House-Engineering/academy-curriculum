//
//  AppDelegate.swift
//  HelloController2
//
//  Created by arjuna sky kok on 22/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, UITabBarControllerDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        self.window = self.window ?? UIWindow()
        let tab1 = TabItem1ViewController()
        tab1.tabBarItem = UITabBarItem(tabBarSystemItem: .contacts, tag: 0)
        let tab2 = TabItem2ViewController()
        tab2.tabBarItem = UITabBarItem(title: "Game", image: UIImage(named: "football")!, tag: 1)
        let tab3 = TabItem3ViewController()
        tab3.tabBarItem = UITabBarItem(tabBarSystemItem: .bookmarks, tag: 2)
        let tabBarController = UITabBarController()
        tabBarController.delegate = self
        tabBarController.viewControllers = [tab1, tab2, tab3]
        tabBarController.selectedIndex = 0
        self.window!.rootViewController = tabBarController
        self.window!.makeKeyAndVisible()
        return true
    }
    
    func tabBarController(_ tabBarController: UITabBarController, didSelect viewController: UIViewController) {
        print(viewController.tabBarItem.tag)
    }

    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

