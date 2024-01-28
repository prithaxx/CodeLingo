package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;

public interface ICourseHandler {
    public List<CourseObj> getCourses();
    public CourseObj getCourseById(int id);

    /**
     * Pulls course from ISessionData
     */
    public CourseObj getActiveCourse();
}
