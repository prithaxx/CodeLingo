package CodeLinguists.codelingo.ui.slides;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;

public class MultiChoiceSlide extends QuizSlide {
    private String userSelection = null;
    public MultiChoiceSlide(QuizObj quiz) {
        super(R.layout.fragment_slide_multiple_choice, quiz);
    }


    //TODO extract user input
    @Override
    public String getInput() {
        return userSelection;
    }

    @Override
    public void populateView(View v) {
        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(getString(R.string.SlideTitlePlaceholder, quiz.id()));

        tv = v.findViewById(R.id.prompt_text);
        tv.setText(quiz.prompt());

        Button optionAButton = v.findViewById(R.id.optionA_button);
        optionAButton.setText(R.string.optionA);
        optionAButton.setOnClickListener(view -> setUserSelection(v.getResources().getString(R.string.optionA)));

        Button optionBButton = v.findViewById(R.id.optionB_button);
        optionBButton.setText(R.string.optionB);
        optionBButton.setOnClickListener(view -> setUserSelection(v.getResources().getString(R.string.optionB)));

        Button optionCButton = v.findViewById(R.id.optionC_button);
        optionCButton.setText(R.string.optionC);
        optionCButton.setOnClickListener(view -> setUserSelection(v.getResources().getString(R.string.optionC)));

        Button optionDButton = v.findViewById(R.id.optionD_button);
        optionDButton.setText(R.string.optionD);
        optionDButton.setOnClickListener(view -> setUserSelection(v.getResources().getString(R.string.optionD)));
    }

    public void onClickOption(View view){
        
    }

    private void setUserSelection(String selection) {
        this.userSelection = selection;
    }
}
