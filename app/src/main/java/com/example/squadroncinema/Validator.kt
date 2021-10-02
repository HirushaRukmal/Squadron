package com.example.squadroncinema

object Validator {
    fun validateInput(seatnumbers: Int, ticketcount: String): Boolean{
        return !(seatnumbers <= 0 || ticketcount.isEmpty())
    }
}