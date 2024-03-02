package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface ICourseData {
    CourseObj getCourseById(int id, int accountId);
    List<CourseObj> getStartedCourseList(int accountId);
}
