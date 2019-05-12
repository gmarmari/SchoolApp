package com.marmaris.schoolapp.lessons

import android.view.Gravity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.marmaris.schoolapp.R
import com.marmaris.schoolapp.main.MainActivity

import org.junit.Rule
import org.junit.Test

class LessonsFragmentTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickNavItemLessons_showLessonsFragment(){
        // Open Drawer to click on navigation.
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(DrawerActions.open()) // Open Drawer

        // Select the lessons navigation item
        Espresso.onView(ViewMatchers.withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.main_nav_lessons))

        // Check that the Lessons Fragment is opened
        Espresso.onView(ViewMatchers.withId(R.id.m_swipe_refresh_layout_lessons)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}