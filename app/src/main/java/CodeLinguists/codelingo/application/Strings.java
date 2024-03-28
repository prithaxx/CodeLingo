package CodeLinguists.codelingo.application;

public class Strings {

    //User Exceptions
    public final static String NoCourseSelected = "Please select a course and chapter"; //For NoItemSelectedExceptions
    public final static String SlideNotSupported = "This question is not currently supported"; //For SlideTypeNotHandledExceptions
    public final static String QuestionNotAnswered = "No answer provided for the question."; //For QuizHandler
    public final static String NoName = "Name cannot be empty.";
    public final static String NotSignedIn = "Account is not set.";
    public final static String CannotCreateAccount = "Creating account failed, please try again";
    public final static String AccountNotFound = "No account found";
    public final static String ChapterIdPositive = "Chapter Ids must be positive";
    public final static String NoChapterSelected = "Could not start quiz, please select a chapter";
    public final static String QuizNotFound = "Could not find the answer to quiz. Please try again";

    public static String AccountNotFoundWithName(String name) { return "No account found for the name " + name; }
    public static String CourseNotFound(int courseId) { return "Course "+courseId+" is not available. Selecte a different course"; }
    public final static String CourseHasNoChapters = "Course under construction, no lessons found for course.";
    public final static String CannotFindPreferences = "LOCAL_PREFERENCES returned no entries.";

    //System Exceptions
    public final static String DbNotInitialized = "Database is not instantiate yet!";
    public final static String CannotModifyDependencies = "Cannot modify ";
    public static String HSQLDBUrl(String dbName) { return "jdbc:hsqldb:file:"+dbName+";shutdown=true";}

}
