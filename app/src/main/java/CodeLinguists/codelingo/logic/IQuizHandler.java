package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface IQuizHandler {

    QuizObj nextQuestion();

    boolean hasNextQuestion();

    QuizObj prevQuestion();

    boolean hasPrevQuestion();
}
