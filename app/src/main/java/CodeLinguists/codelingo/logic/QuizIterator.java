package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;

public class QuizIterator implements IQuizIterator {

    private IQuizHandler quizHandler;
    List<QuizObj> activeQuiz;
    int currentQuizCursor;
    private boolean inFeedback; //if showing quiz slide or feedback slide

    public QuizIterator(List<QuizObj> activeQuiz) {
        this(new QuizHandler(), activeQuiz);
    }

    public QuizIterator(IQuizHandler quizHandler, List<QuizObj> activeQuiz) {
        this.quizHandler = quizHandler;
        this.activeQuiz = activeQuiz==null ? new ArrayList<>() : activeQuiz;
        this.currentQuizCursor = 0;
        this.inFeedback = false;
    }

    @Override
    public QuizObj startQuiz() {
        currentQuizCursor = 0;
        return nextQuestion();
    }

    @Override
    public QuizObj nextQuestion() {
        inFeedback = false;

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
        inFeedback = false;
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

    @Override
    public QuizObj submit(String input) {
        QuizObj current = activeQuiz.get(currentQuizCursor-1);
        if ( current.answer() == null || !current.hasAnswer() || inFeedback) {
            return nextQuestion();
        }

        QuestionTypes feedbackType = quizHandler.checkQuizAnswer(current, input) ? QuestionTypes.FEEDBACK_PASSED : QuestionTypes.FEEDBACK_FAILED;
        inFeedback = true;
        return QuizObj.asFeedback(current, feedbackType);
    }

    public int cursorPos() {
        return currentQuizCursor;
    }

}
