package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;

public class ShortAnswerSlide extends QuizSlide {
    private String userAnswer = null;
    public ShortAnswerSlide(QuizObj quiz) {
        super(R.layout.fragment_slide_short_answer, quiz);
    }

    @Override
    public String getInput() {
        return userAnswer;
    }

    public void populateView(View v) {
        TextView titleTextView = v.findViewById(R.id.title_text);
        titleTextView.setText(getString(R.string.SlideTitlePlaceholder, quiz.id()));

        TextView promptTextView = v.findViewById(R.id.prompt_text);
        promptTextView.setText(quiz.prompt());

        EditText answerInput = v.findViewById(R.id.answer_input);
        Button submitButton = v.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(view -> {
            userAnswer = answerInput.getText().toString().trim();
        }) ;
    }

    private void setUserAnswer(String answer) {
        this.userAnswer = answer;
    }
}