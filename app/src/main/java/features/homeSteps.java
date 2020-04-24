package features;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tetris.R;

import com.tetris.view.FinalScoreActivity;
import com.tetris.view.GameActivity;
import com.tetris.view.InitDBActivity;
import com.tetris.view.MenuActivity;

import cucumber.api.java.Before;
import cucumber.api.java.ca.I;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;
import static java.util.EnumSet.allOf;
import static java.util.regex.Pattern.matches;
import static org.junit.Assert.*;



//@RunWith(AndroidJUnit4.class)
@LargeTest

public class homeSteps {

    @Rule
    //public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);

    /*@Before("^Game started$")
            public void initGame(){
        onView(withId(R.id.btnlogin)).perform(click());

    }*/


    @Test
    /*public void game_started() {
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        onView(withId(R.id.homeButton)).perform(click());

    }*/

    @Given("^Game started$")
    public void game_started() {
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        I_press_home_button();
        goHome();


    }

    @When("^i Click on homeButton$")
    public void I_press_home_button() {
        onView(withId(R.id.homeButton)).perform(click());

    }




    @Then("^go Home$")
    public void goHome(){
        onView(withId(R.id.toas_button)).perform(click());



    }



}
