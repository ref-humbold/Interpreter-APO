package apolang.interpreter.parser;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import apolang.errors.LabelException;
import apolang.errors.LanguageException;
import apolang.errors.SymbolException;
import apolang.interpreter.Environment;

public class EnvironmentParserTest
{
    private EnvironmentParser testObject;

    @Test
    public void parse_WhenCorrectProgram_ThenEnvironment()
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "Label: ADD y x x", "# next is empty line", "", "Square:",
                        "MUL z x x", "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Environment result = null;
        try
        {
            result = testObject.parse();
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // then
        Assertions.assertTrue(result.contains("x"));
        Assertions.assertEquals(Environment.DEFAULT_VARIABLE_VALUE, result.getVariableValue("x"));
        Assertions.assertTrue(result.contains("y"));
        Assertions.assertEquals(Environment.DEFAULT_VARIABLE_VALUE, result.getVariableValue("y"));
        Assertions.assertTrue(result.contains("z"));
        Assertions.assertEquals(Environment.DEFAULT_VARIABLE_VALUE, result.getVariableValue("z"));
        Assertions.assertTrue(result.contains("Label"));
        Assertions.assertTrue(result.contains("Square"));
    }

    @Test
    public void parse_WhenRedefinedEndLabel_ThenLabelException()
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "End: ADD y x x", "# next is empty line", "", "Square:",
                        "MUL z x x", "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(LabelException.class, executable);
    }

    @Test
    public void parse_WhenDuplicatedLabel_ThenLabelException()
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "Label: ADD y x x", "# next is empty line", "", "Label:",
                        "MUL z x x", "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(LabelException.class, executable);
    }

    @Test
    public void parse_WhenLabelDoesNotConformToNamingStandard_ThenLabelException()
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "Label: ADD y x x", "# next is empty line", "", "Label2:",
                        "MUL z x x", "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(LabelException.class, executable);
    }

    @Test
    public void parse_WhenVariableDoesNotConformToNamingStandard_ThenSymbolException()
    {
        // given
        List<String> lines =
                List.of("ADDC x zero 9", "Label: ADD y x x", "", "Square:", "MUL zZ x x",
                        "PTINT k");
        testObject = new EnvironmentParser(lines);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(SymbolException.class, executable);
    }
}
