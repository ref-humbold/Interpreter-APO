package apolang.instruction;

import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.errors.LanguageException;
import apolang.interpreter.Environment;

public class InstructionListTest
{
    private static final String[] VARS = new String[]{"one", "two", "tree", "res"};
    private static final String LABEL = "Label";
    private final Environment environment = new Environment();
    private InstructionList testObject;

    public InstructionListTest()
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
        testObject = new InstructionList();
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void add_WhenEmpty()
    {
        // given
        Instruction instruction =
                InstructionFactory.create(1, InstructionName.ADD, VARS[3], VARS[0], VARS[1]);
        // when
        testObject.add(instruction);
        // then
        Iterator<Instruction> it = testObject.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(instruction, it.next());
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void add_WhenNotEmpty()
    {
        // given
        Instruction instruction1 =
                InstructionFactory.create(1, InstructionName.ADD, VARS[3], VARS[0], VARS[1]);
        Instruction instruction2 =
                InstructionFactory.create(1, InstructionName.MUL, VARS[3], VARS[0], VARS[1]);
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
    public void add_WhenInstructionNull()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> testObject.add(null));
    }

    @Test
    public void iterator_WhenEmptyList()
    {
        Iterator<Instruction> iterator = testObject.iterator();

        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_WhenNonEmptyList()
    {
        // given
        Instruction instruction =
                InstructionFactory.create(1, InstructionName.ADD, VARS[3], VARS[0], VARS[1]);

        testObject.add(instruction);
        // when
        Iterator<Instruction> iterator = testObject.iterator();
        // then
        Assertions.assertTrue(iterator.hasNext());

        Instruction result = iterator.next();

        Assertions.assertEquals(instruction, result);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorNext_WhenJump()
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, InstructionName.JPNE, VARS[0], VARS[1]);
        Instruction instruction2 =
                InstructionFactory.create(2, InstructionName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                InstructionFactory.create(2, InstructionName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        testObject.add(instruction1);
        testObject.add(instruction2);
        testObject.add(instruction3);
        // when
        Iterator<Instruction> iterator = testObject.iterator();
        // then
        Assertions.assertTrue(iterator.hasNext());

        Instruction result1 = iterator.next();

        Assertions.assertEquals(instruction1, result1);
        Assertions.assertTrue(iterator.hasNext());

        try
        {
            result1.execute(environment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }

        Instruction result3 = iterator.next();

        Assertions.assertEquals(instruction3, result3);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorNext_WhenNoJump()
    {
        // given
        JumpInstruction instruction1 =
                new JumpInstruction(1, InstructionName.JPEQ, VARS[0], VARS[1]);
        Instruction instruction2 =
                InstructionFactory.create(2, InstructionName.ADD, VARS[3], VARS[0], VARS[2]);
        Instruction instruction3 =
                InstructionFactory.create(2, InstructionName.MUL, VARS[3], VARS[0], VARS[2]);

        instruction1.setLink(instruction3);
        testObject.add(instruction1);
        testObject.add(instruction2);
        testObject.add(instruction3);
        // when
        Iterator<Instruction> iterator = testObject.iterator();
        // then
        Assertions.assertTrue(iterator.hasNext());

        Instruction result1 = iterator.next();

        Assertions.assertEquals(instruction1, result1);
        Assertions.assertTrue(iterator.hasNext());

        try
        {
            result1.execute(environment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail(String.format("Unexpected exception %s", e.getClass().getSimpleName()));
        }

        Instruction result2 = iterator.next();

        Assertions.assertEquals(instruction2, result2);
        Assertions.assertTrue(iterator.hasNext());

        Instruction result3 = iterator.next();

        Assertions.assertEquals(instruction3, result3);
        Assertions.assertFalse(iterator.hasNext());
    }
}
