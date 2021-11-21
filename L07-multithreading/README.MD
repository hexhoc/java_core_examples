# Thread

## Description

Threads in Java, ways to create and manage thread states, the main problems when working with threads and
synchronization.

## EXERCISE:

## 1.Thread creation and base (package p1_creation_and_base)

### Create thread using extends from Threads class(easy)

1. Create class ThreadExampleClass
2. Use extends from thread
3. Create and run instance of the ThreadExampleClass

### Create thread using implements from Runnable interface (easy)

1. Create class RunnableExampleClass
2. Use implementation from runnable
3. Create and run instance of the RunnableExampleClass

### Create thread using anonymous class from Runnable interface (easy)

1. Create class AnonymousExampleClass
2. Create thread using anonymous class of Runnable interface
3. Start thread
4. Make sure that the main thread does not terminate until the subordinate thread completes its work

### Create daemon thread (easy)

Usually when the main thread terminates, the application continues to run as long as all the subordinate threads are
running. If we want the subordinate thread to finish its work as soon as the main thread finishes, then we need to mark
it as a daemon.
1. Create class DaemonClass
2. Create thread using anonymous class (runnable interface)
3. Mark thread as daemon
4. run and check it

### Stop thread (easy)
Write class that launch new thread and then stop it before it done

1. Create class StopThreadApp
2. Create thread using anonymous class (runnable interface) with infinity loop
3. Start thread
4. After 5 second try to stop thread from main thread

## 2.Race condition (package p2_race_condition)

### Reproduce race condition (normal)

When two or more thread try to work with one object we can encounter with race condition. For example both thread try to
get value of int variable, both of them get 0, one thread increment 0 and set 1, another thread decrement 0 and set
-1, win one of them, and total value will be 1 or -1 but not 0;

1. Create class Counter that have method that increment and decrement static int variable
2. Create class RaceCondition
3. Create and start thread that increment counter 1000 times
4. Create and start thread that decrement counter 1000 times
5. Wait until all threads are executed and then check counter value

Try to fix race condition


## 3.Synchronization of thread (package p3_synchronization)

### Synchronized static and non-static method (easy)
1. Create class SyncMethodsApp with method1 and method2 which do something within 5 second
2. Create instance of SyncMethodsApp
3. Create two thread. One thread call method1, two thread call method2
4. Run the threads and make sure that methods will be executed sequentially
5. Create class SyncStaticMethodApp and try to implement static synchronized method. Notice what if we use synchronized static, we block all class

### Synchronized monitor (normal)
1. Create class SyncMonitorApp with method1()
2. method1 should have non-synchronized and synchronized block of code (synchronize it using monitor)
3. Try to create 3 threads and execute method1 and watch

### Synchronization of double counter (normal)
1. Create class SyncDoubleCounter with non-static field int value1 and int value2
2. Create method that increment and decrement each of them
3. Create class SyncCounterApp
4. Create instance of SyncDoubleCounter
5. Create 4 thread, two of them increment and decrement value1 100 times, and another two value2.
6. You have to process value1 and value2 parallel, but try to avoid race condition 

### Fix synchronization problem
In SyncProblemApp class each thread should be executed sequentially, but instead they are all start at the same time.
Try to fix the problem.

## 4.Deadlock (package p4_deadlock)

### Reproduce deadlock
1. Create class DeadlockApp
2. Create method1 and method2 which do something
3. Create two thread, one execute method1 and two execute method2
4. Make that method1 and method2 block each other and stuck

## HOMEWORK 1
Fill large array using multithreading
1. Create an array of 100 million elements 
2. Fill array using 2 thread. Each thread should take and fill half of array
3. Using arraycopy assemble a whole array of two parts

## 8.Wait notify thread (package p8_wait_notify)

### Multithreading print string
Using two thread try to print string "ABABABAB"
1. Create non-static variable char currentChar = 'A'
2. Create method printA() that print 'A' 3 times, and method printB that print 'B' 3 times
3. Create two threads one of them execute printA(), another printB()
4. Result should be "ABABABAB"

