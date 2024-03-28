package CodeLinguists.codelingo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
public class LessonATest {

    @Rule
    public ActivityScenarioRule<view_GuestLogin> activityRule =
            new ActivityScenarioRule<>(view_GuestLogin.class);
    @Before
    public void setup() {
        //Sign in
        onView(withId(R.id.un_field)).perform(typeText("JohnDavies"), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
        //Navigate to specific course
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course1)).perform(click());
        //Open specific quiz
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void correctAnswerMultiChoice() {
        onView(withText("String")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Correct!")).check(matches(isDisplayed()));
    }

    @Test
    public void wrongAnswerMultiChoice() {
        onView(withText("int")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Wrong Answer")).check(matches(isDisplayed()));
    }

    @Test
    public void nullAnswerMultiChoice() {
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("int")).check(matches(isDisplayed()));
    }

    @Test
    public void correctAnswerShortAns() {
        goToQuestion(1);
        onView(withId(R.id.answer_input)).perform(typeText("final"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Correct!")).check(matches(isDisplayed()));
    }

    @Test
    public void wrongAnswerShortAns() {
        goToQuestion(1);
        onView(withId(R.id.answer_input)).perform(typeText("Oops"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Wrong Answer")).check(matches(isDisplayed()));
    }

    @Test
    public void nullAnswerShortAns() {
        goToQuestion(1);
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.answer_input)).check(matches(isDisplayed()));
    }

    @Test
    public void correctAnswerTrueFalse() {
        goToQuestion(2);
        onView(withText("True")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Correct!")).check(matches(isDisplayed()));
    }

    @Test
    public void wrongAnswerTrueFalse() {
        goToQuestion(2);
        onView(withText("False")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("Wrong Answer")).check(matches(isDisplayed()));
    }

    @Test
    public void nullAnswerTrueFalse() {
        goToQuestion(2);
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("True")).check(matches(isDisplayed()));
    }

    @Test
    public void previousQuestion() {
        goToQuestion(1);
        onView(withId(R.id.btnSlideShowPrev)).perform(click());
        onView(withText("String")).check(matches(isDisplayed()));
    }

    @Test
    public void completeQuiz() {
        goToQuestion(2);
        onView(withText("False")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).check(matches(withText("Done!")));
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed()));
    }

    private void goToQuestion(int id) {
        if (id == 0) {
            return;
        }
        onView(withText("String")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        if (id == 1) {
            return;
        }
        onView(withId(R.id.answer_input)).perform(typeText("final"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
    }
}
