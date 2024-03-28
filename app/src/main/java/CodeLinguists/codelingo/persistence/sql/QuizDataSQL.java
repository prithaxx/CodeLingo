package CodeLinguists.codelingo.persistence.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

public class QuizDataSQL implements IQuizData {
    private final ISqlRunner sqlRunner;

    public QuizDataSQL(ISqlRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @Override
    public List<QuizObj> getQuizByChapterId(int chapterId){
        List<QuizObj> quizzes = new ArrayList<>();

        try (ResultSet rs = sqlRunner.selectQuizByChapterId(chapterId)){
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
}
