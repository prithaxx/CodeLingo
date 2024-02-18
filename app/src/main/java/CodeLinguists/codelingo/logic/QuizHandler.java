package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizHandler implements IQuizHandler{
    IQuizData quizData;

    public QuizHandler() {
        this.quizData = Services.getQuizData();
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
        return !answer.isBlank() && answer.equals(quiz.answer());
    }
}
