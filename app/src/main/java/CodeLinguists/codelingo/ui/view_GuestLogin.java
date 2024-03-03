package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.persistence.utils.DbHelper;

public class view_GuestLogin extends AppCompatActivity {
    private ISessionManager sessionManager;
    private EditText usernameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        DbHelper.copyDatabaseToDevice(this);
        this.sessionManager = Services.getSessionManager();
        this.usernameField = (EditText) findViewById(R.id.un_field);
    }

    public void btnLoginOnClick(View v){
        login(String.valueOf(usernameField.getText()));
    }

    private void login(String name) {
        try {
            sessionManager.guestLogin(name);
            navigateToCourseOverview();
        } catch (InputValidationException | SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToCourseOverview() {
        Intent intent = new Intent(view_GuestLogin.this, view_CourseOverview.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}