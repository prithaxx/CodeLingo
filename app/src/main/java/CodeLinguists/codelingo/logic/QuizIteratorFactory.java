package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.QuizObj;

public class QuizIteratorFactory {
    IQuizIterator getQuiz(IQuizHandler quizHandler, List<QuizObj> activeQuiz ) {
        return new QuizIterator(quizHandler, activeQuiz);
    }
}
