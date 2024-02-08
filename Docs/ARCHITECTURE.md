# Architecture

**Directory of Classes and their locations**

## Iteration 1 Diagram

![architecture](<Architecture.svg>)

## UI Layer
[GuestLogin](../app/src/main/java/CodeLinguists/codelingo/ui/view_GuestLogin.java)
- The starting screen of the app, where you can login as guest

[CourseOverview](../app/src/main/java/CodeLinguists/codelingo/ui/cont_CourseOverview.java)
- Show the overview of the selected course

[Chapter_Summary](../app/src/main/java/CodeLinguists/codelingo/ui/cont_ChapterSummary.java)
- Show the summary of the selected chapter

[SlideShowWrapper](../app/src/main/java/CodeLinguists/codelingo/ui/view_SlideShowWrapper.java)
- Wraps and instantiates quiz fragments

## Application Layer
[Services](../app/src/main/java/CodeLinguists/codelingo/application/Services.java)
- The main class that deals with the persistence layer for the Handler


## Logic Layer
[SessionManager](../app/src/main/java/CodeLinguists/codelingo/logic/SessionManager.java)
- Primary access between UI and logic layer.

[AccountHandler](../app/src/main/java/CodeLinguists/codelingo/logic/AccountHandler.java)
- Manages accessing, storing, and validating account based operations. 

[QuizHandler](../app/src/main/java/CodeLinguists/codelingo/logic/QuizHandler.java)
- Provides bidirectional, iterable access to a set of quizObj

### Exceptions
[AccountNotFoundException](../app/src/main/java/CodeLinguists/codelingo/exceptions/AccountNotFoundException.java)
- Exception used on failed login

[InputValidationException](../app/src/main/java/CodeLinguists/codelingo/exceptions/InputValidationException.java)
- The exception class for invalid inputs for constrained fields

## Persistence Layer
[IAccountData](../app/src/main/java/CodeLinguists/codelingo/persistence/IAccountData.java)
- The interface for the accounts in the database

[ISessionData](../app/src/main/java/CodeLinguists/codelingo/persistence/ISessionData.java)
- The interface for the inter-session persistant state data. 

[IQuizData](../app/src/main/java/CodeLinguists/codelingo/persistence/IQuizData.java)
- The interface to store all the quizzes

### Stubs
[AccountDataStub](../app/src/main/java/CodeLinguists/codelingo/persistence/stubs/AccountDataStub.java)
- Current account stub "database" for the app (real database will be implemented in a later iteration)

[SessionDataStub](../app/src/main/java/CodeLinguists/codelingo/persistence/stubs/SessionDataStub.java)
- Current session stub "database" for the app (real database will be implemented in a later iteration)

[QuizDataStub](../app/src/main/java/CodeLinguists/codelingo/persistence/stubs/QuizDataStub.java)
- Current quiz stub "database" for the app (real database will be implemented in a later iteration)

## Domain Specific Objects
[AccountObj](../app/src/main/java/CodeLinguists/codelingo/dso/AccountObj.java)
- The Account object

[ChapterObj](../app/src/main/java/CodeLinguists/codelingo/dso/ChapterObj.java)
- The Chapter object

[CourseObj](../app/src/main/java/CodeLinguists/codelingo/dso/CourseObj.java)
- The Course object

[QuizObj](../app/src/main/java/CodeLinguists/codelingo/dso/QuizObj.java)
- The Quiz object

## Docs

[View other docs](.)
