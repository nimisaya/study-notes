# State

- state is a value that changes over time...you have the current state of something. the thing is changed and so the state has changed. Door's state is closed. Someone turns the handle and pulls, the state of the Door is now open.

- When the data that informs a UI state changes the UI state is changed and recomposition occurs. 

## `mutableStateOf`
- creates an observable `MutableState<T>`
- mutable: so can be changed?

```kt
interface MutableState<T> : State<T> {
    override var value: T
}
```

```kt
var door by remember { mutableStateOf(default) } // where default is the initial value
```
## Stateful 

Composables that store state internally using `remember`. Useful when the caller doesn't need to control state but means it is less reusable and harder to test

## Stateless

Composable that doesn't hold any state. Can be achieved through state hoisting

## State Hoisting

Move state to the composables caller to make the composable stateless.

Reasons to hoist the state:

- Only one source of truth for the state (it's not duplicated)
- State can be shared with multiple composables. You might have multiple composables that care if the door is closed or open
- Caller containing can intercept and either ignore or modify events before changing the state and prompting recomposition of the composable. 
- The state is decoupled from the composable, so it can be stored anywhere e.g. moved to the `ViewModel`



TODO: Finish https://www.raywenderlich.com/30172122-managing-state-in-jetpack-compose
