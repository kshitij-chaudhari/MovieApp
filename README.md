# MoviesApp

## About the app
I have created this small Movie app with simple UI to showcase my skills on how to create `Stable` app with proper `MVVM` and `Clean Architecture`. Also the project is using remote api network call and save data on room database to show data on the UI. Launching screen will show the list of movies and user can also able filter movies by searching for terms in the title or by the genre.

## Code structuring approach
To build a good software architecture system that would be easy to understand, to develop, to maintain and to implement. We should follow modern architecture patterns like mentioned below:

1. `MVVM` helps us to separates our views (i.e. Activities and Fragments) from our business logic. MVVM is enough for small projects, but when our codebase becomes huge, our `View-Models` start bloating. Separating responsibilities becomes hard.

2. `MVVM` with `Clean Architecture` and `SOLID Principles` goes one step further in separating the responsibilities of our code base. It clearly abstracts the logic of the actions that can be performed in your app.
 
### CLEAN Architecture:

First of all the layers in `Clean architecture` has been separated into individual modules in a single Android project. For example android modules like `app`, `core`, `presentation`, `data` and `domain`. Notice the dependency of these modules, as per the dependency rule of `Clean architecture`, all the dependencies directly or indirectly point towards the the domain layer. The domain layer incorporates `Entities`, `Use-Cases` and interfaces required to cross boundaries, `Repository` in this case. The `Data` layer handles data and communicates with data source from `Remote` in this case Rest API, to provides required data requested by the `Presentation` layer. `View` layer will observe on the `presentation` layer's stateFlow object, to get the updated data on state change. I have created `Local` data source to provide offline data support for the application using local database.

Having separate modules is not necessary, we can create all the layers in the app modules itself. Having separate modules and depending on the intended modules prevent accidental usage of a classes in unintended places. There are also couple of benefits mentioned below.

<img src="screenshot/Clean-Arch.png" alt="CLEAN Architecture in Android" style="float: left; margin-right: 10px;">

### Reason for using CLEAN architecture

- Our code will be even more easily testable than with plain MVVM
- Our code will be further decoupled
- The project will be even easier to maintain.
- Adding new features can be easy and quick.
- Adding new library and replacing old one can be easy and quick.
- Dependencies can only point inwards (from concretions towards abstractions)
- The project will be point towards Stability
- Our code will point towards Abstractions

## Libraries

- **Kotlin-Coroutine-Flow**:for asynchronous task, reactive programming, mapping, transformation.
- **Hilt**: for Dependency Injection
- **Retrofit**: Netwoking Library to fetch data from REST API
- **Room-Database**: Database library to save data locally and provide offline data
- **View-Binding**: for Accessing xml view ids
- **ViewModel**: For persisting data across configuration changes
- **Expresso, Mockk, Junit** - For testing
- **Glide** - for Image loading

## App screenshot

<img src="screenshot/MovieList.png" width="300"/> <img src="screenshot/SearchList.png" width="300"/>
