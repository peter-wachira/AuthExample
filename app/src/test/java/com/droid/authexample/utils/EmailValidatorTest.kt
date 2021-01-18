package com.droid.authexample.utils

import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import org.junit.Test

/*
* Unit tests for email validator logic
* */
class EmailValidatorTest {
    @Test
    fun `Correct Email Returns True`(){
        assertTrue(EmailValidator.isValidEmail("name@gmail.com"))
    }
}