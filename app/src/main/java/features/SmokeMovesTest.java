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
public class SmokeMovesTest {
    @Rule
    //public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);
    @Test
    public void smokeTest() {
        onView(withId(R.id.formulario)).perform(typeText("User"), closeSoftKeyboard());
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.new_game_button)).perform(click());
        onView(withId(R.id.mvRight)).perform(click());
        onView(withId(R.id.mvLeft)).perform(click());
        onView(withId(R.id.mvRotate)).perform(click());
        onView(withId(R.id.mvDown)).perform(click());


    }
}