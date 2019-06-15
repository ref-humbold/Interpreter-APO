package apolang.interpret;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import apolang.errors.SymbolException;

public class VariableSetTest
{
    private VariableSet testObject;

    @Before
    public void setUp()
    {
        testObject = new VariableSet();
    }

    @After
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void testGetSetValueWhenNoArguments()
    {
        String name = "var";
        int result = 1;

        testObject.setValue(name);

        try
        {
            result = testObject.getValue(name);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assert.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assert.assertEquals(0, result);
    }

    @Test(expected = SymbolException.class)
    public void testGetValueWhenVariableNotSet()
            throws SymbolException
    {
        String name = "var";

        testObject.getValue(name);
    }

    @Test
    public void testGetSetValueWhenArgument()
    {
        String name = "var";
        int value = 10;
        int result = 0;

        testObject.setValue(name, value);

        try
        {
            result = testObject.getValue(name);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assert.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assert.assertEquals(value, result);
    }

    @Test
    public void testContains()
    {
        String name1 = "var1";
        String name2 = "var2";

        testObject.setValue(name1);

        boolean result1 = testObject.contains(name1);
        boolean result2 = testObject.contains(name2);

        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }

    @Test
    public void testGetSetValueByNumber()
    {
        String name = "var";
        int value = 10;
        int result1 = 0;
        int result2 = 0;

        testObject.setValue(name);

        int number = testObject.getNumber(name);

        try
        {
            testObject.setValue(number, value);
            result1 = testObject.getValue(name);
            result2 = testObject.getValue(number);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assert.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assert.assertEquals(value, result1);
        Assert.assertEquals(value, result2);
    }

    @Test(expected = SymbolException.class)
    public void testGetSetValueByNumberWhenVariableNotSet()
            throws SymbolException
    {
        int value = 10;
        int number = 1;

        testObject.setValue(number, value);
    }
}
