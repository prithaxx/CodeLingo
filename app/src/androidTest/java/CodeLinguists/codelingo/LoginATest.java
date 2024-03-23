package CodeLinguists.codelingo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static CodeLinguists.codelingo.ATestUtils.navigateToLogin;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import CodeLinguists.codelingo.ui.view_CourseOverview;
import CodeLinguists.codelingo.ui.view_GuestLogin;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginATest {
    @Before
    public void setup() {
        androidx.test.espresso.intent.Intents.init();
    }
    @After
    public void teardown() {
        androidx.test.espresso.intent.Intents.release();
    }

    @Rule
    public ActivityScenarioRule<view_GuestLogin> activityRule =
            new ActivityScenarioRule<>(view_GuestLogin.class);

    @Test
    public void ViewLoads() {
        onView(withText("CodeLingo")).check(matches(isDisplayed()));
    }

    @Test
    public void createAccount() {
        login(false);
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert in Course Overview
    }

    @Test
    public void loginExistingAccount() {
        login(false);
        logout();
        login(false);
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert in Course Overview
    }

    @Test
    public void stayLoggedIn() {
        login(true);
        navigateToLogin();
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert autologin success
        logout();
    }

    @Test
    public void notStayLoggedIn() {
        login(false);
        navigateToLogin();
        onView(withId(R.id.btnHamburgerMenu)).check(doesNotExist()); //Assert autologin failed
    }

    @Test
    public void logoutNotStayLoggedIn() {
        login(false);
        logout();
        onView(withId(R.id.btnHamburgerMenu)).check(doesNotExist()); //Assert not in Course Overview
    }

    @Test
    public void logoutStayLoggedIn() {
        login(true);
        logout();
        onView(withId(R.id.btnHamburgerMenu)).check(doesNotExist()); //Assert not in Course Overview
    }

    @Test
    public void loginEmpty() {
        onView(withText(R.string.sign_in)).perform(click());
        onView(withId(R.id.btnHamburgerMenu)).check(doesNotExist()); //Assert not in Course Overview (checking toast content is weirdly difficult.)
    }

    private void login(boolean stayLoggedIn) {
        onView(withId(R.id.un_field)).perform(typeText("JohnDavies"), closeSoftKeyboard());
        if (stayLoggedIn) {
            onView(withId(R.id.stay_logged_in)).perform(click());
        }
        onView(withText(R.string.sign_in)).perform(click());
    }

    private void logout() {
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }
}
