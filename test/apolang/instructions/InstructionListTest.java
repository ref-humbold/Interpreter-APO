package apolang.instructions;

import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import apolang.instructions.instruction.Instruction;
import apolang.instructions.list.InstructionList;
import apolang.instructions.statement.StatementName;

public class InstructionListTest
{
    private static final String[] VARS = new String[]{"one", "two", "tree", "res"};
    private final InstructionFactory instructionFactory = InstructionFactory.getInstance();
    private InstructionList testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new InstructionList();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void isEmpty_WhenEmpty_ThenTrue()
    {
        // when
        boolean result = testObject.isEmpty();
        // then
        Assertions.assertTrue(result);
    }

    @Test
    public void isEmpty_WhenNotEmpty_ThenFalse()
    {
        // given
        Instruction instruction =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);

        testObject.add(instruction);
        // when
        boolean result = testObject.isEmpty();
        // then
        Assertions.assertFalse(result);
    }

    @Test
    public void add_WhenEmpty_ThenSingleInstruction()
    {
        // given
        Instruction instruction =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);
        // when
        testObject.add(instruction);
        // then
        Iterator<Instruction> it = testObject.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(instruction, it.next());
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void add_WhenNotEmpty_ThenAddedToEnd()
    {
        // given
        Instruction instruction1 =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);
        Instruction instruction2 =
                instructionFactory.create(2, StatementName.MUL, VARS[3], VARS[0], VARS[1]);
        // when
        testObject.add(instruction1);
        testObject.add(instruction2);
        // then
        Iterator<Instruction> it = testObject.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(instruction1, it.next());
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(instruction2, it.next());
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void add_WhenInstructionNull_ThenNullPointerException()
    {
        // when
        Executable executable = () -> testObject.add(null);
        // then
        Assertions.assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void getByLineNumber_WhenExistsLineNumber_ThenInstruction()
    {
        // given
        Instruction instruction1 =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);
        Instruction instruction2 =
                instructionFactory.create(2, StatementName.MUL, VARS[3], VARS[0], VARS[1]);

        testObject.add(instruction1);
        testObject.add(instruction2);
        // when
        Instruction result = testObject.getByLineNumber(2);
        // then
        Assertions.assertEquals(instruction2, result);
    }

    @Test
    public void getByLineNumber_WhenNotExistsLineNumber_ThenNearestNextInstruction()
    {
        // given
        Instruction instruction1 =
                instructionFactory.create(1, StatementName.ADD, VARS[3], VARS[0], VARS[1]);
        Instruction instruction5 =
                instructionFactory.create(5, StatementName.MUL, VARS[3], VARS[0], VARS[1]);

        testObject.add(instruction1);
        testObject.add(instruction5);
        // when
        Instruction result = testObject.getByLineNumber(3);
        // then
        Assertions.assertEquals(instruction5, result);
    }
}
