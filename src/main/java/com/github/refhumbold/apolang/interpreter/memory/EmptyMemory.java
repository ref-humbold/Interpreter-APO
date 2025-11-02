package com.github.refhumbold.apolang.interpreter.memory;

import com.github.refhumbold.apolang.exceptions.memory.MemoryException;
import com.github.refhumbold.apolang.exceptions.memory.OutOfMemoryException;

public class EmptyMemory
        implements MemoryInterface
{
    @Override
    public int loadWord(int address)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public void storeWord(int address, int value)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public int loadByte(int address)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }

    @Override
    public void storeByte(int address, int value)
            throws MemoryException
    {
        throw new OutOfMemoryException();
    }
}
