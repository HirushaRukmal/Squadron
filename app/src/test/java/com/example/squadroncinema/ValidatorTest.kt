package com.example.squadroncinema


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val seatnumber = 20
        val ticketcount = "test data"

        val result = Validator.validateInput(seatnumber, ticketcount)
        assertThat(result).isEqualTo(true)

    }
}