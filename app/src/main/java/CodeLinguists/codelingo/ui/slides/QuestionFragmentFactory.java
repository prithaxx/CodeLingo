package CodeLinguists.codelingo.ui.slides;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.ui.ui_exceptions.SlideTypeNotHandledException;

public class QuestionFragmentFactory {
    public QuizSlide getInstance(QuizObj quiz) throws SlideTypeNotHandledException {
        return switch (quiz.type()) {
            case TEXT -> new TextSlide(quiz);
            case MULTI_CHOICE -> new MultiChoiceSlide(quiz);
            case SHORT_ANSWER -> new ShortAnswerSlide(quiz);
            case FEEDBACK_FAILED, FEEDBACK_PASSED -> new FeedbackSlide(quiz);
            default -> throw new SlideTypeNotHandledException(Strings.SlideNotSupported);
        };
    }
}
