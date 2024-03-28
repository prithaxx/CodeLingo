package CodeLinguists.codelingo;

import static androidx.test.espresso.Espresso.onView;
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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import CodeLinguists.codelingo.ui.view_GuestLogin;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CourseProgressATest {
    @Rule
    public ActivityScenarioRule<view_GuestLogin> activityRule =
            new ActivityScenarioRule<>(view_GuestLogin.class);

    @Test
    public void testOpeningLockedLesson() {
        onView(withId(R.id.un_field)).perform(typeText("tryLockedLesson"), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert still in Course Overview
        //Navigate to specific course
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course1)).perform(click());
        //Open specific quiz
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void testUnlockingLesson() {
        //sign in
        onView(withId(R.id.un_field)).perform(typeText("tryUnlockingLesson"), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
        //Open chapter
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert still in Course Overview
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course1)).perform(click());
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Complete chapter
        onView(withText("String")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.answer_input)).perform(typeText("final"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("False")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        //Open now unlocked lesson
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.optionA_button)).check(matches(isDisplayed()));
    }

    // Tests 3 different things:
    //      no course progress == 0%
    //      1 course complete progress == 50%
    //      2 courses complete progress == 100%
    // Splitting into 3 tests felt bad, and any failure on this test is sufficiently granular
    @Test
    public void testProgressIndicator() {
        //sign in
        onView(withId(R.id.un_field)).perform(typeText("testProgressIndicator"), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
        //Open chapter
        onView(withId(R.id.btnHamburgerMenu)).check(matches(isDisplayed())); //Assert still in Course Overview
        onView(withId(R.id.btnHamburgerMenu)).perform(click());
        onView(withId(R.id.course1)).perform(click());
        onView(withId(R.id.progress_percentage)).check(matches(withText(containsString("0%"))));
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Complete chapter
        completeOpenLesson();
        //Open now unlocked lesson
        onView(withId(R.id.progress_percentage)).check(matches(withText(containsString("50%"))));
        onView(withId(R.id.chapterList)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        //complete lesson 2
        onView(withText("do-while")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.answer_input)).perform(typeText("num%2 == 0"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.progress_percentage)).check(matches(withText(containsString("100%"))));
    }

    private void completeOpenLesson() {
        onView(withText("String")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.answer_input)).perform(typeText("final"), closeSoftKeyboard());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withText("False")).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
        onView(withId(R.id.btnSlideShowNext)).perform(click());
    }
}
