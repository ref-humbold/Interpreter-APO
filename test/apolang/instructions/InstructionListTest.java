package apolang.instructions;

import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.errors.LanguageException;
import apolang.interpreter.environment.VariableEnvironment;

public class InstructionListTest
{
    private static final String[] VARS = new String[]{"zero", "one", "two", "tree", "res"};
    private final VariableEnvironment variableEnvironment;
    private InstructionList testObject;

    public InstructionListTest()
    {
        variableEnvironment = new VariableEnvironment();

        for(int i = 0; i < VARS.length; ++i)
            variableEnvironment.setValue(VARS[i], i);
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
        int count = 1;
        InstructionName name = InstructionName.ADD;
        int[] args = new int[]{4, 1, 2};

        Instruction instruction = InstructionFactory.create(count, name, args);

        testObject.add(instruction);

        Iterator<Instruction> it = testObject.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(instruction, it.next());
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void add_WhenNotEmpty()
    {
        int count1 = 1;
        InstructionName name1 = InstructionName.ADD;
        int[] args1 = new int[]{4, 1, 2};
        int count2 = 1;
        InstructionName name2 = InstructionName.MUL;
        int[] args2 = new int[]{4, 1, 2};

        Instruction instruction1 = InstructionFactory.create(count1, name1, args1);
        Instruction instruction2 = InstructionFactory.create(count2, name2, args2);

        testObject.add(instruction1);
        testObject.add(instruction2);

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
        int count = 1;
        InstructionName name = InstructionName.ADD;
        int[] args = new int[]{4, 1, 2};
        Instruction instruction = InstructionFactory.create(count, name, args);

        testObject.add(instruction);

        Iterator<Instruction> iterator = testObject.iterator();

        Assertions.assertTrue(iterator.hasNext());

        Instruction result = iterator.next();

        Assertions.assertEquals(instruction, result);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorNext_WhenJump()
    {
        int count1 = 1;
        InstructionName name1 = InstructionName.JPNE;
        int[] args1 = new int[]{1, 2};
        JumpInstruction instruction1 = new JumpInstruction(count1, name1, args1);
        int count2 = 2;
        InstructionName name2 = InstructionName.ADD;
        int[] args2 = new int[]{4, 1, 3};
        Instruction instruction2 = InstructionFactory.create(count2, name2, args2);
        int count3 = 2;
        InstructionName name3 = InstructionName.MUL;
        int[] args3 = new int[]{4, 1, 3};
        Instruction instruction3 = InstructionFactory.create(count3, name3, args3);

        instruction1.setLink(instruction3);

        testObject.add(instruction1);
        testObject.add(instruction2);
        testObject.add(instruction3);

        Iterator<Instruction> iterator = testObject.iterator();

        Assertions.assertTrue(iterator.hasNext());

        Instruction result1 = iterator.next();

        Assertions.assertEquals(instruction1, result1);
        Assertions.assertTrue(iterator.hasNext());

        try
        {
            result1.execute(variableEnvironment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Instruction result3 = iterator.next();

        Assertions.assertEquals(instruction3, result3);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorNext_WhenNoJump()
    {
        int count1 = 1;
        InstructionName name1 = InstructionName.JPEQ;
        int[] args1 = new int[]{1, 2};
        JumpInstruction instruction1 = new JumpInstruction(count1, name1, args1);
        int count2 = 2;
        InstructionName name2 = InstructionName.ADD;
        int[] args2 = new int[]{4, 1, 3};
        Instruction instruction2 = InstructionFactory.create(count2, name2, args2);
        int count3 = 2;
        InstructionName name3 = InstructionName.MUL;
        int[] args3 = new int[]{4, 1, 3};
        Instruction instruction3 = InstructionFactory.create(count3, name3, args3);

        instruction1.setLink(instruction3);

        testObject.add(instruction1);
        testObject.add(instruction2);
        testObject.add(instruction3);

        Iterator<Instruction> iterator = testObject.iterator();

        Assertions.assertTrue(iterator.hasNext());

        Instruction result1 = iterator.next();

        Assertions.assertEquals(instruction1, result1);
        Assertions.assertTrue(iterator.hasNext());

        try
        {
            result1.execute(variableEnvironment);
        }
        catch(LanguageException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Instruction result2 = iterator.next();

        Assertions.assertEquals(instruction2, result2);
        Assertions.assertTrue(iterator.hasNext());

        Instruction result3 = iterator.next();

        Assertions.assertEquals(instruction3, result3);
        Assertions.assertFalse(iterator.hasNext());
    }
}
