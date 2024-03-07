package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.ui.slides.QuestionFragmentFactory;
import CodeLinguists.codelingo.ui.slides.QuizSlide;
import CodeLinguists.codelingo.ui.ui_exceptions.SlideTypeNotHandledException;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */

public class view_SlideShowWrapper extends AppCompatActivity {

    private ISessionManager sessionManager;
    private IQuizIterator quizIterator;
    private QuestionFragmentFactory slideFactory;
    private QuizSlide currentSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);

        this.sessionManager = Services.getSessionManager();
        try {
            this.quizIterator = sessionManager.startQuiz();
        } catch (NoItemSelectedException e) {
            throw new RuntimeException(e);
        }
        this.slideFactory = new QuestionFragmentFactory();
        try {
            changeSlide(quizIterator.startQuiz()); //Load first slide
        } catch (SlideTypeNotHandledException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSlideShowNextOnClick(View v) throws SlideTypeNotHandledException, InputValidationException {
        changeSlide(quizIterator.submit(currentSlide.getInput()));
    }

    public void btnSlideShowPrevOnClick(View v) throws SlideTypeNotHandledException {
        changeSlide(quizIterator.prevQuestion());
    }
    public void btnSlideShowSkipOnClick(View v) throws SlideTypeNotHandledException {
        changeSlide(quizIterator.nextQuestion());
    }

    private void changeSlide(QuizObj quiz) throws SlideTypeNotHandledException {
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
            next.setOnClickListener(v -> {
                try {
                    btnSlideShowNextOnClick(v);
                } catch (SlideTypeNotHandledException | InputValidationException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            if (!quizIterator.hasNextQuestion()) {
                next.setText(R.string.done);
                next.setOnClickListener(this::finishQuiz);
            } else {
                next.setText(R.string.next);
                next.setOnClickListener(v -> {
                    try {
                        btnSlideShowNextOnClick(v);
                    } catch (SlideTypeNotHandledException | InputValidationException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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