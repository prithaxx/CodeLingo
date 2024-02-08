package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;

public interface ICourseHandler {
    List<CourseObj> getCourses();
    CourseObj getCourseById(int id);
}
