package com.example.doordashtakehome;

import android.os.SystemClock;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.doordashtakehome.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldBeAbleToLauchMainScreen(){
        onView(withText("DoorDashTakeHome")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void shouldShowRecyclerView(){
        onView(withId(R.id.restaurant_list)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void shouldScrollToPosition(){
        SystemClock.sleep(1000);
        onView(withId(R.id.restaurant_list)).perform(RecyclerViewActions.scrollToPosition(10));
    }
}
