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

@LargeTest
public class blockedRowsSteps {
    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);


    @Test

    @Given("^Game started$")
    public void game_started() throws InterruptedException {
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        spendTime();
        texture_changed();



    }

    @When("^i Click on pauseButton$")
    public void spendTime() throws InterruptedException {
        Thread.sleep(1000);

    }


    @Then("^rows have diferent textures$")
    public void texture_changed(){
        onView(withId(R.id.blocked_rows_button)).perform(click());
    }
}
