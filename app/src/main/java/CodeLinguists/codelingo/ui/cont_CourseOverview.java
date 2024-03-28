package CodeLinguists.codelingo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.ChapterObj;
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
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        View v = inflater.inflate(R.layout.fragment_course_overview, container, false);

        TextView tv = v.findViewById(R.id.placeholder_course);
        tv.setText(course!=null ? course.getName() : getString(R.string.select_a_course));

        TextView tvProgressPercentage = v.findViewById(R.id.progress_percentage);
        int progressPercentage = 0;
        try {
            progressPercentage = sessionManager.calculateProgressPercentage();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        tvProgressPercentage.setText(String.format(Locale.getDefault(), "%d%% complete", progressPercentage));

        View chapterListView = v.findViewById(R.id.chapterList);
        List<ChapterObj> chapters = null;
        try {
            chapters = sessionManager.getActiveCourseChapters();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            chapters = new ArrayList<>(); //Empty list to avoid null point errors
        }

        // Set the adapter
        if (chapterListView instanceof RecyclerView recyclerView) {
            Context context = chapterListView.getContext();
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            recyclerView.setAdapter(new itm_ChapterRecyclerViewAdapter(chapters));
        }
        return v;
    }
}