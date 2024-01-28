package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import CodeLinguists.codelingo.R;

/**
 * Provides common behaviours for slides like:
 * Submit answer, receive feedback, skip, exit chapter
 */
public class view_SlideShowWrapper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show_wrapper);
    }
}