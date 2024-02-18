package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.QuizObj;

public interface IQuizHandler {
    IQuizIterator getQuiz(int courseId, int chapterId);
    boolean checkQuizAnswer(QuizObj quiz, String answer);
    
}
