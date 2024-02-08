package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizDataStub implements IQuizData {
    private List<QuizObj> quizList;

    public QuizDataStub() {
        quizList = new ArrayList<>();
        quizList.add(new QuizObj(1,1,"Placeholder 1", null, null, null));
        quizList.add(new QuizObj(2,1,"Placeholder 2", null, null, null));
        quizList.add(new QuizObj(3,1,"Placeholder 3", null, null, null));
    }

    public QuizDataStub(List<QuizObj> quizList) {
        this.quizList = quizList;
    }

    @Override
    public List<QuizObj> getQuizByChapterId(int chapterId) {
        return quizList.stream()
                .filter(quizObj -> quizObj.getChapterId()==chapterId)
                .collect(Collectors.toList());
    }

    @Override
    public QuizObj getQuizById(int quizId, int chapterId) {
        return quizList.stream()
                .filter(quizObj -> quizObj.getChapterId()==chapterId && quizObj.getId()==quizId)
                .findFirst()
                .orElse(null);
    }
}
