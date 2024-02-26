package CodeLinguists.codelingo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.SessionManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class cont_ChapterClickable extends Fragment {
    public cont_ChapterClickable() {
        // Required empty public constructor
    }

    public static cont_ChapterClickable newInstance() {
        return new cont_ChapterClickable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //this.sessionManager = SessionManager.newInstance();
        //CourseObj course = sessionManager.getActiveCourse();

        View v = inflater.inflate(R.layout.fragment_chapter_clickable, container, false);

        TextView tv = v.findViewById(R.id.tvChapterName);
        //tv.setText(course!=null ? course : "Select a course");
        tv.setText("Example 1");

        return v;
    }
}
