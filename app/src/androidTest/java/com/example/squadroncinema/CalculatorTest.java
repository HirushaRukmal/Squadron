package com.example.squadroncinema;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void addition(){
        assertEquals(4, 2+2);
    }

    @Test
    public void subtraction(){
        assertEquals(0, 2-2);
    }

    @Test
    public void multiplication(){
        assertEquals(4, 2*2);
    }

    @Test
    public void division(){
        assertEquals(1, 2/2);
    }

}

