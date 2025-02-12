package codelinguists.codelingo.unit_tests.logic.test_doubles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;

public class CourseHandlerTestDouble implements ICourseHandler {
    public CourseObj getActiveCourseResponse;
    public List<CourseObj> getCourseListResponse;
    public List<ChapterObj> getActiveCourseChaptersResponse;
    public int calculateProgressPercentageResponse;
    public Set<Integer> completedChapters = new HashSet<>();
    public Set<Integer> unlockedChapters = new HashSet<>();
    public boolean unlockDefaultChaptersCalled = false;
    public boolean doException;

    public CourseHandlerTestDouble() {
        getActiveCourseResponse = new CourseObj(1, "name", "descrip", true, true);
        getCourseListResponse = new ArrayList<>();
        getCourseListResponse.add(getActiveCourseResponse);
        getActiveCourseChaptersResponse = new ArrayList<>();
        getActiveCourseChaptersResponse.add(new ChapterObj(1,"name", 1, "descript", true, false));
        calculateProgressPercentageResponse = 12;
        doException = false;
    }

    @Override
    public CourseObj getActiveCourse(AccountObj account) throws CourseNotFoundException {
        if (doException) {
            throw new CourseNotFoundException("Empty");
        }
        return getActiveCourseResponse;
    }

    @Override
    public List<CourseObj> getCourseList(AccountObj account) {
        return getCourseListResponse;
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters(AccountObj account) throws CourseNotFoundException {
        if (doException) {
            throw new CourseNotFoundException("Empty");
        }
        return getActiveCourseChaptersResponse;
    }

    @Override
    public void setChapterComplete(int chapterId, AccountObj account) {
        completedChapters.add(chapterId);
        unlockedChapters.add(chapterId + 1);
    }

    @Override
    public void unlockDefaultChapters(AccountObj account) {
        unlockDefaultChaptersCalled = true;
    }

    @Override
    public int calculateProgressPercentage(AccountObj account) throws CourseNotFoundException {
        if (doException) {
            throw new CourseNotFoundException("Empty");
        }
        return calculateProgressPercentageResponse;
    }
}
