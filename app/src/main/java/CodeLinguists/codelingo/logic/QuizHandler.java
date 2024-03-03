package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizHandler implements IQuizHandler{
    IQuizData quizData;

    public QuizHandler(boolean forProduction) {
        this.quizData = Services.getQuizData(forProduction);
    }

    public QuizHandler(IQuizData quizData) {
        this.quizData = quizData;
    }

    @Override
    public IQuizIterator getQuiz(int courseId, int chapterId) {
        return new QuizIterator(quizData.getQuizByChapterId(chapterId));
    }

    @Override
    public boolean checkQuizAnswer(QuizObj quiz, String answer) {
        if (answer==null || answer.isBlank()) {
            throw new InputValidationException(Strings.QuestionNotAnswered);
        }
        return answer.equals(quiz.answer());
    }
}
