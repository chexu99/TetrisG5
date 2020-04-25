package features;

import androidx.test.espresso.ViewAssertion;
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
public class idiomSteps {
    @Rule
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);

    @Test
    @Given("^Im in the menu page$")
    public void estoy_Menu(){
        onView(withId(R.id.btnlogin)).perform(click());
        clickChangeIdiom();
        changedIdiom();
    }

    @When("^i Click on the changeIdiom button$")
    public void clickChangeIdiom(){
        onView(withId(R.id.idioma)).perform(click());
    }

    @Then("^change idiom$")
    public void changedIdiom(){
        System.out.println("idiom changed with exit");

    }
}

