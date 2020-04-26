package features;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import com.tetris.R;
import com.tetris.view.InitDBActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class SmokeMenuOptionsTest {
    @Rule
    //public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);
    @Test
    public void smokeTest() {
        onView(withId(R.id.formulario)).perform(typeText("User"), closeSoftKeyboard());
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        onView(withId(R.id.homeButton)).perform(click());
        onView(withId(R.id.minecraft)).perform(click());
        onView(withId(R.id.mine_cancel)).perform(click());
        onView(withId(R.id.ranking_button)).perform(click());
        onView(withId(R.id.reStar)).perform(click());
        onView(withId(R.id.gamma_button)).perform(click());
        onView(withId(R.id.return_back)).perform(click());
        onView(withId(R.id.graphic_button)).perform(click());
        onView(withId(R.id.ok_button)).perform(click());
        onView(withId(R.id.idioma)).perform(click());

    }
}
