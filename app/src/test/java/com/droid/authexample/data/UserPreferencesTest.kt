package com.droid.authexample.data

import android.content.SharedPreferences
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mock

class UserPreferencesTest{
    private val TEST_TOKEN = "Aaaasbsceryc"

    /*private lateinit var sharedPreferenceEntry: SharedPreferenceEntry
    private lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var mockBrokenSharedPreferencesHelper: SharedPreferencesHelper*/

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences
    @Mock private lateinit var mockBrokenSharedPreferences: SharedPreferences
    @Mock private lateinit var mockEditor: SharedPreferences.Editor
    @Mock private lateinit var mockBrokenEditor: SharedPreferences.Editor
}