package com.example.football2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.football2.view.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    //@Rule digunakan untuk menentukan sebuah rule, sedangkan
    // anotasi @JvmField digunakan untuk memberitahu JUnit bahwa yang kita beri anotasi tersebut adalah sebuah properti,
    // bukan field.
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(list_team))
            .check(matches(isDisplayed()))
        onView(withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(list_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }
    }
}