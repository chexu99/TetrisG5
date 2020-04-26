package features;

import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import com.tetris.R;
import com.tetris.view.InitDBActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class SmokeRegisterTest {
    @Rule
    //public ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule<>(GameActivity.class);
    public IntentsTestRule<InitDBActivity> intentsRule =
            new IntentsTestRule<>(InitDBActivity.class);

@Test
    public void smokeTest() throws InterruptedException {
        onView(withId(R.id.formulario)).perform(typeText("User"),closeSoftKeyboard());
        onView(withId(R.id.btnlogin)).perform(click());
    //onView(isRoot()).perform(waitId(R.id.toas_button, TimeUnit.SECONDS.toMillis(15)));
    //onView(isRoot()).perform(waitFor(5000));
        Thread.sleep(15000);



    }


}
