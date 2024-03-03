package CodeLinguists.codelingo.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizDataHSQLDB implements IQuizData {
    private final String dbPath;

    public QuizDataHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<QuizObj> getQuizByChapterId(int chapterId){
        List<QuizObj> quizzes = new ArrayList<>();

        try (Connection connection = connect()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM QUIZ WHERE chapterId = ? ORDER BY id");
            ps.setInt(1, chapterId);
            ResultSet rs = ps.executeQuery();
            ps.close();

            while (rs.next()) {

                int id = rs.getInt("id");
                String typeString = rs.getString("type");
                QuestionType type = QuestionType.valueOf(typeString);
                String prompt = rs.getString("prompt");
                boolean hasAnswer = rs.getBoolean("hasAnswer");
                String answer = rs.getString("answer");
//                List<String> hints = rs.getString("hints") != null ? Arrays.stream(rs.getString("hints").split(",")).collect(Collectors.toList()) : new ArrayList<>();
                List<String> wrongAnswers = rs.getString("wrongAnswers") != null ? Arrays.stream(rs.getString("wrongAnswers").split(",")).collect(Collectors.toList()) : new ArrayList<>();
                String wrongFeedback = rs.getString("wrongFeedback");
                String correctFeedback = rs.getString("correctFeedback");

                QuizObj quiz = new QuizObj(id,chapterId, type, prompt, hasAnswer, answer, null, wrongAnswers, wrongFeedback, correctFeedback);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public QuizObj getQuizById(int quizId, int chapterId){
        QuizObj quiz = null;

        try (Connection connection = connect()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM QUIZ WHERE chapterId = ? AND quizId = ?");
            ps.setInt(1, chapterId);
            ps.setInt(2, quizId);
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                int id = rs.getInt("id");
                QuestionType type = QuestionType.valueOf(rs.getString("type"));
                String prompt = rs.getString("prompt");
                boolean hasAnswer = rs.getBoolean("hasAnswer");
                String answer = rs.getString("answer");
                List<String> hints = rs.getString("hints") != null ? Arrays.stream(rs.getString("hints").split(",")).collect(Collectors.toList()) : new ArrayList<>();
                List<String> wrongAnswers = rs.getString("wrongAnswers") != null ? Arrays.stream(rs.getString("wrongAnswers").split(",")).collect(Collectors.toList()) : new ArrayList<>();
                String wrongFeedback = rs.getString("wrongFeedback");
                String correctFeedback = rs.getString("correctFeedback");

                quiz = new QuizObj(id,chapterId, type, prompt, hasAnswer, answer, hints, wrongAnswers, wrongFeedback, correctFeedback);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }
}
