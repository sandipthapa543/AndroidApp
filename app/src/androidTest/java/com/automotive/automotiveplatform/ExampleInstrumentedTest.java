package com.automotive.automotiveplatform;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.runner.RunWith;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.automotive.automotiveplatform", appContext.getPackageName());
    }


        @Rule
        public ActivityTestRule<LoginActivity>
                testRule = new ActivityTestRule<>(LoginActivity.class);

        @Test
        public void checkNos()
        {
            onView(withId(R.id.login_emailid))
                    .perform(typeText("ask@gmail.com"));
            onView(withId(R.id.login_password))
                    .perform(typeText("admin1234"));
            onView(withId(R.id.btnLogin))
                    .perform(click());

            // This is another activity, no need to tell Espresso


        }
    }

