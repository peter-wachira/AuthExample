package com.droid.authexample.utils

import junit.framework.Assert.assertFalse
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
    @Test
    fun `Invalid Email Returns False`(){
        assertFalse(EmailValidator.isValidEmail("name"))
    }
    @Test
    fun `Correct Email Subdomain Returns True` (){
        assertTrue(EmailValidator.isValidEmail("name@protoenergy.com"))
    }
    @Test
    fun `Invalid Double Dot Returns False` (){
        assertFalse(EmailValidator.isValidEmail("name@protoenergy..com"))
    }
    @Test
    fun `Invalid Email No Username Returns False` (){
        assertFalse(EmailValidator.isValidEmail("@protoenergy.com"))
    }
    @Test
    fun `Null Email Returns False` (){
        assertFalse(EmailValidator.isValidEmail(""))
    }
}