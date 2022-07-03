# RxJava

Reactive Extensions in Java (RxJava) is a library that enables Functional Reactive Programming and used for event-driven programming. 

In event-driven programming values are changed over time and the consumer reacts to the data as it comes in.

Most common uses are:

- Network calls
- UI events that trigger actions
- Database Read/Write 
- Data coming in from sensors

## Simple example

**Marlon** is suspicious of his friend **Jeff** and thinks he might be an undercover spy. He has asked Jeff's roommate, **Sam**, to keep an eye on Jeff.

Jeff (Observable) sneaks out of the house at odd hours (data), makes secretive phone calls in gibberish (data) and has been waking up at weird times (data). 

Sam (Observer) reports back each time Jeff's state changes by making a callback to whoever is listening (Marlon...and it appears the other roommates are curious as well). 

```kt
val jeff : Observable<SuspiciousActions>
val sam: Subscriber

jeff = Observable.just(
    SuspiciousAction("Sneak out")
    SuspiciousAction("Phone call")
    SuspiciousAction("Sneak out")
    SuspiciousAction("Wakes up")
    SuspiciousAction("Phone call")
)

sam = jeff.subscribe({whatHappened -> reportToOthers(whatHappened)})
```

## Event streams

Sequence of events that happen over time

## 👋  Observable (the watched)

Observable is a event/data stream, does work and then emits items that are observed by the Observers. 

Observables can be non-blocking (asynchronous) and blocking (non-synchronous).

### Types of observables in RxJava

- **Observable**: emit one or more items e.g. downloading file and want to emit status (0%, 10%, 50%, 75%, 100%)
- **Flowable**: emit high number of items that can't be consumed by Observer, so will skip some items based on a strategy or throw exception. It has a strategy to handle exception.
- **Single**: emit only one items e.g. response from network call
- **Maybe**: emit a items or no items (is it like Either? [Item, No Item]?)
- **Completable**: it has to do some task without emitting a items

### Creating an observable

Use operators to create observable depending on the ????:

- `create()`
- `fromIterable()`
- `fromArray()`
- `just()`
- `range()`
- `repeat()`


## 👀  Observers (Watchers)

Subscribes to (watches) the observable

### Methods

- `onNext`: Called each time a new event is published to the Observable and allows us to perform an action on each event. For example, each time an event is published increment the count by 1.
- `onError`: Called when an unhandled exception is thrown in the event handler or RxJava framework code
- `onCompleted`: Called when the sequence of the events is complete (finished emitting items). So it knows not to expect anymore `onNext` calls.

### Types of Observers in RxJava

- Observer
- SingleObserver
- MaybeObserver
- CompletableObserver

## 🧰  Operator

Transform, modify and filter observables before the observer(s) are notified. They can be chained ⛓ because they usually return an Observable.

### Common operators

- `filter()`: filter out items (events) that don't meet the predicate (requirement)
- `map()`: apply a function to each item 
- `flatMap()`
- `concatMap()`
- `concat()`
- `combineLatest()`
- `zip()`
- `scan()`
- `reduce()`
- `debounce()`
- `distinct()`
- `distinctUntilChanged()`: suppress duplicate items emitted
- `takeUntil()`
- `defaultIfEmpty()`

## 📅  Scheduler

Schedules and assigns operations to threads. There's a specific **RxAndroid** scheduler (`AndroidScheduler`).

## 🗑  Disposables

All observers are disposable (implement `DisposableObserver` interface). Keep track of all of the observers then clean them up (dispose) when they are no longer needed. In MVVM probably want to track disposables in ViewModel.

Add observer as a disposable
```kt
disposables.add(nameOfDisposable) // call in onSubscribe(nameOfDisposable: Disposable)
```

Clean up disposables in activity (recommended to do in ViewModel instead)

```kt
override fun onDestroy() {
    super.onDestroy()
    disposables.clear() // there is also .dispose() but I think that means nothing else can subscribe to the observable
}
```

Clean up disposables in ViewModel

```kt
override fun onCleared() {
    super.onCleared()
    disposables.clear()
}
```

## Dependencies for Android

You want to ensure you have `RxJava` and `RxAndroid` dependencies. RxAndroid has Android specific components and RxJava is updated more frequently.

## Create an observable

### Example: You have a Model of the data called `Example`

```kt
    Observable<Example> exampleObservable = Observable
        // Run on background IO thread - the worker thread
        .subscribeOn(Scheduler.io))
        /* Tell the observer what thread to watch e.g. mainThread because you need a UI operation to 
        take place e.g. you are showing the download progress bar and need to update its status */
        .observeOn(AndroidSchedulers.mainThread())
    exampleObservable.subscribe(Observer<Example>){
        override fun onSubscribe(d: Disposable) {}
        override fun onNext(example: Example) {} // going to be called each time item emitted
        override fun onError(error: Throwable) {}
        override fun onComplete() {}
    }
```

### Example: `just`

```kt
Observable.just("Apple", "Orange", "Banana")        // just converts strings to Observable items
    .subscribe(
        { value -> println("received: $value") },   // onNext - item emitted from stream
        { error -> println("Error: $error) },       // onError - error occured within stream
        { println("Complete") }                     // onComplete - stream finished emitting
    )
```

### Example: Network call (using retrofit & gson converter)

_Note: only snippets of the code included_

Define endpoint
```kt
import io.reactivex.Observable
import retrofit2.http.GET
 
interface GetData {
 
    // Describe the request type and the relative URL
   @GET("prices?key=YOUR-API-KEY-HERE")
    fun getData() : Observable<List<Food>>
 
}
```

Create model

```kt
data class Food(val name : String, val price : String)
```

Retrofit instance

```kt
private fun loadData() {
 
    // Build a Retrofit object 
   val requestInterface = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create()) // set the converter so can translate JSON to java object
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // gonna use RxJava
 
            // Get a usable Retrofit object by calling .build()
           .build().create(GetData::class.java)
```

```kt
myCompositeDisposable?.add(requestInterface.getData() // going to return a disposable to be cleared in onCleared()
            .observeOn(AndroidSchedulers.mainThread()) // send observable items to main thread (UI thread)
            .subscribeOn(Schedulers.io()) 
            .subscribe(this::handleResponse))
 
}
```


## Resources

- [Understanding Marble Diagrams for Reactive Streams](https://medium.com/@jshvarts/read-marble-diagrams-like-a-pro-3d72934d3ef5)
- [RxJava and RxAndroid for Beginners YouTube Playlist](https://www.youtube.com/playlist?list=PLgCYzUzKIBE-8wE9Sv3yzYZlo70PBmFPz)  by CodingWithMitch
- [Fundamentals of RxJava with Kotlin for absolute beginners](https://medium.com/@gabrieldemattosleon/fundamentals-of-rxjava-with-kotlin-for-absolute-beginners-3d811350b701) by Gabriel Leon de Mattos
- [Understanding Types Of Observables In RxJava](https://blog.mindorks.com/understanding-types-of-observables-in-rxjava-6c3a2d0819c8) by Amit Shekhar
