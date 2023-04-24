# Error Handling

## Terminology

- `positive value` - successfully function/process result
- `recovery value` - positive value produced by error recovery process
- `error value` - value describing some error

`error value` can not be `positive value` at the same time.

## Error passing

To express that function can result in an error you should return `Either<Error, V>`  type. Error should be conventionally on left.

### UseCase

UseCases should return either `GenericError` or some custom sealed class inhereting ErrorType and Throwable.

## Logging

Errors should be logged at the `positive value` consumption point.

For example if you are loading some data for UI in `controller` you should log error at the point
where you extract concrete value to set into state.
