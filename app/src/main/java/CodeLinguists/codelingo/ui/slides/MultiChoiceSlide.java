package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;

public class MultiChoiceSlide extends QuizSlide {
    private String userSelection = null;
    public MultiChoiceSlide(QuizObj quiz) {
        super(R.layout.fragment_slide_multiple_choice, quiz);
    }


    @Override
    public String getInput() {
        return userSelection;
    }

    private void highlightSelection(View v, Button selectedButton) {
        Button[] buttons = new Button[]{
                v.findViewById(R.id.optionA_button),
                v.findViewById(R.id.optionB_button),
                v.findViewById(R.id.optionC_button),
                v.findViewById(R.id.optionD_button)
        };

        int defaultColor = v.getResources().getColor(R.color.defaultButtonColor, v.getContext().getTheme());
        for (Button button : buttons) {
            button.setBackgroundColor(defaultColor);
        }

        int highlightColor = v.getResources().getColor(R.color.highlightButtonColor, v.getContext().getTheme());
        selectedButton.setBackgroundColor(highlightColor);
    }

    @Override
    public void populateView(View v) {
        TextView titleTextView = v.findViewById(R.id.title_text);
        titleTextView.setText(getString(R.string.SlideTitlePlaceholder, quiz.id()));

        TextView promptTextView = v.findViewById(R.id.prompt_text);
        promptTextView.setText(quiz.prompt());

        List<String> allAnswers = new ArrayList<>(quiz.wrongAnswers());
        allAnswers.add(quiz.answer());
        Collections.shuffle(allAnswers);

        Button[] buttons = new Button[]{
                v.findViewById(R.id.optionA_button),
                v.findViewById(R.id.optionB_button),
                v.findViewById(R.id.optionC_button),
                v.findViewById(R.id.optionD_button)
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(allAnswers.get(i));
            buttons[i].setOnClickListener(view -> {
                setUserSelection(((Button)view).getText().toString());
                highlightSelection(v,(Button)view);
            });
        }
    }

    private void setUserSelection(String selection) {
        this.userSelection = selection;
    }
}
