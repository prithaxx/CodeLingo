package CodeLinguists.codelingo.dso;

import CodeLinguists.codelingo.logic.Messages;

public class CourseObjFactory {
    public static CourseObj getNoneCourse() {
        return new CourseObj(0, Messages.SelectCourse, Messages.NonSelected, false, false);
    }
}
