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
        // when
        Throwable throwable = Assertions.catchThrowable(() -> testObject.parse(new String[]{ }));
        // then
        Assertions.assertThat(throwable).isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenHelpFlag_ThenTrue()
    {
        // given
        String[] args = new String[]{"-h"};

        try
        {
            // when
            CliParser.Arguments result = testObject.parse(args);
            // then
            Assertions.assertThat(result.isHelp()).isTrue();
        }
        catch(Exception e)
        {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void parse_WhenMemoryFlag_ThenFlagValue()
    {
        // given
        int value = 10;
        String filename = "x.apo";
        String[] args = new String[]{"-m", Integer.toString(value), filename};

        try
        {
            // when
            CliParser.Arguments result = testObject.parse(args);
            // then
            Assertions.assertThat(result.isHelp()).isFalse();
            Assertions.assertThat(result.getMemorySize()).isEqualTo(value);
            Assertions.assertThat(result.getFilename()).isEqualTo(filename);
        }
        catch(Exception e)
        {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void parse_WhenMultipleFiles_ThenFirstTaken()
    {
        // given
        String filename = "x.apo";
        String[] args = new String[]{filename, "y.txt"};

        try
        {
            // when
            CliParser.Arguments result = testObject.parse(args);
            // then
            Assertions.assertThat(result.isHelp()).isFalse();
            Assertions.assertThat(result.getMemorySize()).isEqualTo(1);
            Assertions.assertThat(result.getFilename()).isEqualTo(filename);
        }
        catch(Exception e)
        {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void parse_WhenNoSizeForMemoryFlag_ThenParseException()
    {
        // when
        Throwable throwable =
                Assertions.catchThrowable(() -> testObject.parse(new String[]{"-m", "x.apo"}));
        // then
        Assertions.assertThat(throwable).isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenInvalidMemorySize_ThenParseException()
    {
        // when
        Throwable throwable =
                Assertions.catchThrowable(() -> testObject.parse(new String[]{"-m", "A"}));
        // then
        Assertions.assertThat(throwable).isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenNoFile_ThenParseException()
    {
        // when
        Throwable throwable =
                Assertions.catchThrowable(() -> testObject.parse(new String[]{"-m", "5"}));
        // then
        Assertions.assertThat(throwable).isInstanceOf(ParseException.class);
    }

    @Test
    public void parse_WhenFileHasWrongExtension_ThenParseException()
    {
        // when
        Throwable throwable =
                Assertions.catchThrowable(() -> testObject.parse(new String[]{"x.txt"}));
        // then
        Assertions.assertThat(throwable).isInstanceOf(ParseException.class);
    }
}
