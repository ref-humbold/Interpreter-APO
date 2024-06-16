package apolang;

import org.apache.commons.cli.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CliParserTest
{
    private CliParser testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new CliParser();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void parse_WhenEmptyArguments_ThenParseException()
    {
        Assertions.assertThatThrownBy(() -> testObject.parse(new String[]{ }))
                  .isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenHelpFlag_ThenTrue()
            throws Exception
    {
        // given
        String[] args = new String[]{"-h"};

        // when
        CliParser.Arguments result = testObject.parse(args);

        // then
        Assertions.assertThat(result.isHelp()).isTrue();
    }

    @Test
    public void parse_WhenMemoryFlag_ThenFlagValue()
            throws Exception
    {
        // given
        int value = 10;
        String filename = "x.apo";
        String[] args = new String[]{"-m", Integer.toString(value), filename};

        // when
        CliParser.Arguments result = testObject.parse(args);

        // then
        Assertions.assertThat(result.isHelp()).isFalse();
        Assertions.assertThat(result.getMemorySize()).isEqualTo(value);
        Assertions.assertThat(result.getFilename()).isEqualTo(filename);
    }

    @Test
    public void parse_WhenMultipleFiles_ThenFirstTaken()
            throws Exception
    {
        // given
        String filename = "x.apo";
        String[] args = new String[]{filename, "y.txt"};

        // when
        CliParser.Arguments result = testObject.parse(args);

        // then
        Assertions.assertThat(result.isHelp()).isFalse();
        Assertions.assertThat(result.getMemorySize()).isEqualTo(1);
        Assertions.assertThat(result.getFilename()).isEqualTo(filename);
    }

    @Test
    public void parse_WhenNoSizeForMemoryFlag_ThenParseException()
    {
        Assertions.assertThatThrownBy(() -> testObject.parse(new String[]{"-m", "x.apo"}))
                  .isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenInvalidMemorySize_ThenParseException()
    {
        Assertions.assertThatThrownBy(() -> testObject.parse(new String[]{"-m", "A"}))
                  .isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenNoFile_ThenParseException()
    {
        Assertions.assertThatThrownBy(() -> testObject.parse(new String[]{"-m", "5"}))
                  .isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenFileHasWrongExtension_ThenParseException()
    {
        Assertions.assertThatThrownBy(() -> testObject.parse(new String[]{"x.txt"}))
                  .isInstanceOf(ParseException.class);
    }
}
