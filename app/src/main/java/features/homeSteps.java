package features;

import android.view.MotionEvent;

import com.tetris.view.GameActivity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.mockito.Mockito.mock;

public class homeSteps {
    @Given("^Game started$")
    public void game_started() {
        GameActivity view = mock(GameActivity.class);
        boolean playing = view.getPlaying();

        if (playing){
            System.out.println("The game has started");
        }


    }

    @When("^i Click on homeButton$")
            public void homeCLicked() {
        GameActivity view = mock(GameActivity.class);
        boolean clicked = view.gethomeClicked();
        if (clicked) {
            System.out.println("Home has been touched");
        }
    }

    @Then("^go Home$")
    public void goHome(){
        GameActivity view = mock(GameActivity.class);
        view.openMenu();
    }
}

