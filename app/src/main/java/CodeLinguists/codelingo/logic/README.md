# Logic Directory
The Logic Directory contains all handlers, generators, and validators. 
All data should pass through these classes when going to or from either the Persistence layer or the Presentation layer.

# Naming Scheme

## xHandler
Used between Managers and data. Performs calculation, data layer calls, and obj->primative conversions

## xManager
Used as a facade or adapter between UI and Handlers. Manages state, handles validation, and has minimal to no calculations

## xIterator
Used to iterate over a list of objects. Primarily driven by the UI class.