//
//  ViewController.swift
//  HelloControl2
//
//  Created by arjuna sky kok on 16/2/19.
//  Copyright © 2019 PT. Langit Biru Arjuna. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UISearchBarDelegate {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let searchBar = UISearchBar(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        searchBar.prompt = "Prompt"
        searchBar.searchFieldBackgroundPositionAdjustment = UIOffset(horizontal: 0, vertical: 20)
        searchBar.placeholder = "Placeholder"
        searchBar.barStyle = .black
        searchBar.barTintColor = .yellow
        searchBar.showsSearchResultsButton = true
        searchBar.setImage(UIImage(named: "coffee")!, for: .search, state: .normal)
        searchBar.showsScopeBar = true
        searchBar.scopeButtonTitles = ["Bitcoin", "Ethereum", "Monero"]
        searchBar.translatesAutoresizingMaskIntoConstraints = false
        searchBar.delegate = self
        let anchor_searchBar_1 = searchBar.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor)
        let anchor_searchBar_2 = searchBar.leadingAnchor.constraint(equalTo: self.view.leadingAnchor)
        let anchor_searchBar_3 = searchBar.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        self.view.addSubview(searchBar)
        NSLayoutConstraint.activate([anchor_searchBar_1, anchor_searchBar_2, anchor_searchBar_3])
        
        let switchUi = UISwitch(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        switchUi.onTintColor = .red
        switchUi.tintColor = .yellow
        switchUi.thumbTintColor = .blue
        switchUi.setOn(true, animated: false)
        switchUi.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(switchUi)
        let anchor_switchUi_1 = switchUi.topAnchor.constraint(equalTo: searchBar.bottomAnchor, constant: 20)
        let anchor_switchUi_2 = switchUi.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_switchUi_1, anchor_switchUi_2])
        switchUi.addTarget(self, action: #selector(switchValueDidChange(_:)), for: .valueChanged)
        
        let stepper = UIStepper(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        stepper.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(stepper)
        let anchor_stepper_1 = stepper.topAnchor.constraint(equalTo: switchUi.bottomAnchor, constant: 20)
        let anchor_stepper_2 = stepper.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_stepper_1, anchor_stepper_2])
        stepper.addTarget(self, action: #selector(stepperValueDidChange(_:)), for: .valueChanged)
        
        let stepper2 = UIStepper(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        stepper2.setDecrementImage(UIImage(named: "chevron-sign-left")!, for: .normal)
        stepper2.setIncrementImage(UIImage(named: "chevron-sign-right")!, for: .normal)
        stepper2.setDividerImage(UIImage(named: "vertical-ellipsis")!, forLeftSegmentState: .normal, rightSegmentState: .normal)
        stepper2.setBackgroundImage(UIImage(named: "tent")!, for: .normal)
        stepper2.minimumValue = 1
        stepper2.maximumValue = 10
        stepper2.value = 5
        stepper2.stepValue = 1
        stepper2.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(stepper2)
        let anchor_stepper_3 = stepper2.topAnchor.constraint(equalTo: stepper.bottomAnchor, constant: 20)
        let anchor_stepper_4 = stepper2.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_stepper_3, anchor_stepper_4])
        stepper2.addTarget(self, action: #selector(stepperValueDidChange(_:)), for: .valueChanged)
        
        let pageControl = UIPageControl(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        pageControl.numberOfPages = 4
        pageControl.currentPage = 2
        pageControl.pageIndicatorTintColor = .yellow
        pageControl.currentPageIndicatorTintColor = .blue
        pageControl.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(pageControl)
        let anchor_pageControl_1 = pageControl.topAnchor.constraint(equalTo: stepper2.bottomAnchor, constant: 20)
        let anchor_pageControl_2 = pageControl.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_pageControl_1, anchor_pageControl_2])
        pageControl.addTarget(self, action: #selector(pageControlValueDidChange(_:)), for: .valueChanged)
        
        let datePicker = UIDatePicker(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        datePicker.datePickerMode = .date
        var dateComponents = DateComponents()
        dateComponents.year = 2016
        dateComponents.month = 11
        dateComponents.day = 28
        let userCalendar = Calendar.current
        let dateTime = userCalendar.date(from: dateComponents)!
        datePicker.minimumDate = Date(timeIntervalSinceReferenceDate: dateTime.timeIntervalSinceReferenceDate)
        datePicker.timeZone = TimeZone(identifier: "Asia/Jakarta")!
        datePicker.locale = Locale(identifier: "id")
        datePicker.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(datePicker)
        let anchor_datePicker_1 = datePicker.topAnchor.constraint(equalTo: pageControl.bottomAnchor, constant: 20)
        let anchor_datePicker_2 = datePicker.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_datePicker_1, anchor_datePicker_2])
        datePicker.addTarget(self, action: #selector(datePickerValueDidChange(_:)), for: .valueChanged)
        
        let slider = UISlider(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        slider.minimumValue = 0
        slider.maximumValue = 100
        slider.setValue(40, animated: false)
        slider.minimumTrackTintColor = .blue
        slider.maximumTrackTintColor = .yellow
        slider.setThumbImage(UIImage(named: "space-shuttle")!, for: .normal)
        slider.setThumbImage(UIImage(named: "rocket")!, for: .highlighted)
        slider.minimumValueImage = UIImage(named: "earth")!
        slider.maximumValueImage = UIImage(named: "planets")!
        slider.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(slider)
        let anchor_slider_1 = slider.topAnchor.constraint(equalTo: datePicker.bottomAnchor, constant: 20)
        let anchor_slider_2 = slider.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        let anchor_slider_3 = slider.leadingAnchor.constraint(equalTo: self.view.leadingAnchor)
        let anchor_slider_4 = slider.trailingAnchor.constraint(equalTo: self.view.trailingAnchor)
        NSLayoutConstraint.activate([anchor_slider_1, anchor_slider_2, anchor_slider_3, anchor_slider_4])
        slider.addTarget(self, action: #selector(sliderValueDidChange(_:)), for: .valueChanged)
        
        let segmentedControl = UISegmentedControl(items: [UIImage(named: "earth")!.withRenderingMode(.alwaysOriginal), "Mars", "Venus"])
        segmentedControl.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(segmentedControl)
        let anchor_segmentedControl_1 = segmentedControl.topAnchor.constraint(equalTo: slider.bottomAnchor, constant: 20)
        let anchor_segmentedControl_2 = segmentedControl.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        NSLayoutConstraint.activate([anchor_segmentedControl_1, anchor_segmentedControl_2])
        segmentedControl.addTarget(self, action: #selector(segmentedControlValueDidChange(_:)), for: .valueChanged)
    }
    
    @objc func segmentedControlValueDidChange(_ sender: UISegmentedControl) {
        print(sender.selectedSegmentIndex)
    }
    
    @objc func sliderValueDidChange(_ sender: UISlider) {
        print(sender.value)
    }
    
    @objc func datePickerValueDidChange(_ sender: UIDatePicker) {
        print(sender.date)
    }
    
    @objc func pageControlValueDidChange(_ sender: UIPageControl) {
        print(sender.currentPage)
    }
    
    @objc func stepperValueDidChange(_ sender: UIStepper) {
        print(sender.value)
    }
    
    @objc func switchValueDidChange(_ sender: UISwitch) {
        if (sender.isOn) {
            print("is on")
        } else {
            print("is off")
        }
    }

    func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        print("Mulai")
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        print("text did change")
        print(searchText)
    }
    
    func searchBar(_ searchBar: UISearchBar, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        print("should change")
        print(range)
        print(text)
        return true
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        print(searchBar.text!)
    }
    
    func searchBar(_ searchBar: UISearchBar, selectedScopeButtonIndexDidChange selectedScope: Int) {
        print(selectedScope)
    }

    func searchBarResultsListButtonClicked(_ searchBar: UISearchBar) {
        print("result list")
    }

}

