package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;

public interface IQuizIterator {
    QuizObj startQuiz();

    /**
     * @return next slide regardless of input
     */
    QuizObj nextQuestion();

    boolean hasNextQuestion();

    QuizObj prevQuestion();

    boolean hasPrevQuestion();

    /**
     * @param input User's quiz response
     * @return nextQuestion() if no feedback required,
     * Else a QuizObj for feedback
     */
    QuizObj submit(String input) throws InputValidationException;
}
