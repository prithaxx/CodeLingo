package CodeLinguists.codelingo.persistence.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        try (Connection connection = sqlRunner.connect()){
            PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.courseId, c.description, cc.isUnlocked, cc.isCompleted" +
                                                                    "FROM CHAPTER c" +
                                                                    "JOIN CHAPTER_COMPLETION cc ON c.id = cc.chapterId" +
                                                                    "WHERE c.courseId = ? AND cc.accountId = ?");
            ps.setInt(1, courseId);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            ps.close();

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
    public ChapterObj getChapterById(int chapterId, int courseId, int accountId) {
        ChapterObj chapter = null;
        try (Connection connection = sqlRunner.connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.courseId, c.description, cc.isUnlocked, cc.isCompleted " +
                                                                    "FROM CHAPTER c " +
                                                                    "LEFT JOIN CHAPTER_COMPLETION cc ON c.id = cc.chapterId and cc.accountId = ? " +
                                                                    "WHERE c.courseId = ? AND c.id = ?")) {
            ps.setInt(1, accountId);
            ps.setInt(2, courseId);
            ps.setInt(3, chapterId);

            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                chapter = new ChapterObj(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("courseId"),
                        rs.getString("description"),
                        rs.getBoolean("isUnlocked"),
                        rs.getBoolean("isCompleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;
    }
}
