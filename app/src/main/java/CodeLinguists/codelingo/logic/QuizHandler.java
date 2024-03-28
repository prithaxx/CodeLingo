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
    public IQuizNavigation getQuiz(int courseId, int chapterId) {
        return quizFactory.getQuiz(this, quizData.getQuizByChapterId(chapterId));
    }

    /**
     * Compare the user's answer to the correct answer from the quiz
     *
     * @param quiz - The data for a single question.
     * @param answer - user provided input
     * @return True if the user's answer is the correct answer
     * @throws InputValidationException if the user fails to provide any answer. Users have to at least try.
     */
    @Override
    public boolean checkQuizAnswer(QuizObj quiz, String answer) throws InputValidationException {
        if (quiz == null) {
            throw new InputValidationException(Strings.QuizNotFound);
        }

        // When the quiz question is just information, no validation is needed,
        // So any user input is considered correct.
        // A question does not need any user input if either
        //      it is marked as having no answer
        //      Or there is no answer in the database
        // This is used for cases like feedback and information slides.
        if (!quiz.hasAnswer() || quiz.answer()==null || quiz.answer().isBlank()) {
            return true;
        }

        // If there is a correct answer, (quiz has an answer), then the user must be protected
        // From accidentally clicking "next" before providing an answer.
        // This error interrupts the question change process, giving the user another chance to input an answer
        if (answer==null || answer.isBlank()) {
            throw new InputValidationException(Strings.QuestionNotAnswered);
        }

        // Finally, we check if the user's answer correct
        return answer.equals(quiz.answer());
    }
}
