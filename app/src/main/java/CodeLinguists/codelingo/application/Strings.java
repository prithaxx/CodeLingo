package CodeLinguists.codelingo.application;

public class Strings {

    //User Exceptions
    public static String NoCourseSelected = "Please select a course and chapter"; //For NoItemSelectedExceptions
    public static String SlideNotSupported = "This question is not currently supported"; //For SlideTypeNotHandledExceptions
    public static String QuestionNotAnswered = "No answer provided for the question."; //For QuizHandler
    public static String NoName = "Name cannot be empty.";
    public static String NotSignedIn = "Account is not set.";
    public static String CannotCreateAccount = "Creating account failed, please try again";
    public static String AccountNotFound = "No account found";

    public static String AccountNotFoundWithName(String name) { return "No account found for the name " + name; }
    public static String CourseNotFound(int courseId) { return "Course "+courseId+" is not available. Selecte a different course"; }
    public static String CourseHasNoChapters = "Course under construction, no lessons found for course.";
    public static String CannotFindPreferences = "LOCAL_PREFERENCES returned no entries.";

    //System Exceptions
    public static String DbNotInitialized = "Database is not instantiate yet!";
    public static String CannotModifyDependencies = "Cannot modify ";
    public static String HSQLDBUrl(String dbName) { return "jdbc:hsqldb:file:"+dbName+";shutdown=true";}

}
