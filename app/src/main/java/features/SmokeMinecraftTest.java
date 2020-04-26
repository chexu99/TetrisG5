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
public class SmokeMinecraftTest {
    @Rule
    //public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);
    @Test
    public void smokeTest() {
        onView(withId(R.id.formulario)).perform(typeText("User"), closeSoftKeyboard());
        onView(withId(R.id.btnlogin)).perform(click());
        onView(withId(R.id.minecraft)).perform(click());
        onView(withId(R.id.celda1)).perform(click());
        onView(withId(R.id.celda4)).perform(click());
        onView(withId(R.id.celda6)).perform(click());
        onView(withId(R.id.celda7)).perform(click());
        onView(withId(R.id.celda9)).perform(click());
        onView(withId(R.id.celda3)).perform(click());
        onView(withId(R.id.color7)).perform(click());
        onView(withId(R.id.mine_newshape)).perform(click());



    }
}
