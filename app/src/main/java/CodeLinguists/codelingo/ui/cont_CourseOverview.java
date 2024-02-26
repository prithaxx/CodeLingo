package CodeLinguists.codelingo.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;

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
        this.sessionManager = SessionManager.newInstance();
        CourseObj course = sessionManager.getActiveCourse();

        View v = inflater.inflate(R.layout.fragment_course_overview, container, false);

        TextView tv = v.findViewById(R.id.placeholder_course);
        tv.setText(course!=null ? course.name() : "Select a course");

        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.fragmentContainerView3, cont_CourseOverview.newInstance()).commit();

//        View b = v.findViewById(R.id.rectangle_1);
//        b.setOnClickListener(this::tileOnclick0);
//        b = v.findViewById(R.id.rectangle_2);
//        b.setOnClickListener(this::tileOnclick1);
//        b = v.findViewById(R.id.rectangle_3);
//        b.setOnClickListener(this::tileOnclick2);
//        b = v.findViewById(R.id.rectangle_4);
//        b.setOnClickListener(this::tileOnclick3);

        return v;
    }

    public void tileOnclick0(View v) {
        startQuiz(0);
    }
    public void tileOnclick1(View v) {
        startQuiz(1);
    }
    public void tileOnclick2(View v) {
        startQuiz(2);
    }
    public void tileOnclick3(View v) {
        startQuiz(3);
    }

    public void startQuiz(int index) {
        sessionManager.setActiveChapter(index);
        Intent intent = new Intent(requireContext(), view_SlideShowWrapper.class);
        startActivity(intent);
    }
}