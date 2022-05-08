package apolang.interpreter.parser;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import apolang.exceptions.LanguageException;
import apolang.exceptions.label.DuplicatedLabelException;
import apolang.exceptions.label.InvalidLabelNameException;
import apolang.exceptions.label.LabelException;
import apolang.exceptions.symbol.InvalidVariableNameException;
import apolang.interpreter.Environment;

public class EnvironmentParserTest
{
    private EnvironmentParser testObject;

    @Test
    public void parse_WhenCorrectProgram_ThenEnvironment()
            throws LanguageException
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "Label: ADD y x x", "# next is empty line", "", "Square:",
                        "MUL z x x", "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Environment result = testObject.parse();
        // then
        Assertions.assertThat(result.contains("x")).isTrue();
        Assertions.assertThat(result.getVariableValue("x"))
                  .isEqualTo(Environment.DEFAULT_VARIABLE_VALUE);
        Assertions.assertThat(result.contains("y")).isTrue();
        Assertions.assertThat(result.getVariableValue("y"))
                  .isEqualTo(Environment.DEFAULT_VARIABLE_VALUE);
        Assertions.assertThat(result.contains("z")).isTrue();
        Assertions.assertThat(result.getVariableValue("z"))
                  .isEqualTo(Environment.DEFAULT_VARIABLE_VALUE);
        Assertions.assertThat(result.contains("Label")).isTrue();
        Assertions.assertThat(result.contains("Square")).isTrue();
    }

    @Test
    public void parse_WhenRedefinedEndLabel_ThenLabelException()
    {
        // given
        List<String> lines = List.of("End: ADDC x zero 9");
        testObject = new EnvironmentParser(lines);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(LabelException.class);
    }

    @Test
    public void parse_WhenDuplicatedLabel_ThenDuplicatedLabelException()
    {
        // given
        List<String> lines = List.of("Label: ADDC x zero 9", "Label:");
        testObject = new EnvironmentParser(lines);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(DuplicatedLabelException.class);
    }

    @Test
    public void parse_WhenLabelDoesNotConformToNamingStandard_ThenInvalidLabelNameException()
    {
        // given
        List<String> lines = List.of("Label2:");
        testObject = new EnvironmentParser(lines);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(InvalidLabelNameException.class);
    }

    @Test
    public void parse_WhenVariableDoesNotConformToNamingStandard_ThenInvalidVariableNameException()
    {
        // given
        List<String> lines = List.of("ADDC xX zero 9");
        testObject = new EnvironmentParser(lines);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(InvalidVariableNameException.class);
    }
}
