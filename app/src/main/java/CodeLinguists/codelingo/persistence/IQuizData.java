package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.QuizObj;

public interface IQuizData {
    List<QuizObj> getQuizByChapterId(int id);
}
