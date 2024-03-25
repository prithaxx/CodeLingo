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
    public void setChapterComplete(int chapterId, AccountObj account) {
        chapterData.setChapterCompletionById(account.getId(), chapterId);
        // And if(there exists a next chapter) update nextChapter to isUnlocked=true
        if (chapterId < chapterData.getChapterByCourseId(account.getActiveCourseId(),account.getId()).size()) {
            chapterData.setChapterUnlockedById(account.getId(),chapterId,true);
        }
    }

    @Override
    public void unlockDefaultChapters(AccountObj account) {
        // not sure this is correct
        // it is unlocked the first chapter of each course
//        for (int i = 0; i < courseData.getCourseList(account.getId()).size(); i++) {
//            chapterData.setChapterUnlockedById(account.getId(),getCourseList(account).get(i).,true);
//        }
        // rightnow, it is just hardcoded cause I don't know how to indicate the first chapter of each course
        chapterData.setChapterUnlockedById(account.getId(),1,true);
        chapterData.setChapterUnlockedById(account.getId(),3,true);
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
