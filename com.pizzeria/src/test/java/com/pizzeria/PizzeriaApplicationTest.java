package com.pizzeria;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PizzeriaApplicationTest
    extends TestCase
{

    public PizzeriaApplicationTest(String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( PizzeriaApplicationTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
