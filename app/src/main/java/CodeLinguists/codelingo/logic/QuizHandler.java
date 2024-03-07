package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IQuizData;

public class QuizHandler implements IQuizHandler{
    private final IQuizData quizData;
    private final QuizIteratorFactory quizFactory;
    public QuizHandler(IQuizData quizData, QuizIteratorFactory quizFactory) {
        this.quizData = quizData;
        this.quizFactory = quizFactory;
    }

    @Override
    public IQuizIterator getQuiz(int courseId, int chapterId) {
        return quizFactory.getQuiz(this, quizData.getQuizByChapterId(chapterId));
    }

    @Override
    public boolean checkQuizAnswer(QuizObj quiz, String answer) throws InputValidationException {
        if (quiz == null) {
            throw new InputValidationException(Strings.QuizNotFound);
        }

        if (!quiz.hasAnswer() || quiz.answer()==null || quiz.answer().isBlank()) {
            return true;
        }

        if (answer==null || answer.isBlank()) {
            throw new InputValidationException(Strings.QuestionNotAnswered);
        }

        return answer.equals(quiz.answer());
    }
}
