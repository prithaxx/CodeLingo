package CodeLinguists.codelingo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import CodeLinguists.codelingo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cont_SlideText#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cont_SlideText extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "prompt";
    private String title;
    private String prompt;

    public cont_SlideText() {
        // Required empty public constructor
    }

    public static cont_SlideText newInstance(String param1, String param2) {
        cont_SlideText fragment = new cont_SlideText();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            prompt = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_slide_text, container, false);

        TextView tv = v.findViewById(R.id.title_text);
        tv.setText(title);

        tv = v.findViewById(R.id.prompt_text);
        tv.setText(prompt);

        return v;
    }
}