package CodeLinguists.codelingo.ui.slides;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.exceptions.SlideTypeNotHandledException;

public class QuestionFragmentFactory {
    public QuizSlide getInstance(QuizObj quiz) {
        return switch (quiz.type()) {
            case TEXT -> new TextSlide(quiz);
            case MULTI_CHOICE -> new MultiChoiceSlide(quiz);
            default -> throw new SlideTypeNotHandledException(Strings.SlideNotSupported);
        };
    }
}
