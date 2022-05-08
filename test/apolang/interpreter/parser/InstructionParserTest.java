package apolang.interpreter.parser;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.exceptions.LanguageException;
import apolang.exceptions.arithmetic.ArithmeticException;
import apolang.exceptions.label.InvalidLabelNameException;
import apolang.exceptions.label.LabelNotFoundException;
import apolang.exceptions.symbol.AssignmentToZeroException;
import apolang.exceptions.symbol.InvalidVariableNameException;
import apolang.exceptions.symbol.NotExistingInstructionException;
import apolang.exceptions.symbol.SymbolException;
import apolang.exceptions.symbol.VariableNotInitializedException;
import apolang.instructions.instruction.Instruction;
import apolang.instructions.instruction.JumpInstruction;
import apolang.instructions.list.InstructionList;
import apolang.instructions.statement.Statement;
import apolang.instructions.statement.StatementName;
import apolang.interpreter.Environment;

public class InstructionParserTest
{
    private Environment environment;
    private InstructionParser testObject;

    @BeforeEach
    public void setUp()
            throws InvalidVariableNameException, InvalidLabelNameException
    {
        environment = new Environment();
        environment.addVariable("a");
        environment.addVariable("b");
        environment.addVariable("c");
        environment.addLabel("La");
        environment.addLabel("Lb");
        environment.addLabel("Lc");
    }

    @Test
    public void parse_WhenCorrectProgram_ThenInstructionList()
            throws LanguageException
    {
        // given
        List<String> lines =
                Arrays.asList("ASGNC a 0x11", "JPGT a zero Lb", "JPLT a zero Lc", "# equal",
                              "MULC a a 2", "Lb: SUBC a a 1", "Lc: PTINT a");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = testObject.parse();
        // then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.getByLineNumber(1))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.ASGNC);
        Assertions.assertThat(result.getByLineNumber(2))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.JPGT);
        Assertions.assertThat(result.getByLineNumber(3))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.JPLT);
        Assertions.assertThat(result.getByLineNumber(4))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.MULC);
        Assertions.assertThat(result.getByLineNumber(5))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.MULC);
        Assertions.assertThat(result.getByLineNumber(6))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.SUBC);
        Assertions.assertThat(result.getByLineNumber(7))
                  .extracting(Instruction::getStatement)
                  .extracting(Statement::getName)
                  .isEqualTo(StatementName.PTINT);

        JumpInstruction jumpGreaterThan = (JumpInstruction)result.getByLineNumber(2);
        JumpInstruction jumpLessThan = (JumpInstruction)result.getByLineNumber(3);

        Assertions.assertThat(result.getByLineNumber(6)).isSameAs(jumpGreaterThan.getLink());
        Assertions.assertThat(result.getByLineNumber(7)).isSameAs(jumpLessThan.getLink());
    }

    @Test
    public void parse_WhenEmptyProgram_ThenInstructionListIsEmpty()
            throws LanguageException
    {
        // given
        List<String> lines = List.of("# only empty line", "");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = testObject.parse();
        // then
        Assertions.assertThat(result).isEmpty();
        Assertions.assertThat(result.getLinesCount()).isZero();
    }

    @Test
    public void parse_WhenSingleLabel_ThenOnlyNOPInstruction()
            throws LanguageException
    {
        // given
        List<String> lines = List.of("# only label", "La:");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = testObject.parse();
        // then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.getLinesCount()).isEqualTo(2);
    }

    @Test
    public void parse_WhenEndLabel_ThenEndsWithNOPInstruction()
            throws LanguageException
    {
        // given
        List<String> lines = List.of("# jump to End", "JUMP End");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        InstructionList result = testObject.parse();
        // then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.getLinesCount()).isEqualTo(3);
    }

    @Test
    public void parse_WhenNoSuchInstruction_ThenNotExistingInstructionException()
    {
        // given
        List<String> lines = List.of("XYZ a b c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(NotExistingInstructionException.class);
    }

    @Test
    public void parse_WhenTooFewArguments_ThenSymbolException()
    {
        // given
        List<String> lines = List.of("ADD a b");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(SymbolException.class);
    }

    @Test
    public void parse_WhenNoSuchVariable_ThenVariableNotInitializedException()
    {
        // given
        List<String> lines = List.of("ADD a x c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(VariableNotInitializedException.class);
    }

    @Test
    public void parse_WhenIncorrectVariableName_ThenInvalidVariableNameException()
    {
        // given
        List<String> lines = List.of("ADD a X c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(InvalidVariableNameException.class);
    }

    @Test
    public void parse_WhenWriteToVariableZero_ThenAssignmentToZeroException()
    {
        // given
        List<String> lines = List.of("ADD zero b c");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(AssignmentToZeroException.class);
    }

    @Test
    public void parse_WhenNoSuchLabelToJump_ThenLabelNotFoundException()
    {
        // given
        List<String> lines = List.of("JUMP Xyz");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(LabelNotFoundException.class);
    }

    @Test
    public void parse_WhenNotAConstant_ThenArithmeticException()
    {
        // given
        List<String> lines = List.of("ASGNC a b");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void parse_WhenNoSuchLabel_ThenLabelNotFoundException()
    {
        // given
        List<String> lines = List.of("Xyz: ASGNC a 10");

        testObject = new InstructionParser(lines);
        testObject.setEnvironment(environment);
        // when
        Exception exception = Assertions.catchException(() -> testObject.parse());
        // then
        Assertions.assertThat(exception).isInstanceOf(LabelNotFoundException.class);
    }
}
