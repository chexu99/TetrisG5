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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.*;
@LargeTest
public class Stopwatch {
    boolean game_started=false;
    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);




    @Test

    @Given("^Game started$")
    public void game_started() {
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        game_is_running();
        crono_is_running();


    }

    @When("^Game is running$")
        public void game_is_running() {
        onView(withId(R.id.crono)).check(matches(isEnabled()));


    }




    @Then("^crono is running$")
    public void crono_is_running(){
        onView(withId(R.id.crono)).check(matches(isDisplayed()));



    }
}
