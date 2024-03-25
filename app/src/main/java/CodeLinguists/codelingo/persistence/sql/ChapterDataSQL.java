package CodeLinguists.codelingo.persistence.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.persistence.IChapterData;
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
            sqlRunner.setChapterComplete(accountId, chapterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked) {
        try {
            sqlRunner.setChapterUnlocked(accountId, chapterId, unlocked);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
