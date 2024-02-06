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
        this.sessionData = Services.getSessionData();
    }

    public CourseHandler(ICourseData courseData, ISessionData sessionData){
        this.courseData = courseData;
        this.sessionData = sessionData;
    }

    @Override
    public List<CourseObj> getCourses() {
        return courseData.getCourses();
    }

    @Override
    public CourseObj getCourseById(int id) {
        return courseData.getCourseById(id);
    }

    @Override
    public CourseObj getActiveCourse() {
        return sessionData.getActiveCourse();
    }
}

