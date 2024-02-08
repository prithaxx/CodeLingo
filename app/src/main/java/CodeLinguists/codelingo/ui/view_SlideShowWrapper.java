package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */

public class view_SlideShowWrapper extends AppCompatActivity {

    int lives = 3; //needs to be global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);
        TextView prompt = (TextView)findViewById(R.id.txtQuizPrompt);
        //get quiz prompt and set
        Button option1 = (Button)findViewById(R.id.btnQuiz1);
        //Get quiz answer and set
        //option1.setText();
        Button option2 = (Button)findViewById(R.id.btnQuiz2);
        Button option3 = (Button)findViewById(R.id.btnQuiz3);
        Button option4 = (Button)findViewById(R.id.btnQuiz4);

    }

    public void btnSlideShowNextOnClick(View v){
        startActivity(new Intent(this, view_SlideShowWrapper.class));
    }

    public void btnSlideShowPrevOnClick(View v){
        finish();
    }

    public void btnQuiz1OnClick(View v){
        TextView prompt = (TextView)findViewById(R.id.txtQuizPrompt);
        prompt.setText("Correct!");
    }

    public void btnQuiz234OnClick(View v){
        TextView prompt = (TextView)findViewById(R.id.txtQuizPrompt);
        prompt.setText("Wrong!");
        lives--;
        if(lives == 2) {
            ImageView life = (ImageView) findViewById(R.id.imgHeart3);
            life.setVisibility(View.GONE);
        } else if(lives == 1) {
            ImageView life = (ImageView) findViewById(R.id.imgHeart2);
            life.setVisibility(View.GONE);
        } else {
            ImageView life = (ImageView) findViewById(R.id.imgHeart1);
            life.setVisibility(View.GONE);
            prompt.setText("Too bad, please reattempt");
        }
    }



}