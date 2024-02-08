package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface IQuizData {
    QuizObj getQuizByChapter(ChapterObj chapter);
    QuizObj getQuizByChapterId(int id);
    List<ChapterObj> getChapters();
    QuizObj getQuizById(int quizId, int chapterId);
}
