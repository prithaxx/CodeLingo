# Coding Style

## Naming
Java class, field, method, and variable names are written in CamelCase
```java
thisIsAnExample
```
Class names are generally noun or noun phrases and begin with a capital letter
```java
AccountObj
```
All Interface classes shall be prefixed with 'I'.
```java
IAccountHandler
```
Method names generally begin with a lowercase letter. A call on a procedure is a statement to do something, so a procedure name is generally a verb phrase that is a command to do something
```java
public void setActiveCourse(CourseObj activeCourse){}
```
Variable names generally start with a lowercase letter, they should give the reader some hint about what the variable is used for.
```java
chapterId
```


## Format
All opening curly brackets shall sit *beside* the line, not under, followed by a newline.
```java
public boolean doMethod() {
```

All indents are four spaces. All indenting is done with tabs.

Matching braces always line up vertically in the same column as their construct.
```java
if(condition) {
    do thing
}
```

## Code organization
Place all field and class (static) variables at the beginning of a class, before all the methods.

Class members should be private where possible

All classes shall be setup as follows:
```java
class Order {
    //fields
    
    //constructors
    
    //methods
    
    @override
    //methods
}
```
Use returns to simplify method structure, avoid redundancy

Keep methods short, if a single method is getting beyond 20-50 lines, consider lifting some of its functionality into other methods.

## Documentation
As a rule of thumb, code should be self-explanatory 99% of the time. If you feel the need to comment, there's a good chance you can write it in a simpler, better way.

Of course, sometimes you do need to comment to clarify something, or point out something that isn't obvious. However, they should be used minimally.
