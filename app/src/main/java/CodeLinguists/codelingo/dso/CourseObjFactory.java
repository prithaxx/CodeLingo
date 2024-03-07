package CodeLinguists.codelingo.dso;

public class CourseObjFactory {
    public static CourseObj getNoneCourse() {
        return new CourseObj(0, "Select a Course", "No Course Selected", false, false);
    }
}
