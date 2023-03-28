package apolang.interpreter.memory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
        // when
        Exception exception = Assertions.catchException(() -> testObject.storeWord(7, 0x01ABCDEF));
        // then
        Assertions.assertThat(exception).isInstanceOf(MemoryException.class);
    }

    @Test
    public void storeWord_loadWord()
            throws MemoryException
    {
        // given
        int value = 0x01ABCDEF;
        int address = 0;
        // when
        testObject.storeWord(address, value);
        int result = testObject.loadWord(address);
        // then
        Assertions.assertThat(result).isEqualTo(value);
    }

    @Test
    public void storeByte_loadByte()
            throws MemoryException
    {
        // given
        int value = 0x78;
        int address = 10;
        // when
        testObject.storeByte(address, value);
        int result = testObject.loadByte(address);
        // then
        Assertions.assertThat(result).isEqualTo(value);
    }

    @Test
    public void storeByte_loadWord_WhenCorrectAddress()
            throws MemoryException
    {
        // given
        int[] values = new int[]{0x78, 0xED, 0x40, 0x9C};
        int address = 16;
        int expected = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];
        // when
        testObject.storeByte(address, values[0]);
        testObject.storeByte(address + 1, values[1]);
        testObject.storeByte(address + 2, values[2]);
        testObject.storeByte(address + 3, values[3]);
        int result = testObject.loadWord(address);
        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void storeByte_loadWord_WhenInvalidAddress_ThenMemoryException()
            throws MemoryException
    {
        // given
        int[] values = new int[]{0x78, 0xED, 0x40, 0x9C};
        int address = 23;

        testObject.storeByte(address, values[0]);
        testObject.storeByte(address + 1, values[1]);
        testObject.storeByte(address + 2, values[2]);
        testObject.storeByte(address + 3, values[3]);
        // when
        Exception exception = Assertions.catchException(() -> testObject.loadWord(address));
        // then
        Assertions.assertThat(exception).isInstanceOf(MemoryException.class);
    }

    @Test
    public void storeWord_loadByte()
            throws MemoryException
    {
        // given
        int[] values = new int[]{0x23, 0x45, 0xBD, 0xDE};
        int address = 32;
        int value = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];
        // when
        int[] results = new int[4];

        testObject.storeWord(address, value);
        results[0] = testObject.loadByte(address);
        results[1] = testObject.loadByte(address + 1);
        results[2] = testObject.loadByte(address + 2);
        results[3] = testObject.loadByte(address + 3);
        // then
        Assertions.assertThat(results).containsExactly(values);
    }
}
