package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;

public interface ICourseHandler {
    //Either return the active course, the placeholder course, or throw courseNotFound
    CourseObj getActiveCourse(AccountObj account) throws CourseNotFoundException;
    List<CourseObj> getCourseList(AccountObj account);
    List<ChapterObj> getActiveCourseChapters(AccountObj account) throws CourseNotFoundException;
    int calculateProgressPercentage(AccountObj account, CourseObj course) throws CourseNotFoundException;
}
