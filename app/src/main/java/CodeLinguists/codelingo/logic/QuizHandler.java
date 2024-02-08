package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;

public class QuizHandler implements IQuizHandler{

    List<QuizObj> activeQuiz;
    int currentQuizCursor;

    public QuizHandler(List<QuizObj> activeQuiz) {
        this.activeQuiz=activeQuiz;
        currentQuizCursor=0;
    }

    @Override
    public QuizObj nextQuestion() {
        if (currentQuizCursor >= activeQuiz.size()) {
            return null;
        }
        return activeQuiz.get(currentQuizCursor++);
    }

    @Override
    public boolean hasNextQuestion() {
        return currentQuizCursor < activeQuiz.size();
    }

    @Override
    public QuizObj prevQuestion() {
        //cursor always points to the next quiz
        //so going backwards requires on offset of 1
        if (currentQuizCursor <= 1) {
            return null;
        }
        return activeQuiz.get(--currentQuizCursor-1);
    }

    @Override
    public boolean hasPrevQuestion() {
        return currentQuizCursor > 1;
    }
}
