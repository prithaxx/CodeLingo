package CodeLinguists.codelingo.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.logic.ISessionManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cont_CourseOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cont_CourseOverview extends Fragment {

    private ISessionManager sessionManager;

    public cont_CourseOverview() {
        // Required empty public constructor
    }

    public static cont_CourseOverview newInstance() {
        return new cont_CourseOverview();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.sessionManager = Services.getSessionManager();
        CourseObj course = null;
        try {
            course = sessionManager.getActiveCourse();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        View v = inflater.inflate(R.layout.fragment_course_overview, container, false);

        TextView tv = v.findViewById(R.id.placeholder_course);
        tv.setText(course!=null ? course.name() : getString(R.string.select_a_course));

        TextView tvProgressPercentage = v.findViewById(R.id.progress_percentage);
        int progressPercentage = 0;
        try {
            progressPercentage = sessionManager.calculateProgressPercentage(course);
        } catch (CourseNotFoundException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        tvProgressPercentage.setText(String.format(Locale.getDefault(), "%d%% complete", progressPercentage));


        //We know this is hard-coded, will be resolve in iteration 3
        View b = v.findViewById(R.id.rectangle_1);
        b.setOnClickListener(this::tileOnclick0);
        b = v.findViewById(R.id.rectangle_2);
        b.setOnClickListener(this::tileOnclick1);
        b = v.findViewById(R.id.rectangle_3);
        b.setOnClickListener(this::tileOnclick2);
        b = v.findViewById(R.id.rectangle_4);
        b.setOnClickListener(this::tileOnclick3);

        return v;
    }

    public void tileOnclick0(View v) {
        startQuiz(1);
    }
    public void tileOnclick1(View v) {
        startQuiz(1);
    }
    public void tileOnclick2(View v) {
        startQuiz(1);
    }
    public void tileOnclick3(View v) {
        startQuiz(1);
    }

    public void startQuiz(int index) {
        sessionManager.setActiveChapter(index);
        Intent intent = new Intent(requireContext(), view_SlideShowWrapper.class);
        startActivity(intent);
    }
}