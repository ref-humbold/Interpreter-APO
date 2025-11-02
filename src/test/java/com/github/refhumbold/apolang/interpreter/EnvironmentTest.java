package com.github.refhumbold.apolang.interpreter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.refhumbold.apolang.exceptions.label.LabelException;
import com.github.refhumbold.apolang.exceptions.symbol.AssignmentToZeroException;
import com.github.refhumbold.apolang.exceptions.symbol.SymbolException;

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
    public void setVariableValue_WhenVariablePresent_ThenValueChanged()
            throws Exception
    {
        // given
        String variable = "var";
        int value = 10;

        testObject.addVariable(variable);

        // when
        testObject.setVariableValue(variable, value);

        // then
        Assertions.assertThat(testObject.getVariableValue(variable)).isEqualTo(value);
    }

    @Test
    public void setVariableValue_WhenVariableAbsent_ThenNothingChanged()
            throws AssignmentToZeroException
    {
        // given
        String variable = "var";
        int value = 10;

        // when
        testObject.setVariableValue(variable, value);

        // then
        Integer resultValue = testObject.getVariableValue(variable);

        Assertions.assertThat(resultValue).isNull();
        Assertions.assertThat(testObject.contains(variable)).isFalse();
    }

    @Test
    public void setVariableValue_WhenSetToVariableZero_ThenSymbolError()
    {
        Assertions.assertThatThrownBy(
                          () -> testObject.setVariableValue(Environment.ZERO_VARIABLE, 10))
                  .isInstanceOf(SymbolException.class);
    }

    @Test
    public void contains_WhenVariablePresent_ThenTrue()
            throws Exception
    {
        // given
        String variable = "var";

        testObject.addVariable(variable);

        // when
        boolean result = testObject.contains(variable);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void contains_WhenLabelPresent_ThenTrue()
            throws Exception
    {
        // given
        String label = "Label";

        testObject.addLabel(label);

        // when
        boolean result = testObject.contains(label);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void contains_WhenAbsent_ThenFalse()
    {
        // when
        boolean result = testObject.contains("qwerty");

        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void addLabel_WhenAbsent_ThenAdded()
            throws Exception
    {
        // given
        String label = "Label";

        // when
        testObject.addLabel(label);

        // then
        Assertions.assertThat(testObject.contains(label)).isTrue();
    }

    @Test
    public void addLabel_WhenDoesNotConformToNamingSchema_ThenLabelException()
    {
        Assertions.assertThatThrownBy(() -> testObject.addLabel("qwertYuiop09"))
                  .isInstanceOf(LabelException.class);
    }

    @Test
    public void addVariable_WhenAbsent_ThenAddedWithDefaultValue()
            throws Exception
    {
        // given
        String variable = "var";

        // when
        testObject.addVariable(variable);

        // then
        Assertions.assertThat(testObject.contains(variable)).isTrue();
        Assertions.assertThat(testObject.getVariableValue(variable))
                  .isEqualTo(Environment.DEFAULT_VARIABLE_VALUE);
    }

    @Test
    public void addVariable_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        Assertions.assertThatCode(() -> testObject.addVariable("qwertyuiop"))
                  .doesNotThrowAnyException();
    }

    @Test
    public void validateLabel_WhenDoesNotConformToNamingSchema_ThenLabelException()
    {
        Assertions.assertThatThrownBy(() -> testObject.validateLabel("qwertYuiop09"))
                  .isInstanceOf(LabelException.class);
    }

    @Test
    public void validateLabel_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        Assertions.assertThatCode(() -> testObject.validateLabel("Qwertyuiop"))
                  .doesNotThrowAnyException();
    }

    @Test
    public void validateVariable_WhenDoesNotConformToNamingSchema_ThenSymbolException()
    {
        Assertions.assertThatThrownBy(() -> testObject.validateVariable("qwertYuiop09"))
                  .isInstanceOf(SymbolException.class);
    }

    @Test
    public void validateVariable_WhenCorrectNamingSchema_ThenNoExceptionThrown()
    {
        Assertions.assertThatCode(() -> testObject.validateVariable("qwertyuiop"))
                  .doesNotThrowAnyException();
    }
}
