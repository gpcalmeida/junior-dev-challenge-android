package br.com.radixeng.juniorandroiddevchallenge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.radixeng.juniorandroiddevchallenge.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void showMovieNameLabelTextViewTest() {
        onView(withId(R.id.title_label_text_view)).check(matches(withText(R.string.film_name)));
    }

    @Test
    public void showDirectorNameLabelTextViewTest() {
        onView(withId(R.id.director_label_text_view)).check(matches(withText(R.string.film_director)));
    }

    @Test
    public void showEpisodeNameLabelTextViewTest() {
        onView(withId(R.id.episode_label_text_view)).check(matches(withText(R.string.film_episode)));
    }
}
