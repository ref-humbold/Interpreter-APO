package apolang.interpreter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import apolang.errors.LabelException;
import apolang.errors.SymbolException;

public class EnvironmentTest
{
    private Environment testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new Environment();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void setVariableValue_getVariableValue_WhenVariablePresent_ThenValueChanged()
    {
        // given
        String variable = "var";
        int value = 10;

        try
        {
            testObject.addVariable(variable);
            // when
            testObject.setVariableValue(variable, value);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }

        int result = testObject.getVariableValue(variable);
        // then
        Assertions.assertEquals(value, result);
    }

    @Test
    public void setVariableValue_getVariableValue_WhenVariableAbsent_ThenNothingChanged()
    {
        // given
        String variable = "var";
        int value = 10;

        // when
        try
        {
            testObject.setVariableValue(variable, value);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }

        Integer result = testObject.getVariableValue(variable);
        // then
        Assertions.assertNull(result);
        Assertions.assertFalse(testObject.contains(variable));
    }

    @Test
    public void setVariableValue_WhenSetToVariableZero_ThenSymbolError()
    {
        // when
        Executable executable = () -> testObject.setVariableValue(Environment.ZERO_VARIABLE, 10);
        // then
        Assertions.assertThrows(SymbolException.class, executable);
    }

    @Test
    public void contains_WhenVariablePresent_ThenTrue()
    {
        // given
        String variable = "var";
        try
        {
            testObject.addVariable(variable);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // when
        boolean result = testObject.contains(variable);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    public void contains_WhenLabelPresent_ThenTrue()
    {
        // given
        String label = "Label";

        try
        {
            testObject.addLabel(label);
        }
        catch(LabelException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // when
        boolean result = testObject.contains(label);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    public void contains_WhenAbsent_ThenTrue()
    {
        // when
        boolean result = testObject.contains("qwerty");
        // then
        Assertions.assertFalse(result);
    }

    @Test
    public void addLabel_WhenAbsent_ThenAdded()
    {
        // given
        String label = "Label";
        // when
        try
        {
            testObject.addLabel(label);
        }
        catch(LabelException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // then
        Assertions.assertTrue(testObject.contains(label));
    }

    @Test
    public void addLabel_WhenDoesNotConformToNamingSchema_ThenLabelException()
    {
        // when
        Executable executable = () -> testObject.addLabel("qwertYuiop09");
        // then
        Assertions.assertThrows(LabelException.class, executable);
    }

    @Test
    public void addVariable_WhenAbsent_ThenAddedWithDefaultValue()
    {
        // given
        String variable = "var";
        // when
        try
        {
            testObject.addVariable(variable);
        }
        catch(SymbolException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // then
        Assertions.assertTrue(testObject.contains(variable));
        Assertions.assertEquals(Environment.DEFAULT_VARIABLE_VALUE,
                                testObject.getVariableValue(variable));
    }

    @Test
    public void addVariable_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        // when
        Executable executable = () -> testObject.addVariable("qwertyuiop");
        // then
        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    public void validateLabel_WhenDoesNotConformToNamingSchema_ThenLabelException()
    {
        // when
        Executable executable = () -> testObject.validateLabel("qwertYuiop09");
        // then
        Assertions.assertThrows(LabelException.class, executable);
    }

    @Test
    public void validateLabel_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        // when
        Executable executable = () -> testObject.validateLabel("Qwertyuiop");
        // then
        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    public void validateVariable_WhenDoesNotConformToNamingSchema_ThenSymbolException()
    {
        // when
        Executable executable = () -> testObject.validateVariable("qwertYuiop09");
        // then
        Assertions.assertThrows(SymbolException.class, executable);
    }

    @Test
    public void validateVariable_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        // when
        Executable executable = () -> testObject.validateVariable("qwertyuiop");
        // then
        Assertions.assertDoesNotThrow(executable);
    }
}
