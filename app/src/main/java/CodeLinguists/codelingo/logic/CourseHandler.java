package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;
import java.util.List;

public class CourseHandler implements ICourseHandler {

    private ICourseData courseData;
    private ISessionData sessionData;

    public CourseHandler(){
        this.courseData = Services.getCourseData();
    }

    @Override
    public List<CourseObj> getCourses() {
        return courseData.getCourses();
    }

    @Override
    public CourseObj getCourseById(int id) {
        List<CourseObj> courses = getCourses();
        for (CourseObj course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null; 
    }

    @Override
    public CourseObj getActiveCourse() {
        sessionData = Services.getSessionData();
        return sessionData.getActiveCourse();
    }
}

