package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import CodeLinguists.codelingo.R;

public class view_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnNavSignupClicked(View v) {
        startSignupActivity();
    }
    public void btnLoginClicked(View v) {
        login();
    }

    private void startSignupActivity() {
        Intent intent = new Intent(this, cont_Signup.class);
        this.startActivity(intent);
    }
    private void login() {
        //TODO validate login
        Intent intent = new Intent(this, cont_ChapterSummary.class);
        startActivity(intent);
    }
}