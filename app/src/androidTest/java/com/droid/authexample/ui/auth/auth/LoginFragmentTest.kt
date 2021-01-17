package com.droid.authexample.ui.auth.auth

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest : TestCase() {
    //set the rule
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    public override fun setUp() {
        super.setUp()
        hiltRule.inject()
    }

    @Test
    fun loginFragment(){
        val scenario = launchFragmentInContainer<LoginFragment>()
    }

    @Test
    fun happyCase(){
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.droid.authexample", appContext.packageName)
    }

    public override fun tearDown() {}
}