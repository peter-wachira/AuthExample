package com.droid.authexample.data

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

/**
 * Unit tests for the [SharedPreferencesHelper] that mocks [SharedPreferences].
 */
@RunWith(MockitoJUnitRunner::class)
class SharedPreferenceTest{

    private lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var mockBrokenSharedPreferencesHelper: SharedPreferencesHelper

    @Mock private lateinit var mockSharedPreferences: SharedPreferences
    @Mock private lateinit var mockBrokenSharedPreferences: SharedPreferences
    @Mock private lateinit var mockEditor: SharedPreferences.Editor
    @Mock private lateinit var mockBrokenEditor: SharedPreferences.Editor



    @Before
    fun setUp(){
       //create mock shared prefs
        mockSharedPreferencesHelper = createMockSharedPreference()
        mockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference()
        mockSharedPreferencesHelper.saveToken("token")
    }

    @Test
    fun `Save Auth Token` (){
        //save token
        assertTrue(mockSharedPreferencesHelper.saveToken("token"))
        //get the token
        assertEquals("token",mockSharedPreferencesHelper.getToken())

    }

    @Test
    fun `Save Auth Token Failed Returns False` (){
        //save token
        assertFalse(mockBrokenSharedPreferencesHelper.saveToken("token"))
        //get fake token
        assertNotSame("token",mockBrokenSharedPreferencesHelper.getToken())
    }

    /**
     * Creates a mocked SharedPreferences.
     */
    private fun createMockSharedPreference(): SharedPreferencesHelper {
        // Mocking reading the SharedPreferences as if mockSharedPreferences was previously written
        // correctly.
        given(mockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME), anyString()))
            .willReturn("token")

        // Mocking a successful commit.
        given(mockEditor.commit()).willReturn(true)

        // Return the MockEditor when requesting it.
        given(mockSharedPreferences.edit()).willReturn(mockEditor)
        return SharedPreferencesHelper(mockSharedPreferences)
    }

    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private fun createBrokenMockSharedPreference(): SharedPreferencesHelper {
        // Mocking a commit that fails.
        given(mockBrokenEditor.commit()).willReturn(false)

        // Return the broken MockEditor when requesting it.
        given(mockBrokenSharedPreferences.edit()).willReturn(mockBrokenEditor)
        return SharedPreferencesHelper(mockBrokenSharedPreferences)
    }

}