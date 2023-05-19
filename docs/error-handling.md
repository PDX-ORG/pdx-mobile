# Error Handling

## Terminology

- `positive value` - successfully function/process result
- `recovery value` - positive value produced by error recovery process
- `error value` - value describing some error

`error value` can not be `positive value` at the same time.

## Error passing

To express that function can result in an error you should return `Either<Error, V>`  type. Error should be conventionally on left.

## UseCase

UseCases should return either `GenericError` or some custom sealed class inheriting ErrorType and Throwable.

## Logging

Errors should be logged at the `positive value` consumption point.

For example if you are loading some data for UI in `controller` you should log error at the point
where you extract concrete value to set into state.

## Handling

### Recovery

No recovery policy specified, just show error to user.

### Display

1. Pass error via [viewmodel](viewmodel.md) to UI layer.
2. Display error via `ErrorDialog`


```kotlin
class MyViewModel(...) {

    init {
        state = when (val data = getData(id).classify(MyClass::class)) {
            is Either.Left -> state.copy(error = data.value)
            is Either.Right -> state.copy(value = data.value)
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }
}

@Composable
fun MyComposable(vm: MyViewModel) {
    ErrorDialog(vm.state.error) {
        vm.hideError()
    }
}
```
