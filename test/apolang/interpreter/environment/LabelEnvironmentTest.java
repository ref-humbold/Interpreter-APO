package apolang.interpreter.environment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.errors.LabelException;
import apolang.instruction.Instruction;
import apolang.instruction.NOPInstruction;

public class LabelEnvironmentTest
{
    private LabelEnvironment testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new LabelEnvironment();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void getSetValue()
    {
        String name = "label";
        Instruction value = new NOPInstruction(1);
        Instruction result = null;

        testObject.setInstruction(name, value);

        try
        {
            result = testObject.getInstruction(name);
        }
        catch(LabelException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(value, result);
    }

    @Test
    public void contains_WhenValueSet()
    {
        String name = "label1";
        Instruction value = new NOPInstruction(1);

        testObject.setInstruction(name, value);

        boolean result = testObject.contains(name);

        Assertions.assertTrue(result);
    }

    @Test
    public void contains_WhenValueNotSet()
    {
        String name = "label1";
        boolean result = testObject.contains(name);

        Assertions.assertFalse(result);
    }

    @Test
    public void getSetValue_WhenByNumber()
    {
        String name = "label";
        Instruction value1 = new NOPInstruction(1);
        Instruction value2 = new NOPInstruction(2);
        Instruction result1 = null;
        Instruction result2 = null;

        testObject.setInstruction(name, value1);

        int number = testObject.getNumber(name);

        try
        {
            testObject.setInstruction(number, value2);
            result1 = testObject.getInstruction(name);
            result2 = testObject.getInstruction(number);
        }
        catch(LabelException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(value2, result1);
        Assertions.assertEquals(value2, result2);
    }

    @Test
    public void getSetValue_WhenByNumberAndVariableNotSet()
    {
        Instruction value = new NOPInstruction(1);
        int number = 1;

        Assertions.assertThrows(LabelException.class,
                                () -> testObject.setInstruction(number, value));
    }
}
