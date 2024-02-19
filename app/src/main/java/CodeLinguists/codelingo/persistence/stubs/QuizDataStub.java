package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizDataStub implements IQuizData {
    private final List<QuizObj> quizList;

    public QuizDataStub() {
        quizList = new ArrayList<>();
        quizList.add(new QuizObj(1,1, QuestionTypes.TEXT,"TEXT Placeholder 1", null, null, null));
        quizList.add(new QuizObj(2,1, QuestionTypes.TEXT,"TEXT Placeholder 2", null, null, null));
        quizList.add(new QuizObj(3,1, QuestionTypes.MULTI_CHOICE,"MULTI_CHOICE Placeholder 1", null, null, null));
        quizList.add(new QuizObj(4,1, QuestionTypes.MULTI_CHOICE,"MULTI_CHOICE Placeholder 2", null, null, null));
    }

    public QuizDataStub(List<QuizObj> quizList) {
        this.quizList = quizList;
    }

    @Override
    public List<QuizObj> getQuizByChapterId(int chapterId) {
        return quizList.stream()
                .filter(quizObj -> quizObj.chapterId()==chapterId)
                .collect(Collectors.toList());
    }

    @Override
    public QuizObj getQuizById(int quizId, int chapterId) {
        return quizList.stream()
                .filter(quizObj -> quizObj.chapterId()==chapterId && quizObj.id()==quizId)
                .findFirst()
                .orElse(null);
    }
}
