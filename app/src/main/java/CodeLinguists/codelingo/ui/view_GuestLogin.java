package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
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

        if (sessionManager.autoLogin()) {
            navigateToCourseOverview();
        }
    }

    public void btnLoginOnClick(View v){
        String name = String.valueOf(usernameField.getText());
        boolean stayIn = ((CheckBox)findViewById(R.id.stay_logged_in)).isChecked();
        login(name, stayIn);
    }

    private void login(String name, boolean stayLoggedIn) {
        try {
            sessionManager.guestLogin(name, stayLoggedIn);
            navigateToCourseOverview();
        } catch (InputValidationException | DataInaccessibleException | CourseNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (AccountPermissionException e) {
            e.printStackTrace();
        }
    }

    private void navigateToCourseOverview() {
        Intent intent = new Intent(view_GuestLogin.this, view_CourseOverview.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}