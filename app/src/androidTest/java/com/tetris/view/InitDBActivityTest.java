package com.tetris.view;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import com.tetris.R;
import com.tetris.model.Shape;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;
import static org.junit.Assert.*;

@LargeTest
// TDD for hard mode
public class InitDBActivityTest {

    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);

    @Test
    public void verifyMessageSentToMessageActivity() {

        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.graphic_button)).perform(click());
        assertEquals(300, Shape.updateInterval);
        onView(withId(R.id.hardmode_switch)).perform(click());
        assertEquals(100, Shape.updateInterval);
    }

}