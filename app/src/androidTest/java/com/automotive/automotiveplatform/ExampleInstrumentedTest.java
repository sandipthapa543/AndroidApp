package com.automotive.automotiveplatform;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.runner.RunWith;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.RecursiveAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<DashboardActivity>
            testRule = new ActivityTestRule<>(DashboardActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.automotive.automotiveplatform", appContext.getPackageName());
    }



    @Before
    public void fragment(){
        testRule.getActivity().getSupportFragmentManager().beginTransaction();


    }

    @Test
    public void home(){
        onView(withId(R.id.navigation_home))
                .perform(swipeLeft());
        onView(withId(R.id.navigation_Acc))
        .perform(click());
    }

    @Test
    public void profile(){
        onView(withId(R.id.navigation_dashboard))
                .perform(click());
    }

    @Test
    public void editProfile(){
        onView(withId(R.id.navigation_Acc))
                .perform(click());
        onView(withId(R.id.btnEdit))
                .perform(click());
        onView((withId(R.id.firstName)))
                .perform(typeText("sandip"));
        onView(withId(R.id.lastName))
                .perform(typeText("thapa"));
        onView(withId(R.id.Phone))
                .perform(typeText("9814103679"),closeSoftKeyboard());
        onView(withId(R.id.Address))
                .perform(typeText("pokhara"),closeSoftKeyboard());
        onView(withId(R.id.btnUpdate))
                .perform(click());
    }

//    @Test
//    public void signUp(){
//        onView(withId(R.id.firstName))
//                .perform(click
//    }

//        @Test
//        public void checkNos()
//        {
//            onView(withId(R.id.login_emailid))
//                    .perform(typeText("ask@gmail.com"),closeSoftKeyboard());
//            onView(withId(R.id.login_password))
//                    .perform(typeText("admin1234"),closeSoftKeyboard());
//            onView(withId(R.id.btnLogin))
//                    .perform(click());
//
//            // This is another activity, no need to tell Espresso
//
//
//        }
    }

