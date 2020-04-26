package features;



import com.tetris.R;


import com.tetris.view.InitDBActivity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;




@LargeTest
public class homeSteps {

    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);

    @Test
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
