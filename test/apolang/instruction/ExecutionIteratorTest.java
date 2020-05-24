package apolang.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.exceptions.LanguageException;
import apolang.instruction.instructions.Instruction;
import apolang.instruction.instructions.JumpInstruction;
import apolang.instruction.list.ExecutionIterator;
import apolang.instruction.list.InstructionList;
import apolang.interpreter.Environment;

public class ExecutionIteratorTest
{
    private static final String[] VARS = new String[]{"one", "two", "tree", "res"};
    private static final String LABEL = "Label";
    private final Environment environment = new Environment();
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
        Assertions.assertFalse(result);
    }

    @Test
    public void hasNext_WhenNonEmptyList_ThenTrue()
    {
        // given
        Instruction instruction =
                InstructionFactory.create(1, InstructionName.ADD, VARS[3], VARS[0], VARS[1]);

        instructionList.add(instruction);
        ExecutionIterator testObject = instructionList.run();
        // when
        boolean result = testObject.hasNext();
        // then
        Assertions.assertTrue(result);
    }

    @Test
    public void next_WhenStandardInstruction_ThenFollowingInstruction()
    {
        Instruction instruction1 =
                InstructionFactory.create(2, InstructionName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction2 =
                InstructionFactory.create(2, InstructionName.MUL, VARS[3], VARS[0], VARS[2]);

        instructionList.add(instruction1);
        instructionList.add(instruction2);

        ExecutionIterator testObject = instructionList.run();
        // when
        Instruction result1 = testObject.next();
        Instruction result2 = testObject.next();
        // then
        Assertions.assertEquals(instruction1, result1);
        Assertions.assertEquals(instruction2, result2);
    }

    @Test
    public void next_WhenJump_ThenLinkedInstruction()
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, InstructionName.JPNE, VARS[0], VARS[1]);
        Instruction instruction2 =
                InstructionFactory.create(2, InstructionName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                InstructionFactory.create(2, InstructionName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        instructionList.add(instruction3);

        ExecutionIterator testObject = instructionList.run();
        Instruction result1 = testObject.next();

        try
        {
            result1.execute(environment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // when
        Instruction result = testObject.next();
        // then
        Assertions.assertEquals(instruction3, result);
        Assertions.assertFalse(testObject.hasNext());
    }

    @Test
    public void next_WhenNoJump_ThenFollowingInstruction()
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, InstructionName.JPEQ, VARS[0], VARS[1]);
        Instruction instruction2 =
                InstructionFactory.create(2, InstructionName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                InstructionFactory.create(2, InstructionName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        instructionList.add(instruction3);

        ExecutionIterator testObject = instructionList.run();
        Instruction result1 = testObject.next();

        try
        {
            result1.execute(environment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }
        // when
        Instruction result = testObject.next();
        // then
        Assertions.assertEquals(instruction2, result);
        Assertions.assertTrue(testObject.hasNext());
    }
}
