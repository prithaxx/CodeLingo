package CodeLinguists.codelingo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.persistence.IAccountData;

public class view_GuestLogin extends AppCompatActivity {
    private IAccountHandler accountHandler;
    private EditText usernameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        this.accountHandler = new AccountHandler();
        this.usernameField = (EditText) findViewById(R.id.un_field);
    }

    public void btnLoginOnClick(View v){
        login(String.valueOf(usernameField.getText()));
    }

    private void login(String name) {
        try {
            accountHandler.guestLogin(name);
        } catch (InputValidationException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}