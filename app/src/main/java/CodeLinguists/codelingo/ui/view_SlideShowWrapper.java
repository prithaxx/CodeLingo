package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.ui.slides.QuestionFragmentFactory;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */

public class view_SlideShowWrapper extends AppCompatActivity {

    private ISessionManager sessionManager;
    private IQuizIterator quizHandler;
    private QuestionFragmentFactory slideFactory;
    int lives = 3; //needs to be global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);

        this.sessionManager = SessionManager.newInstance();
        this.quizHandler = sessionManager.startQuiz();
        this.slideFactory = new QuestionFragmentFactory();
        changeSlide(quizHandler.nextQuestion()); //Load first slide
    }

    public void btnSlideShowNextOnClick(View v){
        changeSlide(quizHandler.nextQuestion());
    }

    public void btnSlideShowPrevOnClick(View v){
        changeSlide(quizHandler.prevQuestion());
    }

    private void changeSlide(QuizObj quiz) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerView, slideFactory.getInstance(quiz)).commit();
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