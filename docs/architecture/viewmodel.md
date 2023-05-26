# ViewModel Structure

## Base Type

Generic view model should extend `androidx.lifecycle.ViewModel`.

## State Exposure

State is exposed via `state` property of type `T` where `T` is your custom data class.
To propagate changes form `ViewModel` to compose UI layer use MutableState<T> get/set delegates:

```kotlin
var state by mutableStateOf(MovesSubPageState())
    private set
```

Note, `state`'s `set` is private.

## Interactions from UI layer

All interactions from UI layer should be done via direct calls to `viewmodel`.

```kotlin
class ErrorDialogViewModel : ViewModel() {
    
    fun hideError() {  }
}

@Composable
fun ErrorDialog(vm: ErrorDialogViewModel) {
    ...
    DismissButton(onClick = { vm.hideError() })
}
```

## Dependencies

`ViewModel`s accept usually require`UseCase` and `Navigator` objects as dependencies.

## ErrorHandling

Use `classify` function to convert `UseCase`s domain error to `UIError`. Then consume either value or error with `when` statement.

```kotlin
state = when (val data = getData(id).classify(MyClass::class)) {
    is Either.Left -> state.copy(error = data.value)
    is Either.Right -> state.copy(value = data.value)
}
```

