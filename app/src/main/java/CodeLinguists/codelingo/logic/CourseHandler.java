package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.ISessionData;

import java.util.List;

public class CourseHandler implements ICourseHandler {

    private final ICourseData courseData;

    public CourseHandler(){
        this.courseData = Services.getCourseData();
    }

    public CourseHandler(ICourseData courseData){
        this.courseData = courseData;
    }

    @Override
    public List<CourseObj> getCourses() {
        return courseData.getCourses();
    }

    @Override
    public CourseObj getCourseById(int id) {
        return courseData.getCourseById(id);
    }
}

