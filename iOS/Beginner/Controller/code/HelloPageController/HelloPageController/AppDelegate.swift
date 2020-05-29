//
//  AppDelegate.swift
//  HelloPageController
//
//  Created by arjuna sky kok on 23/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, UIPageViewControllerDataSource {
    
    var window: UIWindow?
    let strings : [String] = ["Bitcoin", "Ethereum", "Monero", "Ripple"]

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        self.window = self.window ?? UIWindow()
        let pvc = UIPageViewController(transitionStyle: .scroll, navigationOrientation: .horizontal, options: nil)
        //let pvc = UIPageViewController(transitionStyle: .pageCurl, navigationOrientation: .horizontal, options: nil)
        let controller = SinglePageViewController(self.strings[0])
        pvc.setViewControllers([controller], direction: .forward, animated: true, completion: nil)
        pvc.dataSource = self
        self.window!.rootViewController = pvc
        self.window!.makeKeyAndVisible()
        return true
    }

    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        let labelString = (viewController as! SinglePageViewController).string
        let index = self.strings.firstIndex(of: labelString!)! - 1
        if index < 0 {
            return nil
        }
        let controller = SinglePageViewController(self.strings[index])
        return controller
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        let labelString = (viewController as! SinglePageViewController).string
        let index = self.strings.firstIndex(of: labelString!)! + 1
        if index == self.strings.count {
            return nil
        }
        let controller = SinglePageViewController(self.strings[index])
        return controller
    }
    
    func presentationCount(for pageViewController: UIPageViewController) -> Int {
        return self.strings.count
    }
    
    func presentationIndex(for pageViewController: UIPageViewController) -> Int {
        let controller = pageViewController.viewControllers![0] as! SinglePageViewController
        let string = controller.string!
        return self.strings.firstIndex(of:string)!
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

