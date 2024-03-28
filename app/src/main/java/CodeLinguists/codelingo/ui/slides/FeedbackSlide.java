package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuestionType;
import CodeLinguists.codelingo.dso.QuizObj;

public class FeedbackSlide extends InformationSlide{
    private final boolean isCorrect;
    public FeedbackSlide(QuizObj quiz) {
        super(
                quiz.type().equals(QuestionType.FEEDBACK_PASSED)
                        ? R.layout.fragment_slide_correct_answer
                        : R.layout.fragment_slide_wrong_answer,
                quiz);
        this.isCorrect = quiz.type().equals(QuestionType.FEEDBACK_PASSED);
    }

    @Override
    public void populateView(View v) {
        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(this.isCorrect ? R.string.correct_answer_title : R.string.wrong_answer_title);

        tv = v.findViewById(R.id.body_text);
        tv.setText(this.isCorrect ? quiz.correctFeedback() : quiz.wrongFeedback());
    }
}
