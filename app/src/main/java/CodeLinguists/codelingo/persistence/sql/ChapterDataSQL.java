package CodeLinguists.codelingo.persistence.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

public class ChapterDataSQL implements IChapterData {
    private final ISqlRunner sqlRunner;

    public ChapterDataSQL(ISqlRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @Override
    public List<ChapterObj> getChapterByCourseId(int courseId, int accountId) {
        List<ChapterObj> chapters = new ArrayList<>();
        try (ResultSet rs = sqlRunner.selectChaptersByCourseId(courseId, accountId)) {
            while (rs.next()) {
                chapters.add(new ChapterObj(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("courseId"),
                        rs.getString("description"),
                        rs.getBoolean("isUnlocked"),
                        rs.getBoolean("isCompleted")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public void setChapterCompletionById(int accountId, int chapterId) {
        try {
            boolean exists = sqlRunner.selectChapterCompletionById(accountId, chapterId).next();
            if (exists) {
                sqlRunner.updateChapterCompletionSetComplete(accountId, chapterId);
            } else {
                sqlRunner.insertUnlockedIntoChapterCompletion(accountId,chapterId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isChapterComplete(int accountId, int chapterId) {
        boolean isComplete;
        try {
            isComplete = sqlRunner.selectChapterCompletionById(accountId, chapterId).getBoolean("isCompleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isComplete;
    }

        @Override
    public void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked) {
        try {
            boolean exists = sqlRunner.selectChapterCompletionById(accountId, chapterId).next();
            if (exists) {
                sqlRunner.updateChapterCompletion(accountId, chapterId, unlocked);
            } else {
                sqlRunner.insertChapterCompletion(accountId,chapterId, unlocked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isChapterUnlocked(int accountId, int chapterId) throws CourseNotFoundException {
        try (ResultSet rs = sqlRunner.selectChapterCompletionById(accountId, chapterId)) {
            if (!rs.next()) {
                throw new CourseNotFoundException(Strings.CourseNotFound(chapterId));
            }
            return rs.getBoolean("isLocked");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hasNextChapter(int courseId, int chapterId) {
        try (ResultSet rs = sqlRunner.selectChaptersInCourseAfterId(courseId,chapterId)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ChapterObj> getFirstChaptersForAllCourse() {
        List<ChapterObj> chapters = new ArrayList<>();
        try (ResultSet rs = sqlRunner.selectFirstChaptersOfAllCourses()) {
            while (rs.next()) {
                chapters.add(new ChapterObj(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("courseId"),
                        rs.getString("description"),
                        false,
                        false));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }
}
