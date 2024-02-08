# Architecture

**Directory of Classes and their locations**

## Iteration 1 Diagram

![architecture](<Architecture.svg>)

## UI Layer
[GuestLogin](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/ui/view_GuestLogin.java?ref_type=heads)
- The starting screen of the app, where you can login as guest

[CourseOverview](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/ui/view_CourseOverview.java?ref_type=heads)
- Show the overview of the selected course

[Chapter_Summary](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/ui/cont_ChapterSummary.java?ref_type=heads)
- Show the summary of the selected chapter

[SlideShowWrapper](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/ui/view_SlideShowWrapper.java?ref_type=heads)
- Wraps and instantiates quiz fragments

## Application Layer
[Services](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/application/Services.java?ref_type=heads)
- The main class that deals with the persistence layer for the Handler


## Logic Layer
[SessionManager]()
- Primary access between UI and logic layer.

[AccountHandler](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/logic/AccountHandler.java?ref_type=heads)
- Manages accessing, storing, and validating account based operations. 

[QuizHandler](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/logic/QuizHandler.java?ref_type=heads)
- Provides bidirectional, iterable access to a set of quizObj

### Exceptions
[AccountNotFoundException](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/exceptions/AccountNotFoundException.java?ref_type=heads)
- Exception used on failed login

[InputValidationException](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/exceptions/InputValidationException.java?ref_type=heads)
- The exception class for invalid inputs for constrained fields

## Persistence Layer
[IAccountData](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/IAccountData.java?ref_type=heads):
- The interface for the accounts in the database

[ISessionData](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/IChapterData.java?ref_type=heads):
- The interface for the inter-session persistant state data. 

[IQuizData](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/IQuizData.java?ref_type=heads):
- The interface to store all the quizzes

### Stubs
[AccountDataStub](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/stubs/AccountDataStub.java?ref_type=heads)
- Current account stub "database" for the app (real database will be implemented in a later iteration)

[SessionDataStub](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/stubs/SessionDataStub.java?ref_type=heads)
- Current session stub "database" for the app (real database will be implemented in a later iteration)

[QuizDataStub](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/persistence/stubs/QuizDataStub.java?ref_type=heads)
- Current quiz stub "database" for the app (real database will be implemented in a later iteration)

## Domain Specific Objects
[AccountObj](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/dso/AccountObj.java?ref_type=heads):
- The Account object

[ChapterObj](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/dso/ChapterObj.java?ref_type=heads):
- The Chapter object

[CourseObj](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/dso/CourseObj.java?ref_type=heads):
- The Course object

[QuizObj](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/blob/development/app/src/main/java/CodeLinguists/codelingo/dso/QuizObj.java?ref_type=heads):
- The Quiz object

## Docs

[View other docs](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/tree/development/Docs?ref_type=heads)
