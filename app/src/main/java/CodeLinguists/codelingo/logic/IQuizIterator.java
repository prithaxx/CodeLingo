package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.QuizObj;

public interface IQuizIterator {

    QuizObj nextQuestion();

    boolean hasNextQuestion();

    QuizObj prevQuestion();

    boolean hasPrevQuestion();
}
