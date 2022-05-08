package apolang.instructions;

import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.exceptions.LanguageException;
import apolang.instructions.instruction.Instruction;
import apolang.instructions.instruction.JumpInstruction;
import apolang.instructions.list.ExecutionIterator;
import apolang.instructions.list.InstructionList;
import apolang.instructions.statement.JumpBaseStatement;
import apolang.instructions.statement.StatementName;
import apolang.interpreter.Environment;

public class ExecutionIteratorTest
{
    private static final String[] VARS = new String[]{"one", "two", "tree", "res"};
    private static final String LABEL = "Label";
    private final Environment environment = new Environment();
    private final InstructionFactory instructionFactory = InstructionFactory.getInstance();
    private InstructionList instructionList;

    public ExecutionIteratorTest()
            throws LanguageException
    {
        environment.addLabel(LABEL);

        for(int i = 0; i < VARS.length; ++i)
        {
            environment.addVariable(VARS[i]);
            environment.setVariableValue(VARS[i], i);
        }
    }

    @BeforeEach
    public void setUp()
    {
        instructionList = new InstructionList();
    }

    @AfterEach
    public void tearDown()
    {
        instructionList = null;
    }

    @Test
    public void hasNext_WhenEmptyList_ThenFalse()
    {
        // given
        ExecutionIterator testObject = instructionList.run();
        // when
        boolean result = testObject.hasNext();
        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void hasNext_WhenNonEmptyList_ThenTrue()
    {
        // given
        Instruction instruction =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);

        instructionList.add(instruction);
        ExecutionIterator testObject = instructionList.run();
        // when
        boolean result = testObject.hasNext();
        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void next_WhenStandardInstruction_ThenFollowingInstruction()
    {
        Instruction instruction1 =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction2 =
                instructionFactory.create(2, StatementName.MUL, VARS[3], VARS[0], VARS[2]);

        instructionList.add(instruction1);
        instructionList.add(instruction2);

        ExecutionIterator testObject = instructionList.run();
        // when
        Instruction result1 = testObject.next();
        Instruction result2 = testObject.next();
        // then
        Assertions.assertThat(result1).isEqualTo(instruction1);
        Assertions.assertThat(result2).isEqualTo(instruction2);
    }

    @Test
    public void next_WhenJump_ThenLinkedInstruction()
            throws LanguageException
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, (JumpBaseStatement)StatementName.JPNE.getStatement(),
                                    VARS[0], VARS[1]);
        Instruction instruction2 =
                instructionFactory.create(2, StatementName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                instructionFactory.create(3, StatementName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        instructionList.add(instruction3);

        ExecutionIterator testObject = instructionList.run();

        testObject.next().execute(environment);
        // when
        Instruction result = testObject.next();
        // then
        Assertions.assertThat(result).isEqualTo(instruction3);
        Assertions.assertThat(testObject.hasNext()).isFalse();
    }

    @Test
    public void next_WhenNoJump_ThenFollowingInstruction()
            throws LanguageException
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, (JumpBaseStatement)StatementName.JPEQ.getStatement(),
                                    VARS[0], VARS[1]);
        Instruction instruction2 =
                instructionFactory.create(2, StatementName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                instructionFactory.create(3, StatementName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        instructionList.add(instruction3);

        ExecutionIterator testObject = instructionList.run();

        testObject.next().execute(environment);
        // when
        Instruction result = testObject.next();
        // then
        Assertions.assertThat(result).isEqualTo(instruction2);
        Assertions.assertThat(testObject.hasNext()).isTrue();
    }

    @Test
    public void next_WhenExitInstruction_ThenNoSuchElementException()
            throws LanguageException
    {
        // given
        Instruction instruction1 =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction2 = instructionFactory.create(2, StatementName.EXIT);
        Instruction instruction3 =
                instructionFactory.create(3, StatementName.MUL, VARS[3], VARS[0], VARS[2]);

        instructionList.add(instruction1);
        instructionList.add(instruction2);
        instructionList.add(instruction3);

        ExecutionIterator testObject = instructionList.run();

        testObject.next();
        testObject.next().execute(environment);
        // when
        Exception exception = Assertions.catchException(testObject::next);
        // then
        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class);
        Assertions.assertThat(testObject.hasNext()).isFalse();
    }
}
