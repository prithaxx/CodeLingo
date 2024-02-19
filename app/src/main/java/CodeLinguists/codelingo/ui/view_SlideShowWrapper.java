package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.ui.slides.QuestionFragmentFactory;
import CodeLinguists.codelingo.ui.slides.QuizSlide;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */

public class view_SlideShowWrapper extends AppCompatActivity {

    private ISessionManager sessionManager;
    private IQuizIterator quizIterator;
    private QuestionFragmentFactory slideFactory;
    int lives = 3; //needs to be global
    private QuizSlide currentSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);

        this.sessionManager = SessionManager.newInstance();
        this.quizIterator = sessionManager.startQuiz();
        this.slideFactory = new QuestionFragmentFactory();
        changeSlide(quizIterator.startQuiz()); //Load first slide
    }

    public void btnSlideShowNextOnClick(View v){
        try {
            changeSlide(quizIterator.submit(currentSlide.getInput()));
        } catch (InputValidationException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnSlideShowPrevOnClick(View v){
        changeSlide(quizIterator.prevQuestion());
    }
    public void btnSlideShowSkipOnClick(View v) {
        changeSlide(quizIterator.nextQuestion());
    }

    private void changeSlide(QuizObj quiz) {
        this.currentSlide = slideFactory.getInstance(quiz);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerView, this.currentSlide).commit();
        toggleNavButtons();
    }

    private void toggleNavButtons() {
        Button next = findViewById(R.id.btnSlideShowNext);
        Button prev = findViewById(R.id.btnSlideShowPrev);
        QuizObj quizData = currentSlide.getQuiz();

        if (quizData.hasAnswer()) {
            next.setText(R.string.check_answer);
            next.setOnClickListener(this::btnSlideShowNextOnClick);
        } else {
            if (!quizIterator.hasNextQuestion()) {
                next.setText(R.string.done);
                next.setOnClickListener(this::finishQuiz);
            } else {
                next.setText(R.string.next);
                next.setOnClickListener(this::btnSlideShowNextOnClick);
            }
        }

        if (!quizIterator.hasPrevQuestion()) {
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