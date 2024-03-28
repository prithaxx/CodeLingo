package CodeLinguists.codelingo;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Intent;

import CodeLinguists.codelingo.ui.view_CourseOverview;
import CodeLinguists.codelingo.ui.view_GuestLogin;

public class ATestUtils {
    public static void navigateToLogin() {
        Intent intent = new Intent(getApplicationContext(), view_GuestLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(getApplicationContext(), intent, null);
    }
}
