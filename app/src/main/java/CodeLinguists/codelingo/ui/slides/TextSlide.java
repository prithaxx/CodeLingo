package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
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
        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(getString(R.string.SlideTitlePlaceholder, quiz.id()));

        tv = v.findViewById(R.id.prompt_text);
        tv.setText(quiz.prompt());
    }
}
