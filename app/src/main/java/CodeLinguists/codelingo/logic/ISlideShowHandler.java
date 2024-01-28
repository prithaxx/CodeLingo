package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface ISlideShowHandler {
    public List<QuizObj> getQuizByChapter(ChapterObj chapter);
}
