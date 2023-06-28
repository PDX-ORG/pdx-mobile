# Navigation

## Goals

- Should be able to deeplink to any screen by default
- Navigation events should be fired from `ViewController` level
- Navigation system should discourage passing state via global state
- Dialogs / BottomSheets e.t.c. should be mainly displayed via navigation system\
- Navigation should be configured dynamically on startup

## Terminology

- `Page` - single user visible screen, multiple pages could be embedded to make single composite page
- `ViewController` - `ViewModel`/`Persenter`/`Socket` what ever component controls UI
- `Navigator` - object that passes navigation commands to UI layer and gives access to current state of navigation
- `Decoration` - UI element shared between pages, could be tweaked by individual pages

## URI based navigation concept

Every page has corresponding URI just like on web. Resource identifier style is preferred.

For example pokemon details page will have URI like: `pdx://pokemon/ditto?style=short`, where:
1. protocol: `pdx://` -  in this case app name is aliased to `pdx`, links to browser will contain `https` instead
2. part 1: `ppokemon` - denotes that we need to access pokemon resource
3. part 2: `ditto` - identifier of accessed resource, passed to page as parameter
4. query 1: `style=short` - some parameter passed to `Page`

**Pros:**
1. Each page will have semi-stable unique URI
2. Any parameters passed to page will be visible in URI
3. Can't pass complex data structure, promotes simplicity

**Cons:**
1. Changing page URI will require manually going to every navigation call

## Navigator contract

1. `navigateCommand : StateFlow<NavigateCommand>` - flow of command for UI layer
2. `navigate(route: Route)` - route (effectively URI) to next page

## Decorations

Sometimes application should contain UI elements that should be persisted across pages.
For example toolbar should be visible on all pages, but it could contain different titles / badges / e.t.c.

This issue is solved by `Decoration` system. 

`DecorationHost` - point of integration of decorations into UI.
Represents a slot that could be placed somewhere in UI. Marked by `decoarion route`

`Decoration` - side effect emitting peace of UI into `DecorationHost`. 
Should emit new version on UI on state changed. Connected to `DecoationHost` by specifying `decoartion route`.

Each page could have only one `Decoaration` unique `decoration route`. Multiple `Decorations`s with different `decoartino route`s are permitted.

### Example

```kotlin
@Composable
fun Toolbar() {
    // ... toolbar implementation
    DecoationHost("pdx://decoration/toolbar_header") {
        Text("Default Toolbar Title")
    }
}

// on home page
@Composable
fun HomePage() {
    // ... toolbar implementation
    Decoation("pdx://decoration/toolbar_header") {
        Text("HomePageTitle")
    }
}
// on search page
@Composable
fun SearchPage() {
    // ... toolbar implementation
    Decoation("pdx://decoration/toolbar_header") {
        SearchBar(
            query = state.query, 
            suggestions = state.suggestions,
            onUpdate = { newQuery -> act(UpdateQuery(newQuery)) }
        )
    }
}
```
