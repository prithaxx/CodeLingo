package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ICourseData;

public class CourseDataStub implements ICourseData {

    private final List<CourseObj> courseList;

    public CourseDataStub(){
        courseList = new ArrayList<>();
        courseList.add(new CourseObj(1, "Java", null, true, false));
        courseList.add(new CourseObj(2, "Advanced Java Concepts", null, true, false));
        courseList.add(new CourseObj(3, "Networking Java Concepts", null, true, false));
        courseList.add(new CourseObj(4, "C#", null, true, false));
        courseList.add(new CourseObj(5, "JavaScript", null, true, false));
        courseList.add(new CourseObj(6, "C++", null, false, false));
    }

    @Override
    public CourseObj getCourseById(int id, int accountId){
        return courseList.stream()
                .filter(courseObj -> id == courseObj.id())
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CourseObj> getCourseList(int accountId){
        return courseList;
    }

}
