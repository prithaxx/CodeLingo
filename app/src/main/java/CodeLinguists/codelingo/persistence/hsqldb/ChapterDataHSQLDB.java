package CodeLinguists.codelingo.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.persistence.IChapterData;

public class ChapterDataHSQLDB implements IChapterData {
    private final String dbPath;

    public ChapterDataHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<ChapterObj> getChapterByCourseId(int courseId) {
        List<ChapterObj> chapters = new ArrayList<>();

        try (Connection connection = connect()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CHAPTER WHERE courseId = ?");
            ps.setInt(1, courseId);
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
    public ChapterObj getChapterById(int chapterId, int courseId) {
        ChapterObj chapter = null;
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM CHAPTER WHERE courseId = ? AND id = ?")) {
            ps.setInt(1, courseId);
            ps.setInt(2, chapterId);
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
