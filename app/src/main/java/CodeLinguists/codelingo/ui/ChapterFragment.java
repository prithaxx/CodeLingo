package CodeLinguists.codelingo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;

/**
 * A fragment representing a list of Items.
 */
public class ChapterFragment extends Fragment {
    private final int mColumnCount = 2;
    private List<ChapterObj> chapters;
    private ISessionManager sessionManager;

    public ChapterFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        sessionManager = Services.getSessionManager();
        try {
            chapters = sessionManager.getActiveCourseChapters();
        } catch (CourseNotFoundException | AccountPermissionException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            chapters = new ArrayList<>();
        }
        for (int i = 0; i < 60; i++) {
            chapters.add(new ChapterObj(1, "test", 2, "Hello", true, true));
            chapters.add(new ChapterObj(2, "test", 2, "Hello", true, true));
            chapters.add(new ChapterObj(3, "test", 2, "Hello", true, true));
        }

        // Set the adapter
        if (view instanceof RecyclerView recyclerView) {
            Context context = view.getContext();
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            recyclerView.setAdapter(new ChapterRecyclerViewAdapter(this.chapters));
        }
        return view;
    }
}