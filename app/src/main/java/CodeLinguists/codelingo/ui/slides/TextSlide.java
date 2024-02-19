package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuestionTypes;
import CodeLinguists.codelingo.dso.QuizObj;

public class TextSlide extends QuizSlide{
    public TextSlide(QuizObj quiz) {
        super(R.layout.fragment_slide_text, quiz);
    }

    @Override
    public String getInput() {
        return null;
    }

    @Override
    public void populateView(View v) {
        if (quiz == null) {
//            throw new NullPointerException("System Error: no quiz found");
            this.quiz = new QuizObj(-999, -999, QuestionTypes.TEXT, "ERROR", "ERROR", null, null);
        }
        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(getString(R.string.SlideTitlePlaceholder, quiz.id()));

        tv = v.findViewById(R.id.prompt_text);
        tv.setText(quiz.prompt());
    }
}
