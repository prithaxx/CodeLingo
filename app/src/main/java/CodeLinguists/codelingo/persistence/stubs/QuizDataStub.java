package CodeLinguists.codelingo.persistence.stubs;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizDataStub implements IQuizData {
    private final List<QuizObj> quizList;

    public QuizDataStub() {
        quizList = new ArrayList<>();
        quizList.add(new QuizObj(1,1, QuestionType.TEXT,"TEXT Placeholder 1", false,null, null, null, null, null));
        quizList.add(new QuizObj(2,1, QuestionType.TEXT,"TEXT Placeholder 2", false, null, null, null, null, null));
        quizList.add(new QuizObj(3,1, QuestionType.MULTI_CHOICE,"MULTI_CHOICE Placeholder 1", true, "Correct answer", null, Arrays.asList("Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"), "id 3: Incorrect answer feedback placeholder" , "id 3: Correct answer feedback placeholder"));
        quizList.add(new QuizObj(4,1, QuestionType.MULTI_CHOICE,"MULTI_CHOICE Placeholder 2", true, "Correct answer", null, Arrays.asList("Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"), "id 4: Incorrect answer feedback placeholder" , "id 4: Correct answer feedback placeholder"));
        quizList.add(new QuizObj(5,1, QuestionType.SHORT_ANSWER,"SHORT_ANSWER Placeholder 1",true,"a",null,null,"id 5: Incorrect answer feedback placeholder" , "id 5: Correct answer feedback placeholder"));
        quizList.add(new QuizObj(6,1, QuestionType.SHORT_ANSWER,"SHORT_ANSWER Placeholder 2",true,"a",null,null,"id 6: Incorrect answer feedback placeholder" , "id 6: Correct answer feedback placeholder"));
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

    public List<QuizObj> getQuizList(){
        return quizList;
    }
}
