package CodeLinguists.codelingo.ui.slides;

import CodeLinguists.codelingo.dso.QuizObj;

public abstract class InformationSlide extends QuizSlide{

    public InformationSlide(int fragmentReference, QuizObj quiz) {
        super(fragmentReference, quiz);
    }

    @Override
    public String getInput(){
        // Since these slide types don't have an input field to collect user input,
        // And since the SlideShowWrapper needs to getInput from all slides to handle validation,
        // We return null to indicate that there was no answer provided
        return null;
    }
}
