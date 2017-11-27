package com.m224.infectious.activities;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.m224.infectious.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WorkingGameTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void workingGameTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3584657);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction gridImageView = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(0);
        gridImageView.perform(click());

        DataInteraction gridImageView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(2);
        gridImageView2.perform(click());

        DataInteraction gridImageView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(8);
        gridImageView3.perform(click());

        DataInteraction gridImageView4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(6);
        gridImageView4.perform(click());

        DataInteraction gridImageView5 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(2);
        gridImageView5.perform(click());

        DataInteraction gridImageView6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(3);
        gridImageView6.perform(click());

        DataInteraction gridImageView7 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(6);
        gridImageView7.perform(click());

        DataInteraction gridImageView8 = onData(anything())
                .inAdapterView(allOf(withId(R.id.grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(4);
        gridImageView8.perform(click());

        ViewInteraction imageView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_view),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        3),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_view),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        2),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.grid_view),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        4),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
