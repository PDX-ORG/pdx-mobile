# PDX Android

![Github Actions Build Apl](https://img.shields.io/github/actions/workflow/status/PDX-ORG/pdx-mobile-android/build_apk.yml)
![Stars](https://img.shields.io/github/stars/PDX-ORG/pdx-mobile-android)
![Lines](https://img.shields.io/tokei/lines/github/PDX-ORG/pdx-mobile-android)

**The** open-source Pokèdex

## Screenshots

Home |                          Pokemon Details                           | Settings
:---:|:------------------------------------------------------------------:|:-------------------------: 
![home_sample.png](assets%2Fhome_sample.png)  | ![pokemon_details_sample.png](assets%2Fpokemon_details_sample.png) | ![settings_sample.png](assets%2Fsettings_sample.png)




## Features

✔️ Full pokemon list up to gen 9

✔️ Beautiful Material You design. With multiple pokemon type based themes

✔️ Pokemon view history

✔️ "Who is this pokemon?" game

✔️ Type calculator

✔️ Roomaji pokemon names option

🚧 Full pokemon details

🚧 Item-dex

🚧 Team builder / calculator

🚧 And more, coming soon...

## Tech stack

- Jetpack Compose
- Ktor-Client with OkHttp engine
- [Alice-DI](https://github.com/akore-org/alice)
- Kotlin Coroutines
- Arrow
- [Blogger](https://github.com/akore-org/blogger)

## Architecture

### Module structure

There are 4 types of modules:
- library - small reusable peace of code, that does not contain any app specific or core domain logic
- domain - domain module of application, we do not separate data/domain/e.t.c. layers KISS
- feature - small self-contained screen or set of app screens
- target - application root module

Modules could depend on each other in following combinations

target <- (library, domain, feature)
feature <- (library, domain)
domain <- (library, domain)
library <- (library)

Note, no feature could depend on another feature, and no domain module could depend on feature.

### State management pattern

We use google's default MVVM pattern, but looking for something else in the future.
Might migrate to custom ELM/TEA architecture in the future

## Contributors

- [lexa-diky a.k.a graph of graphs, master of PDX](https://github.com/lexa-diky)
- Might be you (you can also have cool title)
