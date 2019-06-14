package com.tillylabs.star_wars_demo


import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val scenario = ActivityScenario.launch<MainActivity>(Intent())

        onView(ViewMatchers.withId(R.id.rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NameAdapter.PersonViewHolder>(
                    3,
                    click()
                )
            )
        
    }
}
