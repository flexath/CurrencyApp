# CurrencyApp
It is case study.

### Currency App is an Android application built using Jetpack Compose, which supports both light and dark modes. 

#### Components Used
### 1. Clean Architecture
The app follows Clean Architecture, which separates concerns into different layers for better maintainability, scalability, and testability.
##### Layers:
    Data Layer: Handles the data operations, including network requests and local database management.
    Domain Layer: Contains the business logic and use cases that define how the data is used.
    Presentation Layer: Manages the UI and user interactions using Jetpack Compose.

### 2. Dagger Hilt for Dependency Injection
Dagger Hilt is used to manage dependency injection across the app, simplifying the setup and management of dependencies like ViewModels, Repositories, and Use Cases.

### 3. Retrofit for Remote API Calls
The app utilizes Retrofit to make API requests to fetch live currency data from a remote server. Retrofit simplifies network operations and integrates well with Kotlin Coroutines for asynchronous data fetching.

### 4. Room Database for Local Storage
Room is used as the local database to store and cache currency data. This allows the app to function offline and improve the user experience with cached data when there's no internet connection.

### 5. Kotlin Coroutine Flows for Reactive Data Handling
Kotlin Coroutine Flows are used to manage reactive data streams, ensuring that the UI is updated in real-time when data changes.


## Important : Be aware of API_KEY
The API key is stored securely in the local.properties file to keep it out of version control. Make sure to add your API key there before running the app:
###### 1 - Open the local.properties file.
###### 2 - Add the following line with your API key:
    API_KEY=your_api_key_here
  
##### Note: This file is not included in the repository, so you'll need to create it or add your key to the existing file.
