package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.persistenceExceptions.CourseNotFoundException;

public interface ICourseData {
    CourseObj getCourseById(int id, int accountId) throws CourseNotFoundException;
    List<CourseObj> getCourseList(int accountId);
}
