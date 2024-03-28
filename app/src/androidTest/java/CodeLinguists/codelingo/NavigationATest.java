package CodeLinguists.codelingo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import CodeLinguists.codelingo.ui.view_GuestLogin;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationATest {

    @Rule
    public ActivityScenarioRule<view_GuestLogin> activityRule =
            new ActivityScenarioRule<>(view_GuestLogin.class);
    @Before
    public void setup() {
        //Sign in
        onView(withId(R.id.un_field)).perform(typeText("JohnDavies"), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
    }

    @Test
    public void changeCourse() {
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course1)).perform(click());
        onView(withId(R.id.placeholder_course)).check(matches(withText(containsString("Java Basics"))));
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course2)).perform(click());
        onView(withId(R.id.placeholder_course)).check(matches(withText(containsString("Java Advanced"))));
    }

    @Test
    public void selectChapter() {
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fragmentContainerView)).check(matches(isDisplayed()));
    }

    @Test
    public void exitChapterGesture() {
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        onView(withId(R.id.placeholder_course)).check(matches(isDisplayed()));
    }

    @Test
    public void reopenChapter() {
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fragmentContainerView)).check(matches(isDisplayed()));
    }
}
