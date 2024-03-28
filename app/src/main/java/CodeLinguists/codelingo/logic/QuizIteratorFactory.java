package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.QuizObj;

public class QuizIteratorFactory {
    IQuizNavigation getQuiz(IQuizHandler quizHandler, List<QuizObj> activeQuiz ) {
        return new QuizNavigation(quizHandler, activeQuiz);
    }
}
