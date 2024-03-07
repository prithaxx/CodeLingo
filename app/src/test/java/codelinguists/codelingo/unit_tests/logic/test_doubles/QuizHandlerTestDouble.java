package codelinguists.codelingo.unit_tests.logic.test_doubles;

import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.QuizIterator;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;

public class QuizHandlerTestDouble implements IQuizHandler {
    public IQuizIterator getQuizResponse;
    public boolean checkQuizAnswerResponse;
    public boolean throwException;
    public int startQuizChapterOutput;

    public QuizHandlerTestDouble() {
        getQuizResponse = new QuizIterator(null, null);
        checkQuizAnswerResponse = false;
        startQuizChapterOutput=-1;
    }

    @Override
    public IQuizIterator getQuiz(int courseId, int chapterId) {
        startQuizChapterOutput = chapterId;
        return getQuizResponse;
    }

    @Override
    public boolean checkQuizAnswer(QuizObj quiz, String answer) throws InputValidationException {
        if (throwException) {
            throw new InputValidationException("Exception");
        }
        return checkQuizAnswerResponse;
    }
}
