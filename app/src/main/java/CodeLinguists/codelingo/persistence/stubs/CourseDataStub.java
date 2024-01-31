package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ICourseData;

public class CourseDataStub implements ICourseData {
    List<CourseObj> placeholders;

    public CourseDataStub() {
        this.placeholders =  new ArrayList<CourseObj>() {{
            new CourseObj(1,"ex1", "This is a placeholder 1", false, false);
            new CourseObj(2,"ex2", "This is a placeholder 2", false, false);
            new CourseObj(3,"ex3", "This is a placeholder 3", false, false);
            new CourseObj(4,"ex4", "This is a placeholder 4", false, false);
        }};
    }

    public CourseDataStub(List<CourseObj> placeholders) {
        this.placeholders = placeholders;
    }

    @Override
    public List<CourseObj> getCourses() {
        return this.placeholders;
    }
}
