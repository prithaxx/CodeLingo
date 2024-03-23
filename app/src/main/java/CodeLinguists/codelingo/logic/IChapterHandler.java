package CodeLinguists.codelingo.logic;

public interface IChapterHandler {
    IQuizIterator getQuiz(int courseId, int chapterId);
}
