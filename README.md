# Practical challenges for RxJava learners

A set of simple code challenges to learn RxJava using JUnit tests as an acceptance criteria. Focused on some basic concepts and doesn't cover any Android topics yet.


[!["Solved" branch](https://travis-ci.org/sergiiz/RxBasicsKata.svg?branch=solved)](https://travis-ci.org/sergiiz/RxBasicsKata)


## Current implementation
### Dependencies:
- RxJava 3.1.x
- JUnit 5.8

### Reactive types covered:
- Observable: the heart of Rx, a class that emits a stream of data or events
- Single : a version of an Observable that emits a single item or fails
- Maybe: lazy emission pattern, can emit 1 or 0 items or an error signal

### Operators covered:
- map: transforms the items by applying a function to each item
- flatMap: takes the emissions of one Observable and returns merged emissions in another Observable to take its place
- filter: emits only those items from that pass a criteria (predicate test)
- skip/take: suppress or takes the first n items 
- all: determines whether all items meet some criteria
- reduce: applies a function to each item sequentially, and emit the final value. For example, it can be used to sum up all emitted items
- toMap: converts an Observable into another object or data structure
- merge: combine multiple Observables into one by merging their emissions
- sequenceEqual: determine whether two Observables emit the same sequence of items
- test: returns TestObserver with current Observable subscribed
- timeout: to handle timeouts, e.g. deliver some fallback data

### Testing approach:
- The set of test cases are defined in a separate java file
- As a “receiver” of emitted test events we use TestObserver. It records events and allows to make assertions about them
- All tests are failing when you just took them from the repo. This is expected behaviour. You should make tests pass by implementing the logic in CountriesServiceSolved class

## Using this koans 
You should fill CountriesServiceSolved.java  with the code required to make all tests GREEN
Default implementation in  this class returns null everywhere...

# Branches
_problem_  branch contains the empty CountriesServiceSolved.java , so all tests are RED
_solution_ branch contains a solution proposal check it out if needeed