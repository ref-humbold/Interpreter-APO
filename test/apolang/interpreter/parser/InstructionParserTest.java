package apolang.interpreter.parser;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import apolang.exceptions.LanguageException;
import apolang.exceptions.arithmetic.ArithmeticException;
import apolang.exceptions.label.LabelNotFoundException;
import apolang.exceptions.symbol.AssignmentToZeroException;
import apolang.exceptions.symbol.InvalidVariableNameException;
import apolang.exceptions.symbol.NotExistingInstructionException;
import apolang.exceptions.symbol.SymbolException;
import apolang.exceptions.symbol.VariableNotInitializedException;
import apolang.instruction.InstructionName;
import apolang.instruction.instructions.JumpInstruction;
import apolang.instruction.list.InstructionList;
import apolang.interpreter.Environment;

public class InstructionParserTest
{
    private Environment environment;
    private InstructionParser testObject;

    @BeforeEach
    public void setUp()
    {
        environment = new Environment();

        try
        {
            environment.addVariable("a");
            environment.addVariable("b");
            environment.addVariable("c");
            environment.addLabel("La");
            environment.addLabel("Lb");
            environment.addLabel("Lc");
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
    }

    @Test
    public void parse_WhenCorrectProgram_ThenInstructionList()
    {
        // given
        List<String> lines =
                Arrays.asList("ASGNC a 0x11", "JPGT a zero Lb", "JPLT a zero Lc", "# equal",
                              "MULC a a 2", "Lb: SUBC a a 1", "Lc: PTINT a");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = null;

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
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(InstructionName.ASGNC, result.getByLineNumber(1).getName());
        Assertions.assertEquals(InstructionName.JPGT, result.getByLineNumber(2).getName());
        Assertions.assertEquals(InstructionName.JPLT, result.getByLineNumber(3).getName());
        Assertions.assertEquals(InstructionName.MULC, result.getByLineNumber(4).getName());
        Assertions.assertEquals(InstructionName.MULC, result.getByLineNumber(5).getName());
        Assertions.assertEquals(InstructionName.SUBC, result.getByLineNumber(6).getName());
        Assertions.assertEquals(InstructionName.PTINT, result.getByLineNumber(7).getName());

        JumpInstruction jumpGreaterThan = (JumpInstruction)result.getByLineNumber(2);
        JumpInstruction jumpLessThan = (JumpInstruction)result.getByLineNumber(3);

        Assertions.assertSame(result.getByLineNumber(6), jumpGreaterThan.getLink());
        Assertions.assertSame(result.getByLineNumber(7), jumpLessThan.getLink());
    }

    @Test
    public void parse_WhenEmptyProgram_ThenInstructionListIsEmpty()
    {
        // given
        List<String> lines = List.of("# only empty line", "");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = null;

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
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void parse_WhenSingleLabel_ThenOnlyNOPInstruction()
    {
        // given
        List<String> lines = List.of("# only label", "La:");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = null;

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
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    public void parse_WhenNoSuchInstruction_ThenNotExistingInstructionException()
    {
        // given
        List<String> lines = List.of("XYZ a b c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(NotExistingInstructionException.class, executable);
    }

    @Test
    public void parse_WhenTooFewArguments_ThenSymbolException()
    {
        // given
        List<String> lines = List.of("ADD a b");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(SymbolException.class, executable);
    }

    @Test
    public void parse_WhenNoSuchVariable_ThenVariableNotInitializedException()
    {
        // given
        List<String> lines = List.of("ADD a x c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(VariableNotInitializedException.class, executable);
    }

    @Test
    public void parse_WhenIncorrectVariableName_ThenInvalidVariableNameException()
    {
        // given
        List<String> lines = List.of("ADD a X c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(InvalidVariableNameException.class, executable);
    }

    @Test
    public void parse_WhenWriteToVariableZero_ThenAssignmentToZeroException()
    {
        // given
        List<String> lines = List.of("ADD zero b c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(AssignmentToZeroException.class, executable);
    }

    @Test
    public void parse_WhenNoSuchLabelToJump_ThenLabelNotFoundException()
    {
        // given
        List<String> lines = List.of("JUMP Xyz");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(LabelNotFoundException.class, executable);
    }

    @Test
    public void parse_WhenNotAConstant_ThenArithmeticException()
    {
        // given
        List<String> lines = List.of("ASGNC a b");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(ArithmeticException.class, executable);
    }

    @Test
    public void parse_WhenNoSuchLabel_ThenLabelNotFoundException()
    {
        // given
        List<String> lines = List.of("Xyz: ASGNC a 10");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Executable executable = () -> testObject.parse();
        // then
        Assertions.assertThrows(LabelNotFoundException.class, executable);
    }
}
