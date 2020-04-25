package features;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import com.tetris.R;
import com.tetris.view.InitDBActivity;

import org.junit.Rule;
import org.junit.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


@LargeTest
public class musicPause {
    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);




    @Test

    @Given("^Game started$")
    public void game_started() {
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        I_press_pause_button();
        pause_music();


    }

    @When("^i Click on pauseButton$")
    public void I_press_pause_button() {
        onView(withId(R.id.pauseButton)).perform(click());

    }




    @Then("^pause music$")
    public void pause_music(){
        onView(withId(R.id.toas_button_game)).perform(click());
        onView(withId(R.id.mvDown)).check(matches(isDisplayed()));
        onView(withId(R.id.mvLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.mvRight)).check(matches(isDisplayed()));
        onView(withId(R.id.mvRotate)).check(matches(isDisplayed()));
        onView(withId(R.id.homeButton)).check(matches(isDisplayed()));
        onView(withId(R.id.pauseButton)).check(matches(isDisplayed()));



    }
}
