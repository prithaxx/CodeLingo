package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;

public class CourseHandler implements ICourseHandler {
    private final ICourseData courseData;
    private final IChapterData chapterData;
    
    public CourseHandler(ICourseData courseData, IChapterData chapterData) {
        this.courseData = courseData;
        this.chapterData = chapterData;
    }

    @Override
    public CourseObj getActiveCourse(AccountObj account) throws CourseNotFoundException {
        return courseData.getCourseById(account.getActiveCourseId(), account.getId());
    }

    @Override
    public List<CourseObj> getCourseList(AccountObj account) {
        return courseData.getCourseList(account.getId());
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters(AccountObj account) throws CourseNotFoundException {
        return chapterData.getChapterByCourseId(account.getActiveCourseId(), account.getId());
    }

    @Override
    public int calculateProgressPercentage(AccountObj account) throws CourseNotFoundException {
        List<ChapterObj> listOfChapter = getActiveCourseChapters(account);

        int totalChapters = listOfChapter.size();
        int completedChapters = 0;

        for (ChapterObj chapter : listOfChapter) {
            if (chapter.isCompleted()) {
                completedChapters++;
            }
        }

        if (totalChapters == 0) return 0;

        double doublePercent = (double) completedChapters / totalChapters;

        return (int) (doublePercent * 100);
    }
}
