package com.github.refhumbold.apolang.interpreter.memory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.refhumbold.apolang.exceptions.memory.MemoryException;

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
        Assertions.assertThatThrownBy(() -> testObject.storeWord(7, 0x01ABCDEF))
                  .isInstanceOf(MemoryException.class);
    }

    @Test
    public void storeWord_WhenNextLoadWord_ThenLoaded()
            throws Exception
    {
        // given
        int value = 0x01ABCDEF;
        int address = 0;

        // when
        testObject.storeWord(address, value);

        // then
        Assertions.assertThat(testObject.loadWord(address)).isEqualTo(value);
    }

    @Test
    public void storeByte_WhenNextLoadByte_ThenLoaded()
            throws Exception
    {
        // given
        int value = 0x78;
        int address = 10;

        // when
        testObject.storeByte(address, value);

        // then
        Assertions.assertThat(testObject.loadByte(address)).isEqualTo(value);
    }

    @Test
    public void storeByte_WhenNextLoadWordWithCorrectAddress_ThenLoaded()
            throws Exception
    {
        // given
        int[] values = new int[]{ 0x78, 0xED, 0x40, 0x9C };
        int address = 16;
        int expected = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];

        // when
        testObject.storeByte(address, values[0]);
        testObject.storeByte(address + 1, values[1]);
        testObject.storeByte(address + 2, values[2]);
        testObject.storeByte(address + 3, values[3]);

        // then
        Assertions.assertThat(testObject.loadWord(address)).isEqualTo(expected);
    }

    @Test
    public void storeByte_WhenNextLoadWordWithInvalidAddress_ThenMemoryException()
            throws Exception
    {
        // given
        int[] values = new int[]{ 0x78, 0xED, 0x40, 0x9C };
        int address = 23;

        testObject.storeByte(address, values[0]);
        testObject.storeByte(address + 1, values[1]);
        testObject.storeByte(address + 2, values[2]);
        testObject.storeByte(address + 3, values[3]);

        // then
        Assertions.assertThatThrownBy(() -> testObject.loadWord(address))
                  .isInstanceOf(MemoryException.class);
    }

    @Test
    public void storeWord_WhenNextLoadByte_ThenLoaded()
            throws Exception
    {
        // given
        int[] values = new int[]{ 0x23, 0x45, 0xBD, 0xDE };
        int address = 32;
        int value = (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];

        // when
        testObject.storeWord(address, value);

        // then
        int[] word = new int[4];

        word[0] = testObject.loadByte(address);
        word[1] = testObject.loadByte(address + 1);
        word[2] = testObject.loadByte(address + 2);
        word[3] = testObject.loadByte(address + 3);

        Assertions.assertThat(word).containsExactly(values);
    }
}
