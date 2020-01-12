package apolang.interpret;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.errors.SymbolException;

public class VariableSetTest
{
    private VariableSet testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new VariableSet();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void getSetValue_WhenNoArguments()
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
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(0, result);
    }

    @Test
    public void getValue_WhenVariableNotSet()
    {
        String name = "var";

        Assertions.assertThrows(SymbolException.class, () -> testObject.getValue(name));
    }

    @Test
    public void getSetValue_WhenArgument()
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
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(value, result);
    }

    @Test
    public void contains_WhenValueSet_ThenTrue()
    {
        String name = "var1";

        testObject.setValue(name);

        boolean result = testObject.contains(name);

        Assertions.assertTrue(result);
    }

    @Test
    public void contains_WhenValueNotSet_ThenFalse()
    {
        String name = "var1";
        boolean result = testObject.contains(name);

        Assertions.assertFalse(result);
    }

    @Test
    public void getSetValue_WhenByNumber()

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
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(value, result1);
        Assertions.assertEquals(value, result2);
    }

    @Test
    public void getSetValue_WhenByNumberAndVariableNotSet()
    {
        int value = 10;
        int number = 1;

        Assertions.assertThrows(SymbolException.class, () -> testObject.setValue(number, value));
    }
}
