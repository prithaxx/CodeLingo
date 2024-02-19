package CodeLinguists.codelingo.ui.slides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import CodeLinguists.codelingo.dso.QuizObj;

public abstract class QuizSlide extends Fragment {
    protected QuizObj quiz;
    private final int FRAGMENT_REFERENCE;

    protected QuizSlide(int fragmentReference, QuizObj quiz) {
        this.FRAGMENT_REFERENCE = fragmentReference;
        this.quiz=quiz;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(this.FRAGMENT_REFERENCE, container, false);
        populateView(v);
        return v;

    }

    /**
     * Fetch user input from fragment.
     *
     * @return user's response to quiz slide.
     */
    public abstract String getInput();

    /**
     * Called on fragment creation.
     * Should be used to update any values within the fragment.
     *
     * @param v the new view created from the fragment reference
     */
    public abstract void populateView(View v);

    public QuizObj getQuiz() {
        return this.quiz;
    }
}
