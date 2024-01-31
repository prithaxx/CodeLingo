package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import CodeLinguists.codelingo.R;

public class GuestLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);
    }

    public void btnLoginOnClick(View v){
        //TODO
        System.out.println("test");
    }

    private void login(String name) {
        //TODO
    }
}