package apolang.interpreter.memory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apolang.exceptions.memory.MemoryException;

public class MemoryTest
{
    private Memory testObject;

    @BeforeEach
    public void setUp()
    {
        testObject = new Memory(1);
    }

    @AfterEach
    public void tearDown()
    {
        testObject = null;
    }

    @Test
    public void storeWord_WhenInvalidAddress_ThenMemoryException()
    {
        int value = 0x01ABCDEF;
        int address = 7;

        Assertions.assertThrows(MemoryException.class, () -> testObject.storeWord(address, value));
    }

    @Test
    public void storeWordLoadWord()
    {
        int value = 0x01ABCDEF;
        int address = 0;
        int result;

        try
        {
            testObject.storeWord(address, value);
            result = testObject.loadWord(address);

            Assertions.assertEquals(value, result);
        }
        catch(MemoryException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void storeByteLoadByte()
    {
        int value = 0x78;
        int address = 10;
        int result;

        try
        {
            testObject.storeByte(address, value);
            result = testObject.loadByte(address);

            Assertions.assertEquals(value, result);
        }
        catch(MemoryException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void storeByteLoadWord_WhenCorrectAddress()
    {
        int[] values = new int[]{0x78, 0xED, 0x40, 0x9C};
        int address = 16;
        int expected = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];
        int result = 0;

        try
        {
            testObject.storeByte(address, values[0]);
            testObject.storeByte(address + 1, values[1]);
            testObject.storeByte(address + 2, values[2]);
            testObject.storeByte(address + 3, values[3]);
            result = testObject.loadWord(address);
        }
        catch(MemoryException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void storeByteLoadWord_WhenInvalidAddress_ThenMemoryException()
    {
        int[] values = new int[]{0x78, 0xED, 0x40, 0x9C};
        int address = 23;

        try
        {
            testObject.storeByte(address, values[0]);
            testObject.storeByte(address + 1, values[1]);
            testObject.storeByte(address + 2, values[2]);
            testObject.storeByte(address + 3, values[3]);
        }
        catch(MemoryException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }

        Assertions.assertThrows(MemoryException.class, () -> testObject.loadWord(address));
    }

    @Test
    public void storeWordLoadByte()
    {
        int[] values = new int[]{0x23, 0x45, 0xBD, 0xDE};
        int address = 32;
        int value = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];
        int[] results = new int[4];

        try
        {
            testObject.storeWord(address, value);
            results[0] = testObject.loadByte(address);
            results[1] = testObject.loadByte(address + 1);
            results[2] = testObject.loadByte(address + 2);
            results[3] = testObject.loadByte(address + 3);

            Assertions.assertEquals(values[0], results[0]);
            Assertions.assertEquals(values[1], results[1]);
            Assertions.assertEquals(values[2], results[2]);
            Assertions.assertEquals(values[3], results[3]);
        }
        catch(MemoryException e)
        {
            e.printStackTrace();
            Assertions.fail("Unexpected exception " + e.getClass().getSimpleName());
        }
    }
}
