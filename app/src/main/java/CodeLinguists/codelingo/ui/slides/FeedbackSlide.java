package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;

public class FeedbackSlide extends QuizSlide{
    private final boolean isCorrect;
    public FeedbackSlide(QuizObj quiz) {
        super(
                quiz.type().equals(QuestionTypes.FEEDBACK_PASSED)
                        ? R.layout.fragment_slide_correct_answer
                        : R.layout.fragment_slide_wrong_answer,
                quiz);
        this.isCorrect = quiz.type().equals(QuestionTypes.FEEDBACK_PASSED);
    }
    @Override
    public String getInput() {
        //user will always return empty response
        //This is not a Liskov substitution violation.
        //The user is literally returning ""
        return "";
    }

    @Override
    public void populateView(View v) {
        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(this.isCorrect ? R.string.correct_answer_title : R.string.wrong_answer_title);

        tv = v.findViewById(R.id.body_text);
        tv.setText(this.isCorrect ? quiz.correctFeedback() : quiz.wrongFeedback());
    }
}
