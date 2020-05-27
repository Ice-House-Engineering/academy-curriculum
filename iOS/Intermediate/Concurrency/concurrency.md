# Concurrency

We can run many tasks at the same time in iOS. We can put some tasks in the background threads so those tasks will not interrupt the main thread. There are many ways to accomplish this goal. We can create and manipulate threads manually, but that is not recommended. Usually we use Grand Central Dispatch and Operations.

## Grand Central Dispatch

Create a new single view application project. Name it HelloConcurrency1.

Edit ViewController.swift.
```swift
Create a new method. Name it playWithDispatchQueueGlobal.

    func playWithDispatchQueueGlobal() {
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async {
            print("Inside DispatchQueue.global")
        }
        print("In UI thread (playWithDispatchQueueGlobal)")
    }
```

This is the easiest way to run the task in background thread. Dispatch queue is a link between the task and the pool of threads. Remember that we want to run In this example, we get the global dispatch queue with default quality of service. Using global dispatch queue, we can run the task as a closure with “async” method.

There is another type of dispatch queue. Create a new method. Name it playWithCustomDispatchQueue.
```swift
    func playWithCustomDispatchQueue() {
        let dispatchQueue = DispatchQueue.init(label: "co.id.icehouse.helloconcurrency1", qos: .userInitiated, attributes: .concurrent, autoreleaseFrequency: .inherit, target: .global())
        dispatchQueue.async {
            print("Inside custom DispatchQueue")
        }
        print("In UI Thread (playWithCustomDispatchQueue)")
    }
```

Here, we create a custom dispatch queue. We can name it with “label” parameter. We use “userInitiated” quality of service. We use “concurrent” attribute. We use “inherit” autorelease frequency and “main” target.

The quality of service determines how important our task is in background thread. The more important our task is, the higher priority in the background thread our task is.

There are 6 types of quality of service: “userInteractive”, “userInitiated”, “default”, “utility”, “background”, “unspecified”.

The “userInitiated” has higher priority than the “background” priority.

To see the explanation of all types of quality of service, we can check the link below.

https://developer.apple.com/documentation/dispatch/dispatchqos/qosclass

In this example, we use “concurrent” attributes. But there is “initiallyInactive” attributes. If we use “initiallyInactive” attributes, the task will not be run immediately. We must run it with “activate” method of the dispatch queue object.

We should not worry about “autoreleaseFrequency” parameter.

The “target” parameter is the dispatch queue’s target queue on where this task will be executed. If we omit this parameter, the system will provide the queue for us.

We can not update User Interface in the background thread. We must use the User Interface thread. We can get the User Interface thread with DispatchQueue.main.

Create a new method. Name it playWithDispatchQueueMain.
```swift
    func playWithDispatchQueueMain() {
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async {
            print("Inside DispatchQueue.global")
            // self.createUILabel() -> will crash the app
            
            DispatchQueue.main.async { [weak self] in
                if self == nil {
                    return
                }
                
                print("In UI Thread (playWithDispatchQueueMain)")
                // Must be executed inside UI thread (or DispatchQueue.main)
                self!.createUILabel()
            }
        }
        Thread.sleep(forTimeInterval: 1)
    }

    func createUILabel() {
        let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
        let view = UIView(frame: viewFrame)
        view.backgroundColor = .blue
        self.view.addSubview(view)
    }
```

We can only touch User Interface code in “async” method of DispatchQueue.main. If we put User Interface code in global dispatch queue, our application will crash.

Instead of a closure block, we can submit dispatch work item to “async” method.

### DispatchWorkItem

Create a new method. Name it playWithDispatchWorkItem.
```swift
    func playWithDispatchWorkItem() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem = DispatchWorkItem {
            print("Inside DispatchWorkItem")
        }
        dispatchQueue.async(execute: dispatchWorkItem)
    }
```

When we hit “async” method, we run the background task immediately, but we can postpone with “asyncAfter” method.

Create a new method. Name it playWithAfterDeadline.
```swift
    func playWithAfterDeadline() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem = DispatchWorkItem {
            print("Inside DispatchWorkItem with deadline")
        }
        dispatchQueue.asyncAfter(deadline: .init(uptimeNanoseconds: 1000000000), execute: dispatchWorkItem)
        print("Inside playWithAfterDeadline")
    }
```

We use “deadline” parameter to deplay the background task.

We can create continuation between dispatch work items. So we want to execute dispatch work item A after executing dispatch work item B.

Create a new method. Name it playWithNotify.
```swift
    func playWithNotify() {
        let dispatchQueue = DispatchQueue.global()
        let dispatchWorkItem1 = DispatchWorkItem {
            print("Inside DispatchWorkItem1")
        }
        let dispatchWorkItem2 = DispatchWorkItem {
            print("Inside DispatchWorkItem2")
        }
        let dispatchWorkItem3 = DispatchWorkItem {
            print("Inside DispatchWorkItem3")
        }
        dispatchWorkItem2.notify(queue: dispatchQueue, execute: dispatchWorkItem3)
        dispatchWorkItem1.notify(queue: dispatchQueue, execute: dispatchWorkItem2)
        dispatchQueue.async(execute: dispatchWorkItem1)
    }
```

Take a look at this particular line.
```swift
dispatchWorkItem2.notify(queue: dispatchQueue, execute: dispatchWorkItem3)
```

The line above means we will execute “dispatchWorkItem3” after finishing “dispatchWorkItem2”.

### concurrentPerform

We can also execute a same task many times concurrently.

Create a new method. Name it playWithConcurrentPerform.
```swift
    func playWithConcurrentPerform() {
        DispatchQueue.concurrentPerform(iterations: 5, execute: { index in
            print("Iteration: \(index)")
        })
    }
```

We execute the task with “concurrentPerform” method. The parameter “iterations” means we run the task as many as the value we put in “iterations” parameter. The parameter “execute” is the task we want to run. These tasks will be run concurrently not sequentially.

In viewDidLoad method, add these lines:
```swift
        playWithDispatchQueueGlobal()
        Thread.sleep(forTimeInterval: 1)
        playWithCustomDispatchQueue()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchQueueMain()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchWorkItem()
        Thread.sleep(forTimeInterval: 1)
        playWithAfterDeadline()
        Thread.sleep(forTimeInterval: 1)
        playWithNotify()
        Thread.sleep(forTimeInterval: 1)
        playWithConcurrentPerform()
```

You can comment some methods to learn and focus for some specific methods.

Run the application and we will get this output:
```
In UI thread (playWithDispatchQueueGlobal)
Inside DispatchQueue.global
In UI Thread (playWithCustomDispatchQueue)
Inside custom DispatchQueue
Inside DispatchQueue.global
Inside DispatchWorkItem
Inside playWithAfterDeadline
Inside DispatchWorkItem with deadline
Inside DispatchWorkItem1
Inside DispatchWorkItem2
Inside DispatchWorkItem3
Iteration: 0
Iteration: 4
Iteration: 1
Iteration: 2
Iteration: 3
In UI Thread (playWithDispatchQueueMain)
```

Your output could be different than the one shown above because playing with threads has some randomness thrown around.

### DispatchSpecificKey

There are other concepts that need to be explained so let’s create a single view application project. Name it HelloConcurrency2.

We have a dictionary that we can store data from a dispatch queue.

Edit ViewController.swift.

Create a method. Name it playWithDispatchSpecificKey.
```swift
    func playWithDispatchSpecificKey() {
        let dispatchQueue = DispatchQueue.global()
        let specificKey = DispatchSpecificKey<String>()
        dispatchQueue.sync {
            dispatchQueue.setSpecific(key: specificKey, value: "value1")
        }
        dispatchQueue.async {
            let value = dispatchQueue.getSpecific(key: specificKey)
            print("Value of specific key is \(String(describing: value))")
        }
        Thread.sleep(forTimeInterval: 0.5)
    }
```

To create a key, we can initialize the key with DispatchSpecificKey.
```swift
let specificKey = DispatchSpecificKey<String>()
```

Then we can store the value with that key.
```swift
dispatchQueue.setSpecific(key: specificKey, value: "value1")
```

Notice we store the value inside “sync” method. “sync” means that we execute the task synchronously. Don’t do this inside User Interface thread or main thread.

Later we can get the value from the key with this line below.
```swift
let value = dispatchQueue.getSpecific(key: specificKey)
```

### DispatchGroup

We can group dispatch queues inside a dispatch group. There are some advantages for doing so.

Create a method named playWithDispatchGroup.
```swift
    func playWithDispatchGroup() {
        let dispatchGroup = DispatchGroup()
        let dispatchQueue = DispatchQueue.global()
        dispatchQueue.async(group: dispatchGroup) {
            Thread.sleep(forTimeInterval: 1)
            print("First block in dispatch queue with group")
        }
        dispatchQueue.async(group: dispatchGroup) {
            print("Second block in dispatch queue with group")
        }
        dispatchGroup.wait()
    }
```

Here, we have two dispatch queues. For each dispatch queue, we attach it to the same group. Then with the group, we can wait both dispatch queues with “wait” method.

Sometimes inside the background task, we launch another background task. We don’t want the parent background task teminates before the child background task is finished first.

We can use “enter” and “leave” methods from the group.

Create a new method. Name it playWithDispatchGroupEnterLeave.
```swift
    func playWithDispatchGroupEnterLeave() {
        let dispatchGroup = DispatchGroup()
        let dispatchQueue = DispatchQueue.global()
        
        dispatchQueue.async(group: dispatchGroup) {
            dispatchGroup.enter()
            print("Outside nested dispatch queue")
            
            dispatchQueue.async(group: dispatchGroup) {
                defer { dispatchGroup.leave() }
                
                Thread.sleep(forTimeInterval: 1)
                print("Inside nested dispatch queue")
            }
        }
    }
```

### DispatchSemaphore

Imagine we run 10 background tasks at the same time. But we want to limit only 4 background tasks can be run at the same time. If one of these 4 background tasks is finished, a new background task from the remaining 6 background tasks will be executed.

Create a new method. Name it playWithDispatchSemaphore.
```swift
    func playWithDispatchSemaphore() {
        let dispatchSemaphore = DispatchSemaphore(value: 3)
        let tenTimes = Range(1...10)
        let dispatchQueue = DispatchQueue.global()
        for n in tenTimes {
            dispatchQueue.async {
                dispatchSemaphore.wait()
                defer { dispatchSemaphore.signal() }
                print("Doing thing: \(n)")
                Thread.sleep(forTimeInterval: 1)
                print("Done thing: \(n)")
            }
        }
        Thread.sleep(forTimeInterval: 1)
    }
```

As we can see, we create dispatch semaphore with value “3”. It means only 3 background tasks can be run at the same time.
```swift
let dispatchSemaphore = DispatchSemaphore(value: 3)
```

In the background task closure block, we “ask” the permission from the dispatch semaphore with “wait” method. If there are already 3 background tasks, the background task will wait until there is a free spot.
```swift
dispatchSemaphore.wait()
```

If the background task get an available spot from the dispatch queue, the background task needs to inform the dispatch semaphore that it already finishes the task so the dispatch semaphore can give the slot to other background tasks. The way to do that is “signal” method.
```swift
defer { dispatchSemaphore.signal() }
```

We use “defer” method so we can be sure this method will be executed in the end of background task.

Edit viewDidLoad method. Add these lines of code.
```swift
        playWithDispatchSpecificKey()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchGroup()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchGroupEnterLeave()
        Thread.sleep(forTimeInterval: 1)
        playWithDispatchSemaphore()
```

Run the application and we will get this output.
```
Value of specific key is Optional("value1")
Second block in dispatch queue with group
First block in dispatch queue with group
Outside nested dispatch queue
Inside nested dispatch queue
Doing thing: 1
Doing thing: 2
Doing thing: 3
Done thing: 2
Done thing: 1
Done thing: 3
Doing thing: 4
Doing thing: 6
Doing thing: 5
Done thing: 6
Done thing: 5
Doing thing: 7
Done thing: 4
Doing thing: 8
Doing thing: 9
Done thing: 9
Done thing: 7
Done thing: 8
Doing thing: 10
Done thing: 10
```

Notice there are delays in output.

## Operation

The alternative of Grand Central Dispatch is Operation. Operation is an abstract class. We have to create a subclass of Operation. But Apple has provided a simple concrete Operation subclass implementation for a simple usage, called BlockOperation.

### BlockOperation

Create a new single view iOS application. Name it HelloConcurrency3.

Edit ViewController.swift. Add this code inside viewDidLoad method.
```swift
        print(Date())
        
        var result = 0
        let bo = BlockOperation {
            result = 1
            sleep(1)
        }
        bo.completionBlock = {
            print("Finish!!!")
        }
        for i in (0...10) {
            bo.addExecutionBlock {
                sleep(1)
                print("execution block \(i)")
            }
        }
        bo.start()
        print(result)
        
        print(Date())
```

If we run the application, we would get this output.
```
2019-10-27 10:36:37 +0000
execution block 0
execution block 1
execution block 2
execution block 3
execution block 6
execution block 4
execution block 5
execution block 7
execution block 8
execution block 10
execution block 9
1
2019-10-27 10:36:39 +0000
Finish!!!
```

It took 2 seconds to finish the task.

To create a block operation, we can initialize it with code at the same time.
```swift
        let bo = BlockOperation {
            result = 1
            sleep(1)
        }
```

We haven’t run this operation yet.

We can put some code to be executed when this operation is finished with “completionBlock”.
```swift
        bo.completionBlock = {
            print("Finish!!!")
        }
```

If we want to add another code block after creating BlockOperation object, we can use “addExecutionBlock” method.
```swift
        for i in (0...10) {
            bo.addExecutionBlock {
                sleep(1)
                print("execution block \(i)")
            }
        }
```

Here, we create 11 code block.

To run the BlockOperation object, we call “run” method.
```swift
        bo.start()
```

This is a blocking operation. The execution will stop here until all the execution blocks are finished.

Notice, that the BlockOperation object run all execution blocks concurrently. That’s why it only took 2 seconds to finish all execution blocks instead of 12 seconds.

### Subclassing Operation

Create a new single view application. Name it HelloConcurrency4.

Create a new file inside HelloConcurrency4 folder (the directory which contains ViewController.swift). Name it BasicOperation.swift.
```swift
import Foundation

class BasicOperation : Operation {
    var result: Int = 0
    
    override func main() {
        sleep(1)
        result = 1
    }
    
}
```

There are many methods which we can override. For example: “start”, “main”, “completionBlock”. The “main” method represents the task that our Operation class wants to perform. In this example, we just set the variable “result” to 1.

Edit ViewController.swift. Add this code inside “viewDidLoad” method.
```swift
        let bo = BasicOperation()
        bo.completionBlock = {
            print(bo.result)
        }
        print(bo.result)
        bo.start()
        print("start method from sync operation is blocking")
```

It is same as we run BlockOperation object. We run it with “start” method. “start” method is blocking. If we run this application, we would get this output.
```
0
start method from sync operation is blocking
1
```

### OperationQueue

We can manage many operations with OperationQueue.

Create a new empty view application. Name it HelloConcurrency5.

Edit ViewController.swift. Add this code inside “viewDidLoad” method.
```swift
        // Part 1
        print("OperationQueue 1")
        print(Date())
        let op1 = OperationQueue()
        for i in (0...5) {
            op1.addOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
        }
        for i in (6...10) {
            let o = BlockOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
            o.queuePriority = .veryHigh
            op1.addOperation(o)
        }
        op1.waitUntilAllOperationsAreFinished()
        print(Date())
        print()
        
        // Part 2
        print("OperationQueue 2")
        print(Date())
        let op2 = OperationQueue()
        op2.maxConcurrentOperationCount = 3
        for i in (0...10) {
            op2.addOperation {
                sleep(1)
                print("Queue2: Operation \(i)")
            }
        }
        op2.waitUntilAllOperationsAreFinished()
        print(Date())

        // Part 3
        let op3 = OperationQueue.main
        op3.addOperation {
            let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
            let view = UIView(frame: viewFrame)
            view.backgroundColor = .blue
            self.view.addSubview(view)
        }
```

We add operation to OperationQueue with “addOperation” method.
```swift
            op1.addOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
```

Every time we add an operation to OperationQueue, OperationQueue immediately run its operation. We don’t need to call “start” method manually again.

OperationQueue manage operations based on their queuePriority.
```swift
            let o = BlockOperation {
                sleep(1)
                print("Queue1: Operation \(i)")
            }
            o.queuePriority = .veryHigh
            op1.addOperation(o)
```

We can wait all operations from OperationQueue to be finished with “waitUntilAllOperationsAreFinished” method.

If we want to limit maximum operations that OperationQueue can run concurrently, we can use “maxConcurrentOperationCount” attribute.
```swift
        op2.maxConcurrentOperationCount = 3
```

To manipulate User Interface in iOS, we need to do it in a special thread. We need to use “main” attribute from OperationQueue.
```swift
        let op3 = OperationQueue.main
        op3.addOperation {
            let viewFrame = CGRect(x: 0, y: 0, width: 100, height: 100)
            let view = UIView(frame: viewFrame)
            view.backgroundColor = .blue
            self.view.addSubview(view)
        }
```

If we run the application, we would get this output.
```
OperationQueue 1
2019-10-27 14:36:29 +0000
Queue1: Operation 1
Queue1: Operation 5
Queue1: Operation 3
Queue1: Operation 4
Queue1: Operation 2
Queue1: Operation 0
Queue1: Operation 6
Queue1: Operation 8
Queue1: Operation 7
Queue1: Operation 10
Queue1: Operation 9
2019-10-27 14:36:30 +0000

OperationQueue 2
2019-10-27 14:36:30 +0000
Queue2: Operation 1
Queue2: Operation 0
Queue2: Operation 2
Queue2: Operation 4
Queue2: Operation 3
Queue2: Operation 5
Queue2: Operation 7
Queue2: Operation 6
Queue2: Operation 8
Queue2: Operation 10
Queue2: Operation 9
2019-10-27 14:36:34 +0000
```

Notice for the second part, the output was displayed three lines at a time because we set the maximum concurrent operation count to 3.

We also get the blue rectangle on the top left corner.

### Asynchronous Operation

What we have created are synchronous operations. If we want to create asynchronous operation, here are the steps.

Create a new empty view application. Name it HelloConcurrency6.

At first, we must create a subclass from Operation which is asynchronous.

Create a new file inside HelloConcurrency6 folder: TemplateAsyncOperation.swift.
```swift
import Foundation

class TemplateAsyncOperation: Operation {
    enum State: String {
        case r = "Ready"
        case e = "Executing"
        case f = "Finished"
        fileprivate var key: String {
            return "is" + rawValue
        }
    }
    
    var s = State.r {
        willSet {
            willChangeValue(forKey: newValue.key)
            willChangeValue(forKey: s.key)
        }
        didSet {
            didChangeValue(forKey: oldValue.key)
            didChangeValue(forKey: s.key)
        }
    }
    
    override var isExecuting: Bool { return s == .e }
    
    override var isFinished: Bool { return s == .f }
    
    override var isAsynchronous: Bool { return true }
    
    override func start() {
        if isCancelled {
            s = .f
            return
        }
        main()
        s = .e
    }
    
    override func cancel() {
        s = .f
    }
    
    override var isReady: Bool {
        return super.isReady && s == .r
    }
}
```

At first, we must create an enum of three string representations.
```swift
    enum State: String {
        case r = "Ready"
        case e = "Executing"
        case f = "Finished"
        fileprivate var key: String {
            return "is" + rawValue
        }
    }
```

The name of enum is “State” and it can be anything. The cases can be anything. The variable “key” can have different name. What matters is later inside the class, we must set some variables with “isReady”, “isExecuting”, and “isFinished” strings. We don’t need to use enum if we don’t want to.

Then we create a variable to hold any value from “isReady”, “isExecuting”, and “isFinished”. But we need to implement some observers: “willSet” and “didSet”.
```swift
    var s = State.r {
        willSet {
            willChangeValue(forKey: newValue.key)
            willChangeValue(forKey: s.key)
        }
        didSet {
            didChangeValue(forKey: oldValue.key)
            didChangeValue(forKey: s.key)
        }
    }
```

“willChangeValue” and “didChangeValue” are part of key-value observer compliance.

We need to override some attributes.
```swift
    override var isExecuting: Bool { return s == .e }
    
    override var isFinished: Bool { return s == .f }
    
    override var isAsynchronous: Bool { return true }

    override var isReady: Bool {
        return super.isReady && s == .r
    }
```

The “isReady” attribute needs to incorporate the value from the superclass according to Apple documentation.

Then we can override “start” method.
```swift
    override func start() {
        if isCancelled {
            s = .f
            return
        }
        main()
        s = .e
    }
```

“isCancelled” property comes from superclass. It is set true if we cancel the operation.

Finally we have to override “cancel” method.
```swift
    override func cancel() {
        s = .f
    }
```

In the “start” and “cancel” methods we basically set the state variable “s” to “isExecuting” and “isFinished” strings. We also call “main” method inside “start” method. The “main” method itself will be defined in the subclass of our asynchronous operation.

Let’s create a subclass from our asynchronous operation. Name the file BasicAsyncOperation.swift.
```swift
import Foundation

class BasicAsyncOperation: TemplateAsyncOperation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        let dispatchGlobal = DispatchQueue.global()
        dispatchGlobal.async {
            sleep(2)
            self.result = self.input.uppercased()
            self.s = .f
        }
    }
    
    init(_ input: String) {
        self.input = input
    }
}
```

In this subclass of our asynchronous operation, we put main task inside “main” method.

In this example, we just uppercase the input string. We put it inside dispatch queue to simulate the coroutine or asynchronous method. The “main” method should not block. If we have finished the task, don’t forget to set the state variable “s” to “isFinished”.

Edit ViewController.swift. Add this code inside “viewDidLoad” method.
```swift
        let bap = BasicAsyncOperation("Hello World")
        bap.completionBlock = {
            print(bap.result)
            print(Date())
        }
        print(Date())
        bap.start() // Non-blocking
        print(Date())
```

The way to run asynchronous operation is same as running synchronous operation. This time the “start” method returns immediately.

If we run the application, we would get this output.
```
2019-10-27 16:37:43 +0000
2019-10-27 16:37:43 +0000
HELLO WORLD
2019-10-27 16:37:45 +0000
```

### Dependencies between Operation

We can create chain between Operations so Operation A can be run only after Operation B is finished.

Create a single view application. Name it HelloConcurrency7.

Create a new file inside HelloConcurrency7 folder. Name it DoubleWordAsyncOperation.swift.
```swift
import Foundation

class DoubleWordAsyncOperation: Operation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        self.result = input + " " + input
    }
    
    init(_ input: String) {
        self.input = input
    }
}
```

This is a normal Operation subclass which double the word and separate them by a space.

Create a new file inside HelloConcurrency7 folder. Name it UppercaseLowercaseAsyncOperation.swift.
```swift
import Foundation

class UppercaseLowercaseAsyncOperation: Operation {
    var result: String = ""
    var input: String = ""
    
    override func main() {
        let words = input.components(separatedBy: " ")
        self.result = words[0].uppercased() + " " + words[1].lowercased()
    }
}
```

This is a normal Operation subclass which makes the first word uppercased and the second word lowercased.

Edit ViewController.swift. Add this code inside viewDidLoad method.
```swift
        let op = OperationQueue()
        let doubleOp = DoubleWordAsyncOperation("jAkArta bAnDUNG")
        let uppercaseLowercaseOp = UppercaseLowercaseAsyncOperation()
        let transferOp = BlockOperation {
            uppercaseLowercaseOp.input = doubleOp.result
        }
        uppercaseLowercaseOp.addDependency(transferOp)
        transferOp.addDependency(doubleOp)
        
        op.addOperations([doubleOp, transferOp, uppercaseLowercaseOp], waitUntilFinished: true)
        
        print(uppercaseLowercaseOp.result)
```

To add the dependency between Operations, we can use “addDependency” method.
```swift
        uppercaseLowercaseOp.addDependency(transferOp)
        transferOp.addDependency(doubleOp)
```

If you notice, we create another Operation to transfer the result of one Operation to another Operation.
```swift
        let transferOp = BlockOperation {
            uppercaseLowercaseOp.input = doubleOp.result
        }
```

Then we can add them together to OperationQueue with “addOperations” method, instead of “addOperation” method.
```swift
        op.addOperations([doubleOp, transferOp, uppercaseLowercaseOp], waitUntilFinished: true)
```

If we run the application, we would get this output.
```
JAKARTA bandung
```

### Cancelling Operation

To cancel the operation, we can cancel it from OperationQueue or that Operation itself.

Create a new single view application. Name it HelloConcurrency8.

We must create one synchronous Operation and asynchronous Operation.

Create a new file inside HelloConcurrency8. Name it TemplateAsyncOperation.swift.
```swift
import Foundation

class TemplateAsyncOperation: Operation {
    enum State: String {
        case r = "Ready"
        case e = "Executing"
        case f = "Finished"
        fileprivate var key: String {
            return "is" + rawValue
        }
    }
    
    var s = State.r {
        willSet {
            willChangeValue(forKey: newValue.key)
            willChangeValue(forKey: s.key)
        }
        didSet {
            didChangeValue(forKey: oldValue.key)
            didChangeValue(forKey: s.key)
        }
    }
    
    override var isExecuting: Bool { return s == .e }
    
    override var isFinished: Bool { return s == .f }
    
    override var isAsynchronous: Bool { return true }
    
    override func start() {
        if isCancelled {
            s = .f
            return
        }
        main()
        s = .e
    }
    
    override func cancel() {
        s = .f
    }
    
    override var isReady: Bool {
        return super.isReady && s == .r
    }
}
```

This is the same as the asynchronous parent class that we have built before.

Create a asynchronous Operation based on this parent class. Add a new file inside HelloConcurrency8 directory: BasicAsyncOperation.swift.
```swift
import Foundation

class BasicAsyncOperation : TemplateAsyncOperation {
    var result: Int = 0
    
    override func main() {
        let dispatchGlobal = DispatchQueue.global()
        dispatchGlobal.async {
            for i in (1...10) {
                if self.isCancelled { return }
                sleep(1)
                self.result = i * 10
            }
        }
    }
    
}
```

In the “main” method, we check whether the Operation itself has been cancelled or not with “isCancelled” attribute.

Create a synchronous Operation based on this parent class. Add a new file inside HelloConcurrency8 directory: BasicOperation.swift.
```swift
import Foundation

class BasicOperation : Operation {
    var result: Int = 0
    
    override func main() {
        for i in (1...10) {
            if isCancelled { return }
            sleep(1)
            result = i * 10
        }
    }
}
```

Edit ViewController.swift.  Add this code inside “viewDidLoad” method.
```swift
        // Canceling synchronous task from OperationQueue
        let op = OperationQueue()
        let bo = BasicOperation()
        print(bo.result)
        op.addOperation(bo)
        sleep(3)
        op.cancelAllOperations()
        print(bo.result)
        
        // Canceling asynchronous task from Operation
        let bao = BasicAsyncOperation()
        print(bao.result)
        bao.start()
        sleep(4)
        bao.cancel()
        print(bao.result)
```

To cancel the Operation from OperationQueue, we use “cancelAllOperations” method.
```swift
        op.cancelAllOperations()
```

To cancel the Operation from that Operation itself, we use “cancel” method.
```swift
        bao.cancel()
```

If we run the application, we would get this output.
```
0
20
0
30
```
