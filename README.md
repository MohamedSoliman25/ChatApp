# WebSocket Chat Application
## Overview

This project is a WebSocket-based chat application implemented using MVVM architecture with Clean Architecture principles, Kotlin, and Room Persistence Library for Android. The application allows two users to exchange messages in real-time over WebSocket connections and stores chat messages in a local database.

## Architecture

The application follows the MVVM architecture pattern combined with Clean Architecture. The project is divided into three main layers:

- **Presentation Layer**: Contains UI-related classes such as Activities, Fragments, and ViewModels.
- **Domain Layer**: Contains business logic.
- **Data Layer**: Manages data sources, including local database (Room) and remote WebSocket service.

  ## Design Decisions

- **WebSocket Communication**: Used OkHttp library for WebSocket communication.
- **Local Database**: Used Room Persistence Library for storing chat messages locally.
- **Dependency Injection**: Used Hilt for dependency injection to manage dependencies.
- **Clean Code**: Followed Kotlin coding conventions and applied SOLID principles for maintainable and readable code.
####
## Tools

- MVVM
- State flow
- OkHttp
- Coroutine
- Dependency Injection (Dagger Hilt)
- Room Database
- Repository Pattern
- View Binding
 ####
## Note
In the WebSocketService Class replace the local host with your local IP address
####
## Demo Video

Watch the demo video:

<iframe src="https://drive.google.com/file/d/1TDH7oEgp8r8Cl-DZViJn8NlsYcZFdY4q/view?usp=sharing" width="640" height="480"></iframe>
