package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */

public class view_SlideShowWrapper extends AppCompatActivity {

    ISessionManager sessionManager;
    int lives = 3; //needs to be global
    private IQuizHandler quizHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);

        sessionManager = SessionManager.newInstance();
        this.quizHandler = sessionManager.startQuiz();
        changeSlide(true);
    }

    public void btnSlideShowNextOnClick(View v){
        changeSlide(true);
    }

    public void btnSlideShowPrevOnClick(View v){
        changeSlide(false);
    }

    /**
     * @param to_next - go to next slide on true, go to previous slide on false.
     */
    private void changeSlide(boolean to_next) {
        QuizObj quiz = to_next ? quizHandler.nextQuestion() : quizHandler.prevQuestion();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerView, cont_SlideText.newInstance("Quiz #"+quiz.id(), quiz.prompt())).commit();
        toggleNavButtons();
    }

    private void toggleNavButtons() {
        Button next = findViewById(R.id.btnSlideShowNext);
        Button prev = findViewById(R.id.btnSlideShowPrev);

        if (!quizHandler.hasNextQuestion()) {
            next.setText("Done!");
            next.setOnClickListener(this::finishQuiz);
        } else {
            next.setText("Next");
            next.setOnClickListener(this::btnSlideShowNextOnClick);
        }

        if (!quizHandler.hasPrevQuestion()) {
            prev.setVisibility(View.INVISIBLE);
        } else {
            prev.setVisibility(View.VISIBLE);
        }
    }

    private void finishQuiz(View v) {
        Intent intent = new Intent(this, view_CourseOverview.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}