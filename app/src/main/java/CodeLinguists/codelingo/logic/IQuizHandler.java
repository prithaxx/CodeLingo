package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;

public interface IQuizHandler {
    IQuizNavigation getQuiz(int courseId, int chapterId);
    boolean checkQuizAnswer(QuizObj quiz, String answer) throws InputValidationException;
}
