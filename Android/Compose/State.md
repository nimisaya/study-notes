# State

- state is a value that changes over time...you have the current state of something. the thing is changed and so the state has changed. Door's state is closed. Someone turns the handle and pulls, the state of the Door is now open.

- When the data that informs a UI state changes the UI state is changed and recomposition occurs. 
- 

`mutableStateOf`
- creates an observable `MutableState<T>` ???
- mutable: so can be changed?

```kt
interface MutableState<T> : State<T> {
    override var value: T
}
```

```kt
var door by remember { mutableStateOf(default) } // where default is the initial value
```


TODO: Finish https://www.raywenderlich.com/30172122-managing-state-in-jetpack-compose
