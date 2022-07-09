# Remember

Function provided by compose used to store a single object in memory. During composition it will store an initial value and when recomposition occurs it returns the stored value. The stored value can be updated. Each time recomposition occurs remember will return the latest stored value.

It will retain state across recompositions but not across configuration changes. To save across configuration changes you need to use `rememberSaveable` to automatically save in a `Bundle`.

```kt

// 
val text = remember {
  // Initial value stored in remember ""
  mutableStateOf("")
}

```

## Resources

- [State and Jetpack Compose](https://developer.android.com/jetpack/compose/state)
